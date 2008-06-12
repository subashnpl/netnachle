package domain.controller;

import domain.Movie;
import domain.User;
import java.util.HashMap;

interface Strategy {
    public void set_users(HashMap<Integer, User> _users);
    public void set_movies(HashMap<Integer, Movie> _movies);
}
