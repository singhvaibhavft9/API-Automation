package utils;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class SoftAssertionUtil {
	
	private static SoftAssert softAssertInstance;
	
	
	private SoftAssertionUtil()
	{
		
		
	}
	
	public static SoftAssert getInstance()
	{
		if(softAssertInstance==null)
		{
		     softAssertInstance = new SoftAssert();

		}
		return softAssertInstance;
	}
	
	
	public static void assertTrue(boolean condition, String message)
	{
		try
		{
			softAssertInstance.assertTrue(condition,message);
		}
		catch(AssertionError e)
		{
			softAssertInstance.fail(message); 
			
		}
	}
	
	  public static void assertEquals(Object actual, Object expected, String message) {
	       try {
	    	   softAssertInstance.assertEquals(actual, expected, message);
	       } catch (AssertionError e) {
	    	   softAssertInstance.fail(message);
	       }
	   }


	   public static void assertNotEquals(Object actual, Object expected, String message) {
	       try {
	    	   softAssertInstance.assertNotEquals(actual, expected, message);
	       } catch (AssertionError e) {
	    	   softAssertInstance.fail(message);
	       }
	   }


	   public  static void assertAll() {
		   softAssertInstance.assertAll();
	   }


}
