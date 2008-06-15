package domain.controller;

import Exceptions.NonUserException;
import java.util.HashMap;
import java.util.Iterator;
import domain.Movie;
import domain.User;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Controller {
	private DataManipulate _moviesHandler = null;
	private DataManipulate _usersHandler = null;
        public Strategy _strategy;
	private Logger logger;
	private HashMap<Integer, User> _users;
	private HashMap<Integer, Movie> _movies;
	private HashMap<Integer, User> _usersInSystem;  //this is a hash map that tell us who looged in
        private User _currentUser;
        private int _movRand;//oz1
        
	public Controller(Strategy strategy){
		upLoad();
                strategy.set_users(_users);
                strategy.set_movies(_movies);
		_strategy = strategy;
                _movRand = -1;
	}//constructor
        
        public String[]  getMovieNames() {
            String[] moviesNames=new String[this._movies.size()];
            Iterator iter=this.get_movies().values().iterator();
            Movie tMovie=null;
            for (int i=0 ;i< moviesNames.length;i++){
                tMovie = (Movie)iter.next();
                moviesNames[i]=tMovie.get_name() ;
            }
            return moviesNames;
        }

    public int[] getUsersIds() {
        int[] usersIds=new int[_users.size()];
        Iterator<User> iter=_users.values().iterator();
        for(int i=0;i<usersIds.length;i++){
            usersIds[i]=iter.next().getId();
        }
        return usersIds;
    }

    public String[] getUsersNamesById(int[] usersId) {
        String[] usersName=new String[usersId.length];
        for(int i=0; i<usersId.length;i++){
            usersName[i]=_users.get(usersId[i]).getName();
        }
        return usersName;
    }
    public int[] getMoviesIds() {
        int[] moviesIds=new int[_movies.size()];
        Iterator<Movie> iter=_movies.values().iterator();
        for(int i=0;i<moviesIds.length;i++){
            moviesIds[i]=iter.next().get_id();
        }
        return moviesIds;
    }
    public String[] getMoviesNamesById(int[] moviesId) {
        String[] moviesName=new String[moviesId.length];
        for(int i=0; i<moviesId.length;i++){
            moviesName[i]=_movies.get(moviesId[i]).get_name();  //  .getName();
        }
        return moviesName;
    }
    public DataManipulate get_movieHandler() {
        return _moviesHandler;
    }
    public void removeUser(int i) {
        logger.log("remove user  : " + _users.get(i));
        this.removeUser(_users.get(i));
    }
    
    public void removeMovie(int i) {
        logger.log("remove movie  : " + _movies.get(i));
        this.removeMovie(_movies.get(i));
    }
    /*
     /  return 7 movies randomly to be rate by the user   
    *///oz
    public String[][] getMoviesToRate(String for_back) {
        int fb=0;
        if (for_back.equalsIgnoreCase("f")) fb=7;
        else fb=-7;
        String[][]  mov_director_id = new String[7][3];
        int upperLimit=this._movies.size();
        if (_movRand == -1){//oz1  //if first time getting to rate movie
            _movRand = (int)((double)upperLimit * Math.random() );
        }else{
            _movRand = (_movRand + fb);
            if (_movRand<0) _movRand += upperLimit;
            _movRand = _movRand % upperLimit;
        }//else
        
        Object[]  movies2choos = _movies.values().toArray();
        Movie tMov=null;
        for(int i=0;i<7;i++){
            tMov = (Movie)movies2choos[(i+_movRand) % upperLimit];
            mov_director_id[i][0] =tMov.get_name();
            mov_director_id[i][1] = tMov.get_director();
            mov_director_id[i][2] = tMov.get_id()+"";
            System.out.println(mov_director_id[i][2]);
        } //while(!doneRandMovies){
        return mov_director_id;
    }//getMoviesToRate
    
    public int userSaw(Integer movId) {//oz1
        int ret=0;
        Integer saw = getCurrentUser().get_rates().get(movId);
        if (saw!=null) ret = saw.intValue();
        return ret;
    }    
    
    public void setRatesByUser(int[] moviesId, int[] rates, int userID) {
       User tUser=_users.get(userID);
        for (int i=0;i<rates.length;i++){
            tUser.rateMovie(moviesId[i], rates[i]);
            _movies.get(moviesId[i]).add_rater(userID, rates[i]);
        }//for
    }//void setRatesByUser  
    public String[]  getUsersNames() {
        String[] usersNames=new String[this._users.size()];
        Iterator iter=this.get_users().values().iterator();
        User tUser=null;
        for (int i=0 ;i< usersNames.length;i++){
            tUser=(User)iter.next();
            usersNames[i]=tUser.getName();
        }
        return usersNames;
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
            return _usersInSystem;
    }

    public void set_usersIn(HashMap<Integer, User> in) {
            _usersInSystem = in;
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
    public Strategy get_Strategy() {
            return _strategy;
    }

    public void set_Strategy(Strategy strategy) {
            _strategy = strategy;
    }
    //public void removeUser(int i){
    //	_users.remove(i);
    public void removeUser(User u){
            Iterator<Integer> ratesIter = u.get_rates().keySet().iterator();
            while(ratesIter.hasNext()){
                _movies.get(ratesIter.next()).removeRater(u.getId());
            }
            _users.remove(u.getId());
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
        
        Iterator<Integer> ratesIter = m.get_rates().keySet().iterator();
        while(ratesIter.hasNext()){
            _users.get(ratesIter.next()).removeRater(m.get_id());
        }
            _movies.remove(m.get_id());
            logger.log("Movie " + m.get_name() + " has been removed from database.");
    }

    private void upLoad(){
        try {
            _moviesHandler = new DataManipulate("movies");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Movies File Error", JOptionPane.ERROR_MESSAGE);
        }
        try{
            _usersHandler = new DataManipulate("users");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Users File Error", JOptionPane.ERROR_MESSAGE);
        }
        
        _users = _usersHandler.getUsers();
        _usersInSystem = new HashMap<Integer, User>();
        _movies = _moviesHandler.getMovies();
        generateMoviesRaters();
        logger = Logger.makeSingleton("log.txt");
        logger.log("System is up.");
    }

    // Updating movies' rates according to rates that users rated that were read from the users.xml file
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
    }

    /**
     * login gets userName, password & id
     * if user exists returns the user, otherwise returns null
     */
    public void signUp(String name, String password,String permission,int id,String sex){
            User newUser = new User(password,permission,id,name,sex);
            this.addUser(newUser);
    }
    public User login(String password, String userName, int id) throws Exception{
            User tUser = _users.get(id);
            String message;
            if(tUser!=null && tUser.getId()==id &&
                            tUser.getName().equals(userName) &&
                            tUser.getPassword().equals(password)){
                    message = "User " + userName + " has logged on.";
                    logger.log(message);
                    _usersInSystem.put(tUser.getId(), tUser);	//add the user to the system                        
            }
            else{
                tUser = null;
                message = "Non-User " + userName + " has tried to login but failed.";
                logger.log(message);
                throw new NonUserException("No such user: " + userName +"\n- OR -\nPassword doesn't match");
            }
            return tUser;
    }
    public void logout(User user){
            _usersInSystem.remove(user.getId());
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

    public User getCurrentUser() {
        return _currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this._currentUser = currentUser;
    }

	public static void main(String[] args) {
	    Controller c = new Controller(new MatrixHandler());
	    /* Example that works*/

	    /* Initialize some movies: */
	    Vector<String> movieActors1 = new Vector<String>();
	    movieActors1.add("Yehuda Barkan");
	    Movie m1 = new Movie("ima ganuv1",100,"action",movieActors1,1980,"Israel",1.5,"Yehuda Barkan");
	    c.addMovie(m1);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("Stalon");
	    Movie m2 = new Movie("Rambo1",101,"action",movieActors1,1981,"USA",1.7,"Michael");
	    c.addMovie(m2);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("Uri Zohar");
	    movieActors1.add("Arik Ainstein");
	    Movie m3 = new Movie("Matzizim",102,"Comedy",movieActors1,1984,"Israel",1.7,"Uri Zohar");
	    c.addMovie(m3);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("Kiano Rives");
	    Movie m4 = new Movie("Matrix1",103,"Action",movieActors1,1999,"USA",1.6,"Jordan");
	    m1.add_rater(101, 1);
	    c.addMovie(m4);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("Kiano Rives");
	    Movie m5 = new Movie("Matrix2",104,"Action",movieActors1,2001,"USA",1.6,"Jordan");
	    c.addMovie(m5);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("Kiano Rives");
	    Movie m6 = new Movie("Matrix3",105,"Action",movieActors1,2003,"USA",1.7,"Jordan");
	    c.addMovie(m6);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("Stalon");
	    Movie m7 = new Movie("Rambo2",106,"action",movieActors1,1981,"USA",1.5,"Michael");
	    c.addMovie(m7);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("Stalon");
	    Movie m8 = new Movie("Rambo3",107,"action",movieActors1,1983,"USA",1.7,"Michael");
	    c.addMovie(m8);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("Katzman");
	    Movie m9 = new Movie("Net-Nachle",108,"Fiction",movieActors1,2003,"USA",1.7,"BGU");
	    c.addMovie(m9);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("Katzman");
	    Movie m10 = new Movie("Net-Nachle - the coming back",109,"Fiction",movieActors1,2003,"USA",1.7,"BGU");
	    c.addMovie(m10);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("Hobite");
	    Movie m11 = new Movie("Lord of the rings1",110,"Fiction",movieActors1,2002,"USA",1.4,"Roy");
	    c.addMovie(m11);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("Hobite");
	    Movie m12 = new Movie("Lord of the rings2",111,"Fiction",movieActors1,2003,"USA",1.4,"Roy");
	    c.addMovie(m12);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("Hobite");
	    Movie m13 = new Movie("Lord of the rings3",112,"Fiction",movieActors1,2005,"USA",1.4,"Roy");
	    c.addMovie(m13);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("Katash");
	    Movie m14 = new Movie("Rockie1",113,"action",movieActors1,2004,"USA",1.4,"Roy");
	    c.addMovie(m14);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("jony");
	    Movie m15 = new Movie("Rockie2",114,"action",movieActors1,2005,"USA",1.4,"Roy");
	    c.addMovie(m15);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("vanbasten");
	    Movie m16 = new Movie("Rockie3",115,"action",movieActors1,2006,"USA",1.4,"Roy");
	    c.addMovie(m16);
	    
	    movieActors1 = new Vector<String>();
	    movieActors1.add("rodman");
	    Movie m17 = new Movie("Robokop",116,"action",movieActors1,1850,"USA",1.4,"Roy");
	    c.addMovie(m17);

	    User u1 = new User("123","user" , 100 , "ido" , "male");
	    u1.rateMovie(100, 4);
	    u1.rateMovie(101, 5);
	    u1.rateMovie(102, 6);
	    u1.rateMovie(103, 7);
	    c.addUser(u1);
	    
	    User u2 = new User("124","user" , 101 , "Elad" , "male");
	    u2.rateMovie(100, 4);
	    u2.rateMovie(101, 5);
	    u2.rateMovie(102, 6);
	    u2.rateMovie(103, 7);
	    u2.rateMovie(104, 4);
	    u2.rateMovie(105, 1);
	    u2.rateMovie(106, 3);
	    u2.rateMovie(107, 9);
	    u2.rateMovie(108, 9);
	    u2.rateMovie(109, 8);
	    u2.rateMovie(110, 7);
	    u2.rateMovie(111, 2);
	    u2.rateMovie(112, 5);
	    u2.rateMovie(113, 4);
	    u2.rateMovie(114, 7);
	    c.addUser(u2);
	    
	    User u3 = new User("125","user" , 102 , "sami" , "male");
	    u3.rateMovie(100, 4);
	    u3.rateMovie(101, 5);
	    u3.rateMovie(102, 6);
	    u3.rateMovie(103, 7);
	    u3.rateMovie(104, 9);
	    u3.rateMovie(105, 8);
	    u3.rateMovie(106, 7);
	    u3.rateMovie(107, 6);
	    u3.rateMovie(108, 5);
	    u3.rateMovie(109, 4);
	    u3.rateMovie(110, 3);
	    u3.rateMovie(111, 2);
	    u3.rateMovie(112, 1);
	    u3.rateMovie(113, 2);
	    u3.rateMovie(114, 1);
	    c.addUser(u3);
		System.out.println(c.get_Strategy().get10Recomendations(100));
	    c.shutDown();
	}
}


/*******************************************************************************/
/* Example that works*/

/* Initialize some movies: */
//Vector<String> movieActors1 = new Vector<String>();
//movieActors1.add("Yehuda Barkan");
//Movie m1 = new Movie("ima ganuv1",100,"action",movieActors1,1980,"Israel",1.5,"Yehuda Barkan");
//c.addMovie(m1);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("Stalon");
//Movie m2 = new Movie("Rambo1",101,"action",movieActors1,1981,"USA",1.7,"Michael");
//c.addMovie(m2);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("Uri Zohar");
//movieActors1.add("Arik Ainstein");
//Movie m3 = new Movie("Matzizim",102,"Comedy",movieActors1,1984,"Israel",1.7,"Uri Zohar");
//c.addMovie(m3);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("Kiano Rives");
//Movie m4 = new Movie("Matrix1",103,"Action",movieActors1,1999,"USA",1.6,"Jordan");
//m1.add_rater(101, 1);
//c.addMovie(m4);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("Kiano Rives");
//Movie m5 = new Movie("Matrix2",104,"Action",movieActors1,2001,"USA",1.6,"Jordan");
//c.addMovie(m5);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("Kiano Rives");
//Movie m6 = new Movie("Matrix3",105,"Action",movieActors1,2003,"USA",1.7,"Jordan");
//c.addMovie(m6);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("Stalon");
//Movie m7 = new Movie("Rambo2",106,"action",movieActors1,1981,"USA",1.5,"Michael");
//c.addMovie(m7);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("Stalon");
//Movie m8 = new Movie("Rambo3",107,"action",movieActors1,1983,"USA",1.7,"Michael");
//c.addMovie(m8);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("Katzman");
//Movie m9 = new Movie("Net-Nachle",108,"Fiction",movieActors1,2003,"USA",1.7,"BGU");
//c.addMovie(m9);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("Katzman");
//Movie m10 = new Movie("Net-Nachle - the coming back",109,"Fiction",movieActors1,2003,"USA",1.7,"BGU");
//c.addMovie(m10);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("Hobite");
//Movie m11 = new Movie("Lord of the rings1",110,"Fiction",movieActors1,2002,"USA",1.4,"Roy");
//c.addMovie(m11);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("Hobite");
//Movie m12 = new Movie("Lord of the rings2",111,"Fiction",movieActors1,2003,"USA",1.4,"Roy");
//c.addMovie(m12);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("Hobite");
//Movie m13 = new Movie("Lord of the rings3",112,"Fiction",movieActors1,2005,"USA",1.4,"Roy");
//c.addMovie(m13);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("Katash");
//Movie m14 = new Movie("Rockie1",113,"action",movieActors1,2004,"USA",1.4,"Roy");
//c.addMovie(m14);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("jony");
//Movie m15 = new Movie("Rockie2",114,"action",movieActors1,2005,"USA",1.4,"Roy");
//c.addMovie(m15);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("vanbasten");
//Movie m16 = new Movie("Rockie3",115,"action",movieActors1,2006,"USA",1.4,"Roy");
//c.addMovie(m16);
//
//movieActors1 = new Vector<String>();
//movieActors1.add("rodman");
//Movie m17 = new Movie("Robokop",116,"action",movieActors1,1850,"USA",1.4,"Roy");
//c.addMovie(m17);

//User u1 = new User("123","user" , 100 , "ido" , "male");
//u1.rateMovie(100, 4);
//u1.rateMovie(101, 5);
//u1.rateMovie(102, 6);
//u1.rateMovie(103, 7);
//c.addUser(u1);
//
//User u2 = new User("124","user" , 101 , "Elad" , "male");
//u2.rateMovie(100, 4);
//u2.rateMovie(101, 5);
//u2.rateMovie(102, 6);
//u2.rateMovie(103, 7);
//u2.rateMovie(104, 4);
//u2.rateMovie(105, 1);
//u2.rateMovie(106, 3);
//u2.rateMovie(107, 9);
//u2.rateMovie(108, 9);
//u2.rateMovie(109, 8);
//u2.rateMovie(110, 7);
//u2.rateMovie(111, 2);
//u2.rateMovie(112, 5);
//u2.rateMovie(113, 4);
//u2.rateMovie(114, 7);
//c.addUser(u2);
//
//User u3 = new User("125","user" , 102 , "sami" , "male");
//u3.rateMovie(100, 4);
//u3.rateMovie(101, 5);
//u3.rateMovie(102, 6);
//u3.rateMovie(103, 7);
//u3.rateMovie(104, 9);
//u3.rateMovie(105, 8);
//u3.rateMovie(106, 7);
//u3.rateMovie(107, 6);
//u3.rateMovie(108, 5);
//u3.rateMovie(109, 4);
//u3.rateMovie(110, 3);
//u3.rateMovie(111, 2);
//u3.rateMovie(112, 1);
//u3.rateMovie(113, 2);
//u3.rateMovie(114, 1);
//c.addUser(u3);

/************************ to be checked ****************************************/
//User u1 = new User("123"/*"QL0AFWMIX8NRZTI/oT9cXss/vu8"*/,"user" , 100 , "ido" , "male");
//u1.rateMovie(100, 4);
//u1.rateMovie(101, 5);
//u1.rateMovie(102, 6);
//u1.rateMovie(106, 7);
//c.addUser(u1);
//
//User u2 = new User("124","user" , 101 , "Elad" , "male");
//u2.rateMovie(100, 4);
//u2.rateMovie(101, 5);
//u2.rateMovie(102, 6);
//u2.rateMovie(103, 4);
//u2.rateMovie(104, 4);
//u2.rateMovie(105, 1);
//u2.rateMovie(106, 3);
//u2.rateMovie(107, 9);
//u2.rateMovie(108, 9);
//u2.rateMovie(109, 8);
//u2.rateMovie(110, 7);
//u2.rateMovie(111, 2);
//u2.rateMovie(112, 5);
//u2.rateMovie(113, 4);
//u2.rateMovie(114, 7);
//c.addUser(u2);
//
//User u3 = new User("125","user" , 102 , "Shai" , "male");
//u3.rateMovie(107, 4);
//u3.rateMovie(101, 5);
//u3.rateMovie(105, 1);
//u3.rateMovie(103, 1);
//c.addUser(u3);
//
//User u4 = new User("126","user" , 103 , "oz" , "male");
//u4.rateMovie(107, 4);
//u4.rateMovie(101, 2);
//u4.rateMovie(105, 1);
//u4.rateMovie(103, 4);
//c.addUser(u4);
//
//User u5 = new User("126","user" , 104 , "ofir" , "male");
//c.addUser(u5);
//
//User u6 = new User("127","user" , 105 , "Yotam" , "male");
//c.addUser(u6);
//
//User u7 = new User("128","user" , 106 , "jon" , "female");
//c.addUser(u7);
//
//User u8 = new User("129","user" , 107 , "yoni" , "female");
//c.addUser(u8);
//
//User u9 = new User("130","user" , 108 , "idan" , "female");
//c.addUser(u9);
//
//User u10 = new User("131","user" , 110 , "yamit" , "female");
//c.addUser(u10);
//
//User u11 = new User("127","user" , 111 , "dafna" , "female");
//c.addUser(u11);