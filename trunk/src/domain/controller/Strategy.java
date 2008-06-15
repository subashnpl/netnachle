package domain.controller;

import Exceptions.RateNotAtRangeException;
import domain.Movie;
import domain.User;
import java.util.HashMap;
import java.util.Vector;

public interface Strategy {
    public void set_users(HashMap<Integer, User> _users);
    public void set_movies(HashMap<Integer, Movie> _movies);
    public Vector[] getRecomendations(int userId) throws RateNotAtRangeException;
}
