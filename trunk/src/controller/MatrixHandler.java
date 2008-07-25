package controller;

import controller.Strategy;
import exceptions1.NoRateException;
import exceptions1.RateNotAtRangeException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;
import java.util.Map.Entry;

import domain.Movie;
import domain.User;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatrixHandler implements Strategy{

	private HashMap<Integer,User> _users;
	private HashMap<Integer,Movie> _movies;

	public HashMap<Integer, User> get_users() {
		return _users;
	}

	public void set_users(HashMap<Integer, User> _users) {
		this._users = _users;
	}

	public HashMap<Integer, Movie> get_movies() {
		return _movies;
	}

	public void set_movies(HashMap<Integer, Movie> _movies) {
		this._movies = _movies;
	}

	public double weight(Integer currUserId, Integer otherUserId) {
		double tNumer = 0;
		double tDenom = 0;
		double tDenom1 = 0;
		double tDenom2 = 0;
		Iterator<Entry<Integer, Integer>> moviesIter = _users.get(currUserId).get_rates().entrySet().iterator();
		Movie tMovie;
                User currUser;
                User otherUser;
                
		while(moviesIter.hasNext()) {
			tMovie = _movies.get(moviesIter.next().getKey());
			if (tMovie.get_rates().containsKey(otherUserId)){
                            try {
                                currUser =  _users.get(currUserId);
                                otherUser = _users.get(otherUserId);
                                tNumer += currUser.getNormelizedMovieRate(tMovie.get_id())  *
                                        otherUser.getNormelizedMovieRate(tMovie.get_id());
                                tDenom1 += Math.pow(currUser.getNormelizedMovieRate(tMovie.get_id()), 2);
                                tDenom2 += Math.pow(otherUser.getNormelizedMovieRate(tMovie.get_id()), 2);
                            } catch (NoRateException ex) {
                                 Logger.getLogger(MatrixHandler.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		}
		tDenom = Math.sqrt(tDenom1*tDenom2);
		if (tDenom==0){
                    return 0;
		}
		else{
			return tNumer/tDenom;
		}
	}

	public double getK(int userId){
		double tSum = 0;
		Iterator<Entry<Integer, User>> usersIter = _users.entrySet().iterator();
		User tUser;
		while(usersIter.hasNext()) {
			tUser = usersIter.next().getValue();
			if (userId != tUser.getId()) {
				tSum += Math.abs(weight(userId, tUser.getId()));
			}
		}
		return 1/tSum;
	}

	public int getPredictedRate(int userId, int movieId) throws RateNotAtRangeException{
            int ret = _users.get(userId).getMeanRate();
            double tSum = 0;
            if (_users.get(userId).get_rates().containsKey(movieId)) {
                    return _users.get(userId).get_rates().get(movieId);
            }
            else {
                Iterator<Entry<Integer, Integer>> usersIter = _movies.get(movieId).get_rates().entrySet().iterator();
                User tUser;
                while (usersIter.hasNext()) {
                    tUser = _users.get(usersIter.next().getKey());
                    if (userId != tUser.getId()) {
                        try {
                            tSum += weight(userId, tUser.getId()) * tUser.getNormelizedMovieRate(movieId);
                        } catch (NoRateException ex) {
                            Logger.getLogger(MatrixHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                ret = (int) Math.round(ret + getK(userId) * tSum);
                if (ret > 9) {
                	   throw new RateNotAtRangeException("the predicted rate is over 9");
                }
                if  (ret < 1) {
                	   throw new RateNotAtRangeException("the predicted rate is under 0");
                }
                return ret;
            }
	}

	public Vector[] getRecomendations(int userId) throws RateNotAtRangeException{
            Vector[] ans = new Vector[2];
            Vector<Movie> movies = new Vector<Movie>(); // Vector (10 movies) of movies sorted by predicted rate
            Vector<Integer> rates = new Vector<Integer>();
            HashMap<Integer, Integer> tMoviesPredictions =
                    new HashMap<Integer, Integer>(); // movies that user didn't see - movieId / predicted rate

            HashMap<Integer, Integer> userRates =
                    _users.get(userId).get_rates(); // movies that user did see - movieId / predicted rate
            Iterator<Entry<Integer, Movie>> moviesIter = _movies.entrySet().iterator();
            while(moviesIter.hasNext()) {
                Movie tMovie = moviesIter.next().getValue();
                if (!userRates.containsKey(tMovie.get_id())){
                    tMoviesPredictions.put(tMovie.get_id(), getPredictedRate(userId, tMovie.get_id()));
                }
            }
            TreeSet<Entry<Integer,Integer>> tRates = new TreeSet<Entry<Integer,Integer>>(comparator());
            tRates.addAll(tMoviesPredictions.entrySet());
            Iterator<Entry<Integer, Integer>> it = tRates.iterator();
            Entry<Integer, Integer> tEntry = null;
            while (it.hasNext()){
	            tEntry = it.next();
	            movies.add(_movies.get(tEntry.getKey()));
	            rates.add(getPredictedRate(userId,_movies.get(tEntry.getKey()).get_id()));
            }
            ans[0] = movies;
            ans[1] = rates;
            return ans;
	}

	public Comparator<Entry<Integer,Integer>> comparator(){
		return new Comparator<Entry<Integer,Integer>>(){
			public int compare(Entry<Integer, Integer> o1,
					Entry<Integer, Integer> o2) {
				if (o2.getValue().compareTo(o1.getValue()) == 0){
					return o2.getKey().compareTo(o1.getKey());
				}
				else {
					return o2.getValue().compareTo(o1.getValue());
				}
			}
		};
	}
}
