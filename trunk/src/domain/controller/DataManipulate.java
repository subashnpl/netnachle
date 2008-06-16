package domain.controller;

import org.w3c.dom.*;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;

import domain.Administrator;
import domain.Manager;
import domain.Movie;
import domain.User;

import informer.XMLUtilityManagment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class DataManipulate {

	private Document _document;
	private String  _filename;

	public DataManipulate(String filename) throws Exception{
            this._filename = filename;
            try{
                _document = XMLUtilityManagment.readXMLFile(filename+".xml");
            }catch(Exception e){
                try {
                    FileWriter fw=new FileWriter(filename+".xml");
                    fw.write("<?xml version='1.0' encoding='ISO-8859-1' standalone='no'?>\n<"+filename+">\n</"+filename+">");
                    fw.close();
                    _document = XMLUtilityManagment.readXMLFile(filename+".xml");
                } catch (IOException e1) {
                    e1.printStackTrace();
                    throw new Exception("Error with XML File " + filename + ".xml");
                }
            }
	}

	public void addUser(User user){
		NodeList nl = _document.getElementsByTagName("users");
		Element nodeUser = _document.createElement("user");
		nodeUser.setAttribute("userName", user.getName());
		nodeUser.setAttribute("password", user.getPassword());
		nodeUser.setAttribute("permission", ""+user.getPermission());
		nodeUser.setAttribute("id", ""+user.getId());
		nodeUser.setAttribute("sex", user.get_sex());

		HashMap<Integer, Integer> rates = user.get_rates();

	//	System.out.println("user: \n"+user +"end of user");//*************************

		Iterator<Integer> ratesIter = rates.keySet().iterator();
		Integer tMovieId = null;
		Integer tRate =null;
		while(ratesIter.hasNext()) {
			tMovieId = ratesIter.next();
			tRate = rates.get(tMovieId);
			Element movNode = _document.createElement("movie");
			movNode.setAttribute("id", ""+tMovieId);
			movNode.setAttribute("rate", ""+tRate);
			nodeUser.appendChild(movNode);
		}//
		Node node = nl.item(0);
		node.appendChild(nodeUser);
	}

	public void addMovie(Movie mov){
		NodeList nl = _document.getElementsByTagName("movies");
		Element nodeMovie = _document.createElement("movie");
		nodeMovie.setAttribute("name", mov.get_name());
		nodeMovie.setAttribute("id", ""+mov.get_id());
		nodeMovie.setAttribute("category", ""+mov.get_category());
		nodeMovie.setAttribute("year", ""+mov.get_year());
		nodeMovie.setAttribute("country", mov.get_country());
		nodeMovie.setAttribute("duration", ""+ mov.get_duration());
		nodeMovie.setAttribute("director", mov.get_director());
		Vector<String> actors = mov.get_actors();
		for(int j=0; actors != null && j < actors.size(); j++){
			Element movNode = _document.createElement("actor");
			movNode.setAttribute("name", ""+actors.elementAt(j));
			nodeMovie.appendChild(movNode);
		}//for
		Node node = nl.item(0);
		node.appendChild(nodeMovie);
	}

	public HashMap<Integer, User> getUsers(){
		//Vector<User> users = new Vector<User>();
		HashMap<Integer, User> users = new HashMap<Integer, User>();
		NodeList nlUsers = _document.getElementsByTagName("user");
		for(int i=0;i<nlUsers.getLength();i++){
			Node userAttr = nlUsers.item(i);
			NamedNodeMap userAttrs =  userAttr.getAttributes();
			User tUser = null;
			if  (userAttrs.getNamedItem("permission").getNodeValue().equalsIgnoreCase("administrator")){
				tUser = new Administrator(userAttrs.getNamedItem("password").getNodeValue(),
						userAttrs.getNamedItem("permission").getNodeValue(),
						(new Integer(userAttrs.getNamedItem("id").getNodeValue())).intValue(),
						userAttrs.getNamedItem("userName").getNodeValue(),
						userAttrs.getNamedItem("sex").getNodeValue());
			}
			else if  (userAttrs.getNamedItem("permission").getNodeValue().equalsIgnoreCase("manager")){
				tUser = new Manager(userAttrs.getNamedItem("password").getNodeValue(),
						userAttrs.getNamedItem("permission").getNodeValue(),
						(new Integer(userAttrs.getNamedItem("id").getNodeValue())).intValue(),
						userAttrs.getNamedItem("userName").getNodeValue(),
						userAttrs.getNamedItem("sex").getNodeValue());
			}
			else if (userAttrs.getNamedItem("permission").getNodeValue().equalsIgnoreCase("user")){
				tUser = new User(userAttrs.getNamedItem("password").getNodeValue(),
						userAttrs.getNamedItem("permission").getNodeValue(),
						(new Integer(userAttrs.getNamedItem("id").getNodeValue())).intValue(),
						userAttrs.getNamedItem("userName").getNodeValue(),
						userAttrs.getNamedItem("sex").getNodeValue());
			}

			NodeList movList=nlUsers.item(i).getChildNodes();
			for (int j=0;j<movList.getLength();j++){
				if (movList.item(j).getNodeName().equals("movie")){
					NamedNodeMap movModeMap =  movList.item(j).getAttributes();
					int movId = new Integer(movModeMap.getNamedItem("id").getNodeValue());
					int rate = (new Integer(movModeMap.getNamedItem("rate").getNodeValue())).intValue();
					tUser.rateMovie(movId, rate);
				}
			}
			if (tUser!=null){
                            users.put(tUser.getId(), tUser);
			}
		}//for
		return users;
	}//get Users

	public void setUsers(HashMap<Integer, User> users) {
		_document = new DocumentImpl();

		Element root = _document.createElement("users");
		_document.appendChild(root);
		Iterator<Integer> usersIter = users.keySet().iterator();
		while (usersIter.hasNext()){
			User tUsr=users.get(usersIter.next());
			addUser(tUsr);
		}
		try {
			XMLUtilityManagment.writeXMLFile(_document,_filename+".xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<Integer, Movie> getMovies(){//oz vector change to hashMap

		HashMap<Integer, Movie> movies = new HashMap<Integer, Movie>();

		NodeList nlMovies = _document.getElementsByTagName("movie");
		for(int i=0;i<nlMovies.getLength();i++){
			Node movieAttr = nlMovies.item(i);
			NamedNodeMap movieAttrs =  movieAttr.getAttributes();

			Vector<String> tActors = new Vector<String>();
			NodeList movList=nlMovies.item(i).getChildNodes();
			for (int j=0;j<movList.getLength();j++){
				if (movList.item(j).getNodeName().equals("actor")){
					NamedNodeMap movModeMap =  movList.item(j).getAttributes();
					String movName = movModeMap.getNamedItem("name").getNodeValue();
					tActors.add(movName);
				}
			}

			Movie tMovie = new Movie(movieAttrs.getNamedItem("name").getNodeValue(),
					(new Integer(movieAttrs.getNamedItem("id").getNodeValue())).intValue(),
					movieAttrs.getNamedItem("category").getNodeValue(),
					tActors,
					(new Integer(movieAttrs.getNamedItem("year").getNodeValue())).intValue(),
					movieAttrs.getNamedItem("country").getNodeValue(),
					(new Integer(movieAttrs.getNamedItem("duration").getNodeValue())).intValue(),
					movieAttrs.getNamedItem("director").getNodeValue());

			movies.put(tMovie.get_id(), tMovie);

		}//for
		return movies;
	}//get Users

	public void setMovies(HashMap<Integer, Movie> movies) {//oz : vector change to hashMap
		_document = new DocumentImpl();

		Element root = _document.createElement("movies");
		_document.appendChild(root);

		Iterator<Integer> movieIter=movies.keySet().iterator();
		while (movieIter.hasNext()){
			addMovie(movies.get(movieIter.next()));
			//System.out.println(movies.get(movieIter.next()));
		}
		//for (int i=0;i<movies.size();i++) {


//
//		}
		try {
			XMLUtilityManagment.writeXMLFile(_document,_filename+".xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}//class Data Manipulation

