package clarive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.*;

public class Prueba {

	public static void main(String[] args) throws IOException {
		printFactorial();
	}
	
	public static void printFactorial() throws IOException {
		
		String httpParam = "http://support.clarive.com/rule/json/test?user=raragues";
		JSONObject jObj = getJSONObj(httpParam);
		int numberP = getNumberParam(jObj);
		int factorial = calculateFactorial(numberP);
		System.out.println("factorial of " + numberP + " is " + factorial);
	}
	
	public static JSONObject getJSONObj (String httpParam) throws IOException{
		JSONObject jObj = null;
		
		if (httpParam!= null){
			try {
				URL jsonPage = new URL(httpParam);
				URLConnection urlCon = jsonPage.openConnection();
				BufferedReader buffRead = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
				
				String recv;
				String recvBuff = "";
				while ((recv = buffRead.readLine()) != null)
					recvBuff += recv;
				buffRead.close();
		
				jObj = new JSONObject(recvBuff);
			}
			catch(MalformedURLException e){
		        System.out.println("Warning: Invalid URL");
		    }
			catch(IOException e){
		        System.out.println("Warning: Invalid JSON format");
		    }
		} else {System.out.println("Warning: URL null");}
	
		return jObj;
	}
	
	public static int getNumberParam(JSONObject jObj){
		
		int numberP = 0;
		
		if (jObj!=null){
			try {
				numberP = Integer.valueOf(jObj.getString("number"));
			}
			catch(org.json.JSONException e){
				System.out.println("Warning: invalid tag name");
			}
			catch(java.lang.NumberFormatException e){
				System.out.println("Warning: number format exception");
			}
		}
		return numberP;
	}
	
	public static int calculateFactorial (int numberP){
		int fTotal = 0;
		if (numberP > 0){
			fTotal=1;
			for(int i=1; i<numberP; i++){
				fTotal = fTotal * (i+1);
			}	
		}
		return fTotal;
	}
}
