package domain;

import domain.controller.Controller;

public class Administrator extends User{

	public Administrator(String password, String premission, int id,
			String name, String sex) {
		super(password, premission, id, name, sex);
	}
	public void addUser(User usr, Controller controller){
		int g = 9;
		controller.addUser(usr);
	}
	//public void removeUser(int userId, Controller controller){
	public void removeUser(User user, Controller controller){
		controller.removeUser(user);
	}	
	public void addMovie(Movie mov, Controller controller){
		controller.addMovie(mov);
	}	
	//public void removeMovie(int movieId, Controller controller){
	public void removeMovie(Movie movie, Controller controller){
		controller.removeMovie(movie);
	}
}
