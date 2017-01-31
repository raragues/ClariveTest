package clarive;

import java.io.IOException;

import org.json.JSONObject;

public class PruebaTest {

	public static void main(String[] args) throws IOException {
		testCasesFactorial();
	}
	
	private static void printFactorialTestURL(String httpParam, JSONObject jObj, int numberP) throws IOException {
		
		jObj = Prueba.getJSONObj(httpParam);
		numberP = Prueba.getNumberParam(jObj);
		int factorial = Prueba.calculateFactorial(numberP);
		System.out.println("factorial of " + numberP + " is " + factorial);
	}
	
	private static void printFactorialTestJSON(JSONObject jObj, int numberP) throws IOException {
		
		numberP = Prueba.getNumberParam(jObj);
		int factorial = Prueba.calculateFactorial(numberP);
		System.out.println("factorial of " + numberP + " is " + factorial);
	}
	
	
	private static void testCasesFactorial() throws IOException{
		
		String sObj = "";
		JSONObject jObj = null;
		
		//#1: URL not valid
		printFactorialTestURL("http://clarive.com", null, 0);
		//Result: Warning: Invalid JSON format
		//        factorial of 0 is 0
		
		//#2: URL null
		printFactorialTestURL(null, null, 0);
		//Result: Warning: URL null
		//		  factorial of 0 is 0
		
		//#3: URL ""
		printFactorialTestURL("", null, 0);
		//Result: Warning: Invalid URL
		//        factorial of 0 is 0
		
		//#4: JSON null or invalid
		printFactorialTestJSON(null, 0);
		//Result: factorial of 0 is 0
		
		//#5: JSON valid format but tag number doesn't exist
		sObj = "{" + "\"name\": \"Jack\"" + "}";
		jObj = new JSONObject(sObj);
		printFactorialTestJSON(jObj, 0);
		//Result: Warning: invalid tag name
		//        factorial of 0 is 0
		
		//#6: JSON valid format but number content is not an integer
	    sObj = "{" + "\"number\": \"Jack\"" + "}";
	    jObj = new JSONObject(sObj);
	    printFactorialTestJSON(jObj, 0);
	    //Result: Warning: number format exception
	    //        factorial of 0 is 0
	}	
}
