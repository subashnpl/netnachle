package domain.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.Map.Entry;

import domain.Movie;
import domain.User;

public class Controller {
	private DataManipulate _moviesHandler;
	private DataManipulate _usersHandler;
	private MatrixHandler _matrixHandler;
	private Logger logger;
	private HashMap<Integer, User> _users;
	private HashMap<Integer, Movie> _movies;
	private HashMap<Integer, User> _usersIn;  //this is a hash map that tell us who looged in

	public Controller(){
		upLoad();
		_matrixHandler = new MatrixHandler(_users, _movies);
	}//constructor

	public DataManipulate get_movieHandler() {
		return _moviesHandler;
	}
	public void set_movieHandler(DataManipulate handler) {
		_moviesHandler = handler;
	}
	public DataManipulate get_usersHandler() {
		return _usersHandler;
	}
	public void set_usersHandler(DataManipulate handler) {
		_usersHandler = handler;
	}
	public HashMap<Integer, User> get_users() {
		return _users;
	}
	public void set_users(HashMap<Integer, User> _users) {
		this._users = _users;
	}

	public HashMap<Integer, User> get_usersIn() {
		return _usersIn;
	}

	public void set_usersIn(HashMap<Integer, User> in) {
		_usersIn = in;
	}
	public HashMap<Integer, Movie> get_movies() {
		return _movies;
	}
	public void set_movies(HashMap<Integer, Movie> movies) {
		this._movies = movies;
	}
	public void addUser(User usr){
		_users.put(usr.getId(), usr);
		logger.log("User " + usr.getName() + " has been added to database.");
	}
	public MatrixHandler get_matrixHandler() {
		return _matrixHandler;
	}

	public void set_matrixHandler(MatrixHandler handler) {
		_matrixHandler = handler;
	}
	//public void removeUser(int i){
	//	_users.remove(i);
	public void removeUser(User u){
		_users.remove(u);
		logger.log("User " + u.getName() + " has been removed from database.");
	}
	public void addMovie(Movie mov){
		_movies.put(mov.get_id(), mov);
		logger.log("Movie " + mov.get_name() + " has added to movies database.");
	}
	//public void removeMovie(int i){
	//	_users.remove(i);
	//}
	public void removeMovie(Movie m){
		_users.remove(m);
		logger.log("Movie " + m.get_name() + " has been removed from database.");
	}

	private void upLoad(){
		_moviesHandler = new DataManipulate("movies");
		_usersHandler = new DataManipulate("users");
		_users = _usersHandler.getUsers();
		_usersIn = new HashMap<Integer, User>();
		_movies = _moviesHandler.getMovies();
		generateMoviesRaters();
		logger = Logger.makeSingleton("log.txt");
		System.out.println(logger);
		logger.log("System is up.");
	}
	private void generateMoviesRaters(){
		Integer tRaterId = null;
		HashMap<Integer, Integer> tUserRates = null;
		Iterator<Integer> userRateIter = null;
		Iterator<Integer> usersIter = _users.keySet().iterator();
		while(usersIter.hasNext()){
			tRaterId = usersIter.next();
			tUserRates = _users.get(tRaterId).get_rates();
			userRateIter = tUserRates.keySet().iterator();
			while (userRateIter.hasNext()){
				Integer nxt = userRateIter.next();
				Movie tMovie=_movies.get(nxt);
				tMovie.add_rater(tRaterId, _users.get(tRaterId).get_rates().get(nxt));
			}
		}
	}

	public void shutDown(){
		_usersHandler.setUsers(_users);
		_moviesHandler.setMovies(_movies);
		logger.log("System is down gracefully.");
		logger.exit();
		//logger = null;
	}

	/**
	 * login gets userName, password & id
	 * if user exists returns the user, otherwise returns null
	 */
	public void signUp(String name, String password,String permission,int id,String sex){
		User newUser = new User(password,permission,id,name,sex);
		this.addUser(newUser);
	}
	public User login(String password, String userName, int id){
		User tUser = _users.get(id);
		String message;
		if(tUser!=null && tUser.getId()==id &&
				tUser.getName().equals(userName) &&
				tUser.getPassword().equals(password)){
			message = "User " + userName + " has logged on.";
			_usersIn.put(tUser.getId(), tUser);					//add the user to the system
		}
		else{
			message = "Non-User " + userName + " has tried to login but failed.";
		}
		logger.log(message);
		return tUser;
	}
	public void logout(User user){
		_usersIn.remove(user.getId());
	}
	/**
	 * recieveRecommendations(random for now) returns randomly movies
	 * if there are no movies returns null
	 */
	public Movie recieveRecommendations(){
		if(!_movies.isEmpty()){
			int ran = (int) (Math.random()*_movies.size());
			return _movies.get(ran);
		}
		else {
			return null;
		}
	}

	public static void main(String[] args) {

		Controller c = new Controller();

		/* Initialize some users: */
		User u1 = new User("555","user" , 231 , "ido" , "male");
		u1.rateMovie(121, 3);
		u1.rateMovie(122, 4);
		c.addUser(u1);

		User u2 = new User("555","user" , 232 , "yotam" , "male");
		u2.rateMovie(121, 5);
		u2.rateMovie(122, 6);
		u2.rateMovie(123, 7);
		u2.rateMovie(124, 8);
		c.addUser(u2);

		/* Initialize some movies: */
		Vector<String> movieActors = new Vector<String>();
		movieActors.add("elpachino");
		movieActors.add("ofir nissel");
		Movie m1 = new Movie("DC1",121,"action",movieActors,1980,"is",1.5,"ivgi");
		m1.add_rater(231, 3);
		m1.add_rater(232, 5);
		c.addMovie(m1);

		Movie m2 = new Movie("DC2",122,"action",movieActors,1980,"is",1.5,"ivgi");
		m2.add_rater(231, 4);
		m2.add_rater(232, 6);
		c.addMovie(m2);

		Movie m3 = new Movie("DC3",123,"action",movieActors,1980,"is",1.5,"ivgi");
		m3.add_rater(232, 7);
		c.addMovie(m3);

		Movie m4 = new Movie("DC4",124,"action",movieActors,1980,"is",1.5,"ivgi");
		m4.add_rater(232, 8);
		c.addMovie(m4);

		//System.out.println(u1.getMeanRate());
		//System.out.println(c.get_matrixHandler().getK(u1.getId()));

		Iterator<Entry<Integer, User>> usersIter = c.get_users().entrySet().iterator();
		User tUser;
		while(usersIter.hasNext()) {
			tUser = usersIter.next().getValue();
			//System.out.println("weight("+u1.getId()+", "+tUser.getId()+") = "+
			//		c.get_matrixHandler().weight(u1.getId(), tUser.getId()));
			//System.out.println("normelizedRate: " + tUser.getNormelizedMovieRate(122));
		}

		//System.out.println(c.get_matrixHandler().getPredictedRate(231, 124));

		/*
		 * logger check
		 */
		c.login("555", "ido", 231);
		//System.out.println(c.get_usersIn());
		c.login("555", "yotam", 232);
		//System.out.println(c.get_usersIn());
		c.logout(u2);
		c.logout(u1);
		System.out.println(c.get_matrixHandler().get10Recomendations(231));
		c.shutDown();
	}



}
