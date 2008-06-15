package domain;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import domain.User;

public class UserTest extends TestCase{

	@Test
	public void testUser() {
		User u=new User("1231","user",12,"jo","male");
		Assert.assertTrue("impropper sex ", u.get_sex().equals("male"));
		Assert.assertTrue("impropper id ", u.getId()==12);
	}

	@Test
	public void testRateMovie() {
		
	}

	@Test
	public void testRemoveRater() {
		
	}

	@Test
	public void testToString() {
		
	}

	@Test
	public void testGetName() {
		
	}

	@Test
	public void testSetName() {
		
	}

	@Test
	public void testGetPassword() {
		
	}

	@Test
	public void testSetPassword() {
		
	}

	@Test
	public void testGetPermission() {
		
	}

	@Test
	public void testSetPremission() {
	
	}

	@Test
	public void testGetId() {
		
	}

	@Test
	public void testSetId() {
		
	}

	@Test
	public void testGet_sex() {
	
	}

	@Test
	public void testSet_sex() {
		
	}

	@Test
	public void testGet_rates() {
		
	}

	@Test
	public void testSet_rates() {
		
	}

	@Test
	public void testGetMeanRate() {
		
	}

	@Test
	public void testGetMovieRate() {

	}

	@Test
	public void testGetNormelizedMovieRate() {
		
	}

}
