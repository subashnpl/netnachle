package domain.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;
import java.util.Map.Entry;

import domain.Movie;
import domain.User;

public class MatrixHandler implements Strategy{

	private HashMap<Integer,User> _users;
	private HashMap<Integer,Movie> _movies;

//	public MatrixHandler(HashMap<Integer, User> _users,
//			HashMap<Integer, Movie> _movies) {
//		this._users = _users;
//		this._movies = _movies;
//	}

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

	public double weight(Integer currUser, Integer otherUser) {
		double tNumer = 0;
		double tDenom = 0;
		double tDenom1 = 0;
		double tDenom2 = 0;
		Iterator<Entry<Integer, Integer>> moviesIter = _users.get(currUser).get_rates().entrySet().iterator();
		Movie tMovie;

		while(moviesIter.hasNext()) {
			tMovie = _movies.get(moviesIter.next().getKey());
			if (tMovie.get_rates().containsKey(otherUser)){
				tNumer += _users.get(currUser).getNormelizedMovieRate(tMovie.get_id())*
					_users.get(otherUser).getNormelizedMovieRate(tMovie.get_id());
				tDenom1 += Math.pow(_users.get(currUser).getNormelizedMovieRate(tMovie.get_id()),2);
				tDenom2 += Math.pow(_users.get(otherUser).getNormelizedMovieRate(tMovie.get_id()),2);
			}
			tDenom = Math.sqrt(tDenom1*tDenom2);
		}
		return tNumer/tDenom;
	}

	public double getK(int userId){
		double tSum = 0;

		Iterator<Entry<Integer, User>> usersIter = _users.entrySet().iterator();
		User tUser;
		while(usersIter.hasNext()) {
			tUser = usersIter.next().getValue();
			if (userId != tUser.getId()) {
				tSum += weight(userId, tUser.getId());
			}
		}
		return Math.abs(1/tSum);
	}

	public int getPredictedRate(int userId, int movieId){

		int ret = _users.get(userId).getMeanRate();

		double tSum = 0;

		if (_users.get(userId).get_rates().containsKey(movieId)) {
			return _users.get(userId).get_rates().get(movieId);
		}
		else {
			Iterator<Entry<Integer, User>> usersIter = _users.entrySet().iterator();
			User tUser;
			while (usersIter.hasNext()) {
				tUser = usersIter.next().getValue();
				if (userId != tUser.getId()) {
					tSum += weight(userId, tUser.getId())
							* tUser.getNormelizedMovieRate(movieId);
				}
			}
			return (int) Math.round(ret + getK(userId) * tSum);
		}
	}

	public Vector<Movie> get10Recomendations(int userId){
		Vector<Movie> ret = new Vector<Movie>(); // Vector (10 movies) of movies sorted by predicted rate

		HashMap<Integer, Integer> tMoviesPredictions =
			new HashMap<Integer, Integer>(); // movies that user didn't see - movieId / predicted rate

		HashMap<Integer, Integer> userRatesIter =
			_users.get(userId).get_rates(); // movies that user did see - movieId / predicted rate

		Iterator<Entry<Integer, Movie>> moviesIter = _movies.entrySet().iterator();
		Movie tMovie;
		while(moviesIter.hasNext()) {
			tMovie = moviesIter.next().getValue();
			if (!userRatesIter.containsKey(tMovie.get_id())){
				tMoviesPredictions.put(tMovie.get_id(), getPredictedRate(userId,tMovie.get_id()));
			}
		}

		TreeSet<Entry<Integer,Integer>> tRates = new TreeSet<Entry<Integer,Integer>>(comparator());
		tRates.addAll(tMoviesPredictions.entrySet());
		Iterator<Entry<Integer, Integer>> it = tRates.iterator();
		Entry<Integer, Integer> tEntry = null;
		int i;
		for (i = 0;it.hasNext() && i<10;i++){
			tEntry = it.next();
			System.out.println("rate of "+tEntry.getKey()+": "+tEntry.getValue()+"\n");
			ret.add(_movies.get(tEntry.getKey()));
		}
		return ret;
	}

	public Comparator<Entry<Integer,Integer>> comparator(){
		return new Comparator<Entry<Integer,Integer>>(){
			public int compare(Entry<Integer, Integer> o1,
					Entry<Integer, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		};
	}



}
