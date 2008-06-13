package domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class Movie {
	private String _name;
	private int _id;
	private String _category;
	private Vector<String> _actors;
	private int _year;
	private String _country;
	private double _duration;
	private String _director;
	public HashMap<Integer,Integer>_rates;

	public Movie(String _name, int _id, String _category,
			Vector<String> _actors, int _year, String _country,
			double _duration, String _director) {
		this._name = _name;
		this._id = _id;
		this._category = _category;
		this._actors = _actors;
		this._year = _year;
		this._country = _country;
		this._duration = _duration;
		this._director = _director;
		this._rates=new HashMap<Integer, Integer>();
	}
        public void removeRater(int uId){
            _rates.remove(uId);
        }
	public String toString(){
		String ret="Name: "+_name+", Id: "+_id+", Category: "+_category+", Year: "+_year+
		", Country: "+_country+", Duration: "+_duration+", Director: "+_director+"\n";
		ret += "Actors: ";
		for(int i=0;i<_actors.size();i++){
			ret += _actors.get(i)+" ,";
		}
		Iterator<Integer> ratersIter = _rates.keySet().iterator();
		ret+="\nRaters:";
		while (ratersIter.hasNext()){
			Integer tIdUser = ratersIter.next();
			ret += "\n\trater: " +  tIdUser + "  /    given rate: " + _rates.get(tIdUser);
		}
		ret += "\n";
		return ret;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_category() {
		return _category;
	}

	public void set_category(String _category) {
		this._category = _category;
	}

	public Vector<String> get_actors() {
		return _actors;
	}

	public void set_actors(Vector<String> _actors) {
		this._actors = _actors;
	}

	public int get_year() {
		return _year;
	}

	public void set_year(int _year) {
		this._year = _year;
	}

	public String get_country() {
		return _country;
	}

	public void set_country(String _country) {
		this._country = _country;
	}

	public double get_duration() {
		return _duration;
	}

	public void set_duration(double _duration) {
		this._duration = _duration;
	}

	public String get_director() {
		return _director;
	}

	public void set_director(String _director) {
		this._director = _director;
	}

	public HashMap<Integer, Integer> get_rates() {
		return _rates;
	}

	public void set_rates(HashMap<Integer, Integer> _rates) {
		this._rates = _rates;
	}

	public void add_rater(int raterId, int rate) {
		_rates.put(raterId, rate);
	}

//
//
//	public String get_name() {
//		return _name;
//	}
//
//	public void set_name(String _name) {
//		this._name = _name;
//	}
//
//	public int get_id() {
//		return _id;
//	}
//
//	public void set_id(int _id) {
//		this._id = _id;
//	}
//
//	public String get_category() {
//		return _category;
//	}
//
//	public void set_category(String _category) {
//		this._category = _category;
//	}
//
//	public Vector<String> get_actors() {
//		return _actors;
//	}
//
//	public void set_actors(Vector<String> _actors) {
//		this._actors = _actors;
//	}
//
//	public int get_year() {
//		return _year;
//	}
//
//	public void set_year(int _year) {
//		this._year = _year;
//	}
//
//	public String get_country() {
//		return _country;
//	}
//
//	public void set_country(String _country) {
//		this._country = _country;
//	}
//
//	public double get_duration() {
//		return _duration;
//	}
//
//	public void set_duration(double _duration) {
//		this._duration = _duration;
//	}
//
//	public String get_director() {
//		return _director;
//	}
//
//	public void set_director(String _director) {
//		this._director = _director;
//	}
//

}
