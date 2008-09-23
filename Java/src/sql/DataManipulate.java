package sql;

import domain.Movie;
import domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Vector;

public class DataManipulate {

    private String _url;
    private String _user;
    private String _password;
    private Connection _conn;
    private Statement _stmt;
    private ResultSet _rs;
    private ResultSetMetaData _rsmd;

    // open connection to the database
    public DataManipulate() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        
        // for use in external DB (not on local computer)
        _url = "jdbc:mysql://localhost:8888/netnachle";
        _user = "netnachle";
        _password = ";tTl&I47";
        
        //add it:
        // for use in internal DB (on this computer using wamp)
        //_url = "jdbc:mysql://localhost/netnachle";
        //_user = "root";
        //_password = "";
    }
    // open the database
    private void openConnection() throws SQLException {
        _conn = DriverManager.getConnection(_url, _user, _password);
        _stmt = _conn.createStatement();
    }
    // close the database
    private void closeConnection() throws SQLException {
        _conn.close();
    }
    // execute sql command
    private void executeCommand(String query) throws SQLException {
        // String query = "Select * from  users";
        _rs = _stmt.executeQuery(query);
        _rsmd = _rs.getMetaData();
    }
    // get hashmap of users from database
    public HashMap<Integer, User> getUsers() throws SQLException {
        openConnection();
        
        HashMap<Integer, User> ansUsers = new HashMap<Integer, User>();
        executeCommand("select * from users");
        while (_rs.next()) {
            User tUser = new User(_rs.getString("password"),
                    _rs.getString("permission"),
                    _rs.getInt("id"),
                    _rs.getString("name"),
                    _rs.getString("sex"));
            ansUsers.put(tUser.getId(), tUser);
        }
        // set rates for this user
        Iterator<User> usersIter = ansUsers.values().iterator();
        User tUser;
        while (usersIter.hasNext()) {
            tUser = usersIter.next();
            tUser.set_rates(getRatesForMovies(tUser.getId()));
        }
        closeConnection();
        return ansUsers;
    }    
    // get actors that plays at movieId
    private Vector<String> getActors(int movieId) throws SQLException {
        Vector<String> ansActors = new Vector<String>();

        executeCommand("select * from actors where movieId = " + movieId);
        while (_rs.next()) {
            ansActors.add(_rs.getString("name"));
        }

        return ansActors;
    }
    // get hashmap of movies from database
    public HashMap<Integer, Movie> getMovies() throws SQLException {
        openConnection();
        
        HashMap<Integer, Movie> ansMovies = new HashMap<Integer, Movie>();
        executeCommand("select * from movies");
        while (_rs.next()) {
            Movie tMovie = new Movie(_rs.getString("name"),
                    _rs.getInt("id"),
                    _rs.getString("category"),
                    new Vector<String>(),
                    _rs.getInt("year"),
                    _rs.getString("country"),
                    _rs.getInt("duration"),
                    _rs.getString("director"));
            ansMovies.put(tMovie.get_id(), tMovie);
        }
        // set actors & raters for this Movie
        Iterator<Movie> moviesIter = ansMovies.values().iterator();
        Movie tMovie;
        while (moviesIter.hasNext()) {
            tMovie = moviesIter.next();
            tMovie.set_actors(getActors(tMovie.get_id()));
            tMovie.set_rates(getRatesForUsers(tMovie.get_id()));
        }
        
        closeConnection();
        
        return ansMovies;
    }    
    // get movieId/rate hashMap from database for userId
    private HashMap<Integer, Integer> getRatesForMovies(int userId) throws SQLException {
        HashMap<Integer, Integer> ansRates = new HashMap<Integer, Integer>();
        executeCommand("select movieId, rate from rates where userId = " + userId);
        while (_rs.next()) {
           ansRates.put(_rs.getInt("movieId"),_rs.getInt("rate"));
        }
        return ansRates;
    }
    // get userId/rate hashMap from database for movieId
    private HashMap<Integer, Integer> getRatesForUsers(int movieId) throws SQLException {
        HashMap<Integer, Integer> ansRates = new HashMap<Integer, Integer>();
        executeCommand("select userId, rate from rates where movieId = " + movieId);
        while (_rs.next()) {
           ansRates.put(_rs.getInt("userId"),_rs.getInt("rate"));
        }
        return ansRates;
    }
    // delete all tables for restarting the system (mostly for refresh the data)
    public void deleteAllTables() throws SQLException{
        openConnection();
        System.out.println("_stmt: "+_stmt);
        _stmt.executeUpdate("delete from users"); 
        System.out.println("_stmt: "+_stmt);
        _stmt.executeUpdate("delete from movies");
        _stmt.executeUpdate("delete from rates");
        _stmt.executeUpdate("delete from actors");
        closeConnection();
    }
    // insert user to users table and his movie rates to rates table
    private void insertUser(User user) throws SQLException{
        String query;
        query = "insert into users (id, name, password, permission, sex) " +
                "values('" + user.getId() + "','" + user.getName() + "','" +
                user.getPassword() + "','" + user.getPermission() + "','" + user.get_sex() + "')";
        _stmt.executeUpdate(query);
        
        // insert movie rates for this specific userId
        Iterator<Entry<Integer, Integer>> movieRatesIter = user.get_rates().entrySet().iterator();
        Entry<Integer, Integer> tEntry;
        int movieId;
        int rate;
        while (movieRatesIter.hasNext()) {
            tEntry = movieRatesIter.next();
            movieId = tEntry.getKey();
            rate = tEntry.getValue();
            query = "insert into rates (userId, movieId, rate) " +
                    "values('" + user.getId() + "','" + movieId + "','" + rate + "')";
            _stmt.executeUpdate(query);
        }
    }   
    // insert movie to movies table and all of his actors to actors table
    private void insertMovie(Movie movie) throws SQLException{
        openConnection();
        String query;
        query = "insert into movies (id, name, category, year, country, duration, director) " +
                "values('" + movie.get_id() + "','" + movie.get_name() + "','" +
                movie.get_category() + "','" + movie.get_year() + "','" + movie.get_country() + "','" +
                movie.get_duration() + "','" + movie.get_director() + "')";
        _stmt.executeUpdate(query);
        
        // insert actors for this specific movieId
        Vector<String> actors = movie.get_actors();
        for(int i=0;i<actors.size();i++){
            query = "insert into actors (movieId, name) " +
                    "values('" + movie.get_id() + "','" + actors.get(i) + "')";
            _stmt.executeUpdate(query);
        }
        closeConnection();
    }   
    // set the users hashMap to database
    public void setUsers(HashMap<Integer, User> users) throws SQLException {
        openConnection();
        Iterator<User> usersIter = users.values().iterator();
        User tUser;
        while (usersIter.hasNext()) {
            tUser = usersIter.next();
            insertUser(tUser);
        }
        closeConnection();
    }
    // set the movies hashMap to database
    public void setMovies(HashMap<Integer, Movie> movies) throws SQLException {
        openConnection();
        Iterator<Movie> moviesIter = movies.values().iterator();
        Movie tMovie;
        while (moviesIter.hasNext()) {
            tMovie = moviesIter.next();
            insertMovie(tMovie);
        }
        closeConnection();
    }
    
    public static void main(String[] args) {
        try {
            DataManipulate dm = new DataManipulate();
            
//            HashMap<Integer,Integer> tRates = new HashMap<Integer, Integer>();
//            tRates.put(1, 5);
//            tRates.put(2, 6);
//            User tUser = new User("bami", "user", 123, "bami", "male");
//            tUser.set_rates(tRates);
//            dm.insertUser(tUser);
            
            
            Vector<String> actors;
            Movie tMovie;
            
            for (int i=1;i<21;i++){
                actors = new Vector<String>();
                actors.add("actor1");
                actors.add("actor2");
                tMovie = new Movie("movieName"+i, i, "action", actors, 1985+i, "Israel", 1+(1/i), "Menachem Golan");
                dm.insertMovie(tMovie);
            }
            
            System.out.println(dm.getMovies());
            System.out.println(dm.getUsers());
            
            dm.closeConnection();
            
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    
}
