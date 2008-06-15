

import domain.AdministratorTest;
import domain.ManagerTest;
import domain.MovieTest;
import domain.UserTest;
import domain.controller.ControllerTest;
//import domain.controller.DataManipulateTest;
//import domain.controller.LoggerTest;
//import domain.controller.MatrixHandlerTest;
//import informer.XMLUtilityManagmentTest;
import domain.controller.DataManipulateTest;
import domain.controller.LoggerTest;
import domain.controller.MatrixHandlerTest;
import informer.XMLUtilityManagmentTest;
import junit.framework.Test;
import junit.framework.TestSuite;
//import tests.domain.controller.DataManipulateTest;
 
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(AdministratorTest.class);
		suite.addTestSuite(ManagerTest.class);
		suite.addTestSuite(MovieTest.class);
		suite.addTestSuite(UserTest.class);
		suite.addTestSuite(ControllerTest.class);
		suite.addTestSuite(DataManipulateTest.class);
		suite.addTestSuite(MatrixHandlerTest.class);
		suite.addTestSuite(XMLUtilityManagmentTest.class);
		suite.addTestSuite(LoggerTest.class);
		//$JUnit-END$
		return suite;
	}

}
