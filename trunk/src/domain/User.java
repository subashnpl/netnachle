package domain;

import Exceptions.NoRateException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class User {
	private String _name;
	private String _password;
	private String _permission;
	private int _id;
	private String _sex;
	private HashMap<Integer,Integer> _rates;

	public User(String password, String permission, int id, String name, String sex){
		this._id=id;
		this._password=password;
		this._permission=permission;
		this._name = name;
		this._sex = sex;
		this._rates = new HashMap<Integer, Integer>();
	}//constructor
        public void rateMovie(int movieId, int rate){
                if (_rates.containsKey(movieId)) _rates.remove(movieId);
		_rates.put(movieId, rate);
	}
        public void removeRater(int mId){
            _rates.remove(mId);
        }
        
	public String toString(){
		String ret="Name: "+_name+", Id: "+_id+", Password: "+_password+", Sex: "+_sex+
		", Permission: "+_permission+"\n";
		ret += "Movies/Rates: ";

		Iterator<Integer> ratesIter = _rates.keySet().iterator();
		Integer tMovieId = null;
		Integer tRate =null;
		while(ratesIter.hasNext()) {
			tMovieId = ratesIter.next();
			tRate=_rates.get(tMovieId);
			ret += "\nMovie id: " + tMovieId + "  /  movie rate: " + tRate;
		}
		ret += "\n";
		return ret;
	}

	

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		this._password = password;
	}

	public String getPermission() {
		return _permission;
	}

	public void setPremission(String premission) {
		this._permission = premission;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String get_sex() {
		return _sex;
	}

	public void set_sex(String _sex) {
		this._sex = _sex;
	}

	public HashMap<Integer, Integer> get_rates() {
		return _rates;
	}

	public void set_rates(HashMap<Integer, Integer> _rates) {
		this._rates = _rates;
	}

	public int getMeanRate(){
		double tSum = 0;
		Iterator<Entry<Integer, Integer>> iter = _rates.entrySet().iterator();
		while(iter.hasNext()) {
			tSum += iter.next().getValue();
		}
		return (int) Math.round(tSum/_rates.size());
	}

	public int getMovieRate(int movieId)  throws NoRateException{
		if (!_rates.containsKey(movieId))
			throw new  NoRateException("You didn't see that movie, you KUNT!!!");
		return _rates.get(movieId);
	}

	public int getNormelizedMovieRate(int movieId) throws NoRateException{
		return getMovieRate(movieId) - getMeanRate();
	}
}


