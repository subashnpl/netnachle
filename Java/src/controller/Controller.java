package controller;

import exceptions.NonUserException;//svn check
import java.util.HashMap;
import java.util.Iterator;
import domain.Movie;
import domain.User;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import sql.DataManipulate;

public class Controller {
//	private DataManipulate _moviesHandler = null;
//	private DataManipulate _usersHandler = null;
    public Strategy _strategy;
    private Logger logger;
    private HashMap<Integer, User> _users;
    private HashMap<Integer, Movie> _movies;
    private HashMap<Integer, User> _usersInSystem;  //this is a hash map that tell us who looged in
    private User _currentUser;
    private int _movRand;//oz1
    private Thread updater;
    private int _cycleInMinutes;
    private DataManipulate _dataHandler;

    public Controller(Strategy strategy) {
        try {
            _dataHandler = new DataManipulate();
            upLoad();
            strategy.set_users(_users);
            strategy.set_movies(_movies);
            _strategy = strategy;
            _movRand = -1;
            updater = null;
            _cycleInMinutes = 1;    // writes to DB every 1 minute
            //runUpdater();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Controller: SQLException", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Controller: ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
        }
    }//constructor

    private void runUpdater() {
        updater = new Thread(new Runnable() {

            public void run() {
                while (true) {
                    try {
                        Thread.sleep((int) (60000 * _cycleInMinutes));
                    } catch (InterruptedException ex) {
                        System.out.println("Error in sleep of thread");
                    }
                    
                    try {
                        writeMemoryToDataBase();
                    } catch (SQLException ex) {
                        if (_currentUser.getPermission().equalsIgnoreCase("administrator")) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Controller: SQLException", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    
                    System.out.println("Memory wrote to DB (Memory is written every " + _cycleInMinutes + " minutes).");
                }
            }
        });
        updater.start();
    }

    public void stopUpdater() {
        updater.interrupt();
    }

    public boolean lowRateUser() {
        return (_currentUser.get_rates().size() < 7);
    }

    public String[] getMovieNames() {
        String[] moviesNames = new String[this._movies.size()];
        Iterator iter = this.get_movies().values().iterator();
        Movie tMovie = null;
        for (int i = 0; i < moviesNames.length; i++) {
            tMovie = (Movie) iter.next();
            moviesNames[i] = tMovie.get_name();
        }
        return moviesNames;
    }

    public int[] getUsersIds() {
        int[] usersIds = new int[_users.size()];
        Iterator<User> iter = _users.values().iterator();
        for (int i = 0; i < usersIds.length; i++) {
            usersIds[i] = iter.next().getId();
        }
        return usersIds;
    }

    public boolean isUserExist(int id) {
        int[] usersIds = new int[_users.size()];
        Iterator<User> iter = _users.values().iterator();
        for (int i = 0; i < usersIds.length; i++) {
            if (iter.next().getId() == id) {
                return true;
            }
        }
        return false;
    }

    public String[] getUsersNamesById(int[] usersId) {
        String[] usersName = new String[usersId.length];
        for (int i = 0; i < usersId.length; i++) {
            usersName[i] = _users.get(usersId[i]).getName();
        }
        return usersName;
    }

    public int[] getMoviesIds() {
        int[] moviesIds = new int[_movies.size()];
        Iterator<Movie> iter = _movies.values().iterator();
        for (int i = 0; i < moviesIds.length; i++) {
            moviesIds[i] = iter.next().get_id();
        }
        return moviesIds;
    }

    public String[] getMoviesNamesById(int[] moviesId) {
        String[] moviesName = new String[moviesId.length];
        for (int i = 0; i < moviesId.length; i++) {
            moviesName[i] = _movies.get(moviesId[i]).get_name();  //  .getName();
        }
        return moviesName;
    }

    public void removeUser(int i) {
        logger.log("removed user  : " + _users.get(i));
        this.removeUser(_users.get(i));
    }

    public void removeMovie(int i) {
        logger.log("removed movie  : " + _movies.get(i));
        this.removeMovie(_movies.get(i));
    }
    /*
    /  return 7 movies randomly to be rate by the user   
     *///oz

    public String[][] getMoviesToRate(String for_back) {
        int fb = 0;
        if (for_back.equalsIgnoreCase("f")) {
            fb = 7;
        } else {
            fb = -7;
        }
        String[][] mov_director_id = new String[7][3];
        int upperLimit = this._movies.size();
        if (_movRand == -1) {//oz1  //if first time getting to rate movie
            _movRand = (int) ((double) upperLimit * Math.random());
        } else {
            _movRand = (_movRand + fb);
            if (_movRand < 0) {
                _movRand += upperLimit;
            }
            _movRand = _movRand % upperLimit;
        }//else

        Object[] movies2choos = _movies.values().toArray();
        Movie tMov = null;
        for (int i = 0; i < 7; i++) {
            tMov = (Movie) movies2choos[(i + _movRand) % upperLimit];
            mov_director_id[i][0] = tMov.get_name();
            mov_director_id[i][1] = tMov.get_director();
            mov_director_id[i][2] = tMov.get_id() + "";
        } //while(!doneRandMovies){
        return mov_director_id;
    }//getMoviesToRate
    
    public Vector<Movie> getMoviesToRateShai(String for_back){
        Vector<Movie> ans = new Vector<Movie>();
        int fb = 0;
        if (for_back.equalsIgnoreCase("f")) {
            fb = 7;
        } else {
            fb = -7;
        }
        String[][] mov_director_id = new String[7][3];
        int upperLimit = this._movies.size();
        if (_movRand == -1) {//oz1  //if first time getting to rate movie
            _movRand = (int) ((double) upperLimit * Math.random());
        } else {
            _movRand = (_movRand + fb);
            if (_movRand < 0) {
                _movRand += upperLimit;
            }
            _movRand = _movRand % upperLimit;
        }//else

        
        Object[] movies2choos = _movies.values().toArray();
        Movie tMov = null;
        for (int i = 0; i < 7; i++) {
            tMov = (Movie) movies2choos[(i + _movRand) % upperLimit];
            ans.addElement(tMov);
        } //while(!doneRandMovies){
        return ans;
    }
    
    public int userSaw(Integer movId) {//oz1
        int ret = 0;
        Integer saw = getCurrentUser().get_rates().get(movId);
        if (saw != null) {
            ret = saw.intValue();
        }
        return ret;
    }

public void setRatesByUser(int[] moviesId, int[] rates, int userID) {
        User tUser = _users.get(userID);
        for (int i = 0; i < rates.length; i++) {
            if (rates[i] != 0){
                tUser.rateMovie(moviesId[i], rates[i]);
                _movies.get(moviesId[i]).add_rater(userID, rates[i]);
            }else{
                if (tUser.getMovieRate(moviesId[i]) != 0){
                    tUser.removeRater(moviesId[i]);
                    _movies.get(new Integer(moviesId[i])).removeRater(tUser.getId());
                }
            }
        }//for
    }//void setRatesByUser  

    public String[] getUsersNames() {
        String[] usersNames = new String[this._users.size()];
        Iterator iter = this.get_users().values().iterator();
        User tUser = null;
        for (int i = 0; i < usersNames.length; i++) {
            tUser = (User) iter.next();
            usersNames[i] = tUser.getName();
        }
        return usersNames;
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

    public void addUser(User usr) {
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
    public void removeUser(User u) {
        Iterator<Integer> ratesIter = u.get_rates().keySet().iterator();
        while (ratesIter.hasNext()) {
            _movies.get(ratesIter.next()).removeRater(u.getId());
        }
        _users.remove(u.getId());
        logger.log("User " + u.getName() + " has been removed from database.");
    }

    public void addMovie(Movie mov) {
        _movies.put(mov.get_id(), mov);
        logger.log("Movie " + mov.get_name() + " has added to movies database.");
    }
    //public void removeMovie(int i){
    //	_users.remove(i);
    //}
    public void removeMovie(Movie m) {

        Iterator<Integer> ratesIter = m.get_rates().keySet().iterator();
        while (ratesIter.hasNext()) {
            _users.get(ratesIter.next()).removeRater(m.get_id());
        }
        _movies.remove(m.get_id());
        logger.log("Movie " + m.get_name() + " has been removed from database.");
    }

    private void upLoad() throws SQLException {
        _users = _dataHandler.getUsers();
        _movies = _dataHandler.getMovies();
        logger = Logger.makeSingleton("log.txt");
        logger.log("System is up.");
    }

    public void logout(){
        logger.log("User " + _currentUser.getName() + " has logged out.");
        try {
            writeMemoryToDataBase();
        } catch (SQLException ex) {
            logger.log("System's memory was NOT written to DB (Reason: SQLException).");
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "shutdown: SQLException", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void shutDown() {
        try {
            writeMemoryToDataBase();
            logger.log("System is down gracefully.");
        } catch (SQLException ex) {
            logger.log("System's memory was NOT written to DB (Reason: SQLException).");
            logger.log("System is down gracefully (But without writing to DB).");
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "shutdown: SQLException", JOptionPane.ERROR_MESSAGE);
        } finally {
            logger.exit();
        }
    }

    public void writeMemoryToDataBase() throws SQLException {
        try {
            _dataHandler.deleteAllTables();
        } catch (SQLException sQLException) {
            logger.log("System's memory was NOT written to DB. Can't delete tables.");
            throw sQLException;
        }
        try {
            _dataHandler.setUsers(_users);
        } catch (SQLException sQLException) {
            logger.log("System's memory was NOT written to DB. Can't write users to DB");
            throw sQLException;
        }
        try {
            _dataHandler.setMovies(_movies);
        } catch (SQLException sQLException) {
            logger.log("System's memory was NOT written to DB. Can't write movies to DB");
            throw sQLException;
        }
        logger.log("System's memory was written to DB.");
    }

    /**
     * login gets userName, password & id
     * if user exists returns the user, otherwise returns null
     */
    public void signUp(String name, String password, String permission, int id, String sex) {
        User newUser = new User(password, permission, id, name, sex);
        this.addUser(newUser);
    }

    public User login(String password, String userName, int id) throws Exception {
        User tUser = _users.get(id);
        String message;
        if (tUser != null && tUser.getId() == id &&
                tUser.getName().equals(userName) &&
                tUser.getPassword().equals(password)) {
            message = "User " + userName + " has logged on.";
            _currentUser = tUser;
            logger.log(message);
        } else {
            tUser = null;
            message = "Non-User " + userName + " has tried to login but failed.";
            logger.log(message);
            throw new NonUserException("No such user: " + userName + "\n- OR -\nPassword doesn't match");
        }
        return tUser;
    }

    public void logout(User user) {
        _usersInSystem.remove(user.getId());
    }

    /**
     * recieveRecommendations(random for now) returns randomly movies
     * if there are no movies returns null
     */
    public Movie recieveRecommendations() {
        if (!_movies.isEmpty()) {
            int ran = (int) (Math.random() * _movies.size());
            return _movies.get(ran);
        } else {
            return null;
        }
    }

    public User getCurrentUser() {
        return _currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this._currentUser = currentUser;
    }

    public void initDataBase(){
        int answer = JOptionPane.showConfirmDialog(null, "init DB?");
        if (answer == 0){
           _users.clear();
           _movies.clear();
            try {
                this._dataHandler.deleteAllTables();
            } catch (SQLException ex) {
                System.out.println("Exception caught1");
            }
            User admin = new User("YWJj", "administrator", 111111111, "admin", "male");
            addUser(admin);
            //adding some movies:
            Vector<String> movieActors1 = new Vector<String>();
            movieActors1.add("Yehuda Barkan");
            Movie m1 = new Movie("ima ganuv1", 100, "action", movieActors1, 1980, "Israel", 189, "Yehuda Barkan");
            addMovie(m1);

            movieActors1 = new Vector<String>();
            movieActors1.add("Stalon");
            Movie m2 = new Movie("Rambo1", 101, "action", movieActors1, 1981, "USA", 112, "Michael");
            addMovie(m2);

            movieActors1 = new Vector<String>();
            movieActors1.add("Uri Zohar");
            movieActors1.add("Arik Ainstein");
            Movie m3 = new Movie("Matzizim", 102, "Comedy", movieActors1, 1984, "Israel", 134, "Uri Zohar");
            addMovie(m3);

            movieActors1 = new Vector<String>();
            movieActors1.add("Kiano Rives");
            Movie m4 = new Movie("Matrix1", 103, "Action", movieActors1, 1999, "USA", 126, "Jordan");
            m1.add_rater(101, 1);
            addMovie(m4);

            movieActors1 = new Vector<String>();
            movieActors1.add("Kiano Rives");
            Movie m5 = new Movie("Matrix2", 104, "Action", movieActors1, 2001, "USA", 106, "Jordan");
            addMovie(m5);

            movieActors1 = new Vector<String>();
            movieActors1.add("Kiano Rives");
            Movie m6 = new Movie("Matrix3", 105, "Action", movieActors1, 2003, "USA", 177, "Jordan");
            addMovie(m6);

            movieActors1 = new Vector<String>();
            movieActors1.add("Stalon");
            Movie m7 = new Movie("Rambo2", 106, "action", movieActors1, 1981, "USA", 155, "Michael");
            addMovie(m7);

            movieActors1 = new Vector<String>();
            movieActors1.add("Stalon");
            Movie m8 = new Movie("Rambo3", 107, "action", movieActors1, 1983, "USA", 137, "Michael");
            addMovie(m8);

            movieActors1 = new Vector<String>();
            movieActors1.add("Katzman");
            Movie m9 = new Movie("Net-Nachle", 108, "Fiction", movieActors1, 2003, "USA", 17, "BGU");
            addMovie(m9);

            movieActors1 = new Vector<String>();
            movieActors1.add("Katzman");
            Movie m10 = new Movie("Net-Nachle - the coming back", 109, "Fiction", movieActors1, 2003, "USA", 147, "BGU");
            addMovie(m10);
        }
        try {
            this._dataHandler.deleteAllTables();
            this._dataHandler.setMovies(_movies);
            this._dataHandler.setUsers(_users);
        } catch (SQLException ex) {
            System.out.println("Exception caught2: "+ex.getMessage());
        }      
    }
//    public static void main(String[] args) {
//        Controller c = new Controller(new MatrixHandler());
//        
//        Vector<String> movieActors1 = new Vector<String>();
//        movieActors1.add("Yehuda Barkan");
//        Movie m1 = new Movie("ima ganuv1", 100, "action", movieActors1, 1980, "Israel", 189, "Yehuda Barkan");
//        c.addMovie(m1);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("Stalon");
//        Movie m2 = new Movie("Rambo1", 101, "action", movieActors1, 1981, "USA", 112, "Michael");
//        c.addMovie(m2);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("Uri Zohar");
//        movieActors1.add("Arik Ainstein");
//        Movie m3 = new Movie("Matzizim", 102, "Comedy", movieActors1, 1984, "Israel", 134, "Uri Zohar");
//        c.addMovie(m3);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("Kiano Rives");
//        Movie m4 = new Movie("Matrix1", 103, "Action", movieActors1, 1999, "USA", 126, "Jordan");
//        m1.add_rater(101, 1);
//        c.addMovie(m4);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("Kiano Rives");
//        Movie m5 = new Movie("Matrix2", 104, "Action", movieActors1, 2001, "USA", 106, "Jordan");
//        c.addMovie(m5);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("Kiano Rives");
//        Movie m6 = new Movie("Matrix3", 105, "Action", movieActors1, 2003, "USA", 177, "Jordan");
//        c.addMovie(m6);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("Stalon");
//        Movie m7 = new Movie("Rambo2", 106, "action", movieActors1, 1981, "USA", 155, "Michael");
//        c.addMovie(m7);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("Stalon");
//        Movie m8 = new Movie("Rambo3", 107, "action", movieActors1, 1983, "USA", 137, "Michael");
//        c.addMovie(m8);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("Katzman");
//        Movie m9 = new Movie("Net-Nachle", 108, "Fiction", movieActors1, 2003, "USA", 17, "BGU");
//        c.addMovie(m9);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("Katzman");
//        Movie m10 = new Movie("Net-Nachle - the coming back", 109, "Fiction", movieActors1, 2003, "USA", 147, "BGU");
//        c.addMovie(m10);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("Hobite");
//        Movie m11 = new Movie("Lord of the rings1", 110, "Fiction", movieActors1, 2002, "USA", 104, "Roy");
//        c.addMovie(m11);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("Hobite");
//        Movie m12 = new Movie("Lord of the rings2", 111, "Fiction", movieActors1, 2003, "USA", 144, "Roy");
//        c.addMovie(m12);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("Hobite");
//        Movie m13 = new Movie("Lord of the rings3", 112, "Fiction", movieActors1, 2005, "USA", 144, "Roy");
//        c.addMovie(m13);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("Katash");
//        Movie m14 = new Movie("Rockie1", 113, "action", movieActors1, 2004, "USA", 184, "Roy");
//        c.addMovie(m14);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("jony");
//        Movie m15 = new Movie("Rockie2", 114, "action", movieActors1, 2005, "USA", 174, "Roy");
//        c.addMovie(m15);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("vanbasten");
//        Movie m16 = new Movie("Rockie3", 115, "action", movieActors1, 2006, "USA", 114, "Roy");
//        c.addMovie(m16);
//
//        movieActors1 = new Vector<String>();
//        movieActors1.add("rodman");
//        Movie m17 = new Movie("Robokop", 116, "action", movieActors1, 1850, "USA", 164, "Roy");
//        c.addMovie(m17);
//
//        User u1 = new User("123123123", "user", 100, "ido", "male");
//        u1.rateMovie(100, 4);
//        u1.rateMovie(101, 5);
//        u1.rateMovie(102, 6);
//        u1.rateMovie(103, 7);
//        c.addUser(u1);
//
//        User u2 = new User("124124124", "user", 101, "Elad", "male");
//        u2.rateMovie(100, 4);
//        u2.rateMovie(101, 5);
//        u2.rateMovie(102, 6);
//        u2.rateMovie(103, 7);
//        u2.rateMovie(104, 4);
//        u2.rateMovie(105, 1);
//        u2.rateMovie(106, 3);
//        u2.rateMovie(107, 9);
//        u2.rateMovie(108, 9);
//        u2.rateMovie(109, 8);
//        u2.rateMovie(110, 7);
//        u2.rateMovie(111, 2);
//        u2.rateMovie(112, 5);
//        u2.rateMovie(113, 4);
//        u2.rateMovie(114, 7);
//        c.addUser(u2);
//        User admin = new User("YWJj", "administrator", 111111111, "admin", "male");
//        admin.rateMovie(100, 4);
//        admin.rateMovie(101, 5);
//        admin.rateMovie(102, 6);
//        admin.rateMovie(103, 7);
//        admin.rateMovie(104, 9);
//        admin.rateMovie(105, 8);
//        admin.rateMovie(106, 7);
//        admin.rateMovie(107, 6);
//        admin.rateMovie(108, 5);
//        admin.rateMovie(109, 4);
//        admin.rateMovie(110, 3);
//        admin.rateMovie(111, 2);
//        admin.rateMovie(112, 1);
//        admin.rateMovie(113, 2);
//        admin.rateMovie(114, 1);
//        c.addUser(admin);
//        
//
//        c.shutDown();
//    }

    public int getCycleInMinutes() {
        return _cycleInMinutes;
    }

    public void setCycleInMinutes(int cycleInMinutes) {
        if (cycleInMinutes > 0.0) {
            this._cycleInMinutes = cycleInMinutes;
        }
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