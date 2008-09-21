package domain;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import domain.Movie;

public class MovieTest extends TestCase{

	@Test
	public void testMovie() {
		Movie m=new Movie("kadoshara",22,"action",null,1984,"israel",120,"katzman");
		Assert.assertTrue("impropper category ", m.get_category().equalsIgnoreCase("action"));
		Assert.assertTrue("impropper year ", m.get_year()==1984);
	}

	@Test
	public void testRemoveRater() {
		
	}

	@Test
	public void testToString() {
		
	}

	@Test
	public void testGet_name() {
		
	}

	@Test
	public void testSet_name() {
		
	}

	@Test
	public void testGet_id() {
		
	}

	@Test
	public void testSet_id() {
		
	}

	@Test
	public void testGet_category() {
		
	}

	@Test
	public void testSet_category() {
		
	}

	@Test
	public void testGet_actors() {
		
	}

	@Test
	public void testSet_actors() {
		
	}

	@Test
	public void testGet_year() {
		
	}

	@Test
	public void testSet_year() {
	
	}

	@Test
	public void testGet_country() {
		
	}

	@Test
	public void testSet_country() {
		
	}

	@Test
	public void testGet_duration() {
		
	}

	@Test
	public void testSet_duration() {
		
	}

	@Test
	public void testGet_director() {
		
	}

	@Test
	public void testSet_director() {
		
	}

	@Test
	public void testGet_rates() {
		
	}

	@Test
	public void testSet_rates() {
		
	}

	@Test
	public void testAdd_rater() {
		
	}

}
