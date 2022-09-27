package Com.qa.test.API;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Com.ExtentReports.BaseClass;
import Com.ExtentReports.ExtentTestManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class FindCenterAPI extends BaseClass{
	
	static String currentDir=System.getProperty("user.dir") +File.separator+"Library\\JsonBody.txt";
	
	@Test
	public static void PostRequestForFindCenterAPI() throws IOException {
		String line;
		BufferedReader reader = new BufferedReader(new FileReader(currentDir));
		String json = "";
		try {
			StringBuilder sb = new StringBuilder();
			line = reader.readLine();
			
			while(line != null) {
				sb.append(line);
				sb.append("\n");
				line = reader.readLine();
			}
			json = sb.toString();
			
		} finally {
			reader.close();
		}
		
	String url= "https://apim-spin2-dev-westus2-001.azure-api.net/patient/Center/GetAvailableCenters";
	RestAssured.baseURI=url;
	
	ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " +url);
	ExtentTestManager.getTest().log(LogStatus.INFO, "Payload is: " +json);
	
	
	ValidatableResponse response= RestAssured
			.given()
			.headers("Authorization", 
					"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IjJaUXBKM1VwYmpBWVhZR2FYRUpsOGxWMFRPSSJ9.eyJhdWQiOiIzZGY4ZTVkZS0xMWE0LTQ3ZGQtOWQ2My0wMWFlMjFiMmJmZWMiLCJpc3MiOiJodHRwczovL2xvZ2luLm1pY3Jvc29mdG9ubGluZS5jb20vMzM0MzExZWMtMTY0MS00OWQ5LWJhMTAtYjJlYzI4NTM4NjRkL3YyLjAiLCJpYXQiOjE2NjQyODU2NzQsIm5iZiI6MTY2NDI4NTY3NCwiZXhwIjoxNjY0Mjg5NTc0LCJhaW8iOiJBVFFBeS84VEFBQUF4dHd5Y0RkZzlnR3dhb3EwTHJNM2hDTHVQS2hJN0w1RXhFT1JsZjRQRVhIV2ljSUJsWG9KR0NQNmI3UWtWWERCIiwibmFtZSI6IlZpc2hudSBWaWpheSIsIm5vbmNlIjoiYjg5YmJkYjgtOWI4OC00YTk5LWIxOTEtMzdmMjI3NmQ5N2JmIiwib2lkIjoiYjFhN2JkNzctMmFhNS00YTY5LWE5ZDMtMmIzODYyYmZmNzk4IiwicHJlZmVycmVkX3VzZXJuYW1lIjoiVmlzaG51LlZpamF5QHNwZXJpZGlhbmxhYnMub25taWNyb3NvZnQuY29tIiwicmgiOiIwLkFUNEE3QkZETTBFVzJVbTZFTExzS0ZPR1RkN2wtRDJrRWQxSG5XTUJyaUd5di13LUFFdy4iLCJyb2xlcyI6WyJQYXRpZW50UGxhY2VtZW50Il0sInN1YiI6ImhscmFEeXU1T1hoVkVsa29mUzYzblFCampkRDBLdGtTWUVTeElkUHRGV0EiLCJ0aWQiOiIzMzQzMTFlYy0xNjQxLTQ5ZDktYmExMC1iMmVjMjg1Mzg2NGQiLCJ1dGkiOiJEUFRJRzBrVWwweWN1MkZtbTVKaEFBIiwidmVyIjoiMi4wIn0.r1NOCN3Q2-qq9AFOdHnhLcqfgoQJoFkKEbZ1pX7GcmaIVyL2uaTPVCgfZxJaX_QxcLuwZzzwh0LRB-LpEzZfi_T9tK7w26VSTcOVd1ycK4YyOSRbBzeOC1eA_-q4EJqzTVdyPsDUHbOWEg76wKllOW3o3w95mpcuy6MWxAAPtGlbO9ENCqvY7R7pb2bpi-FRFuPgdxmYqgTH_iO44ocZfzEUQqApj-pn4ESJem5EdtmHi9f0otxjZ0SRkpybiDr23dERvQAGx4QEud0XHFXk0TSokooeQARDyiYAZx2ZVAXlXQWOZ4Y7RxPcebJAraBevdccAxE5nAhUD0bGVGS5lQ",
					"Content-Type", "application/json",
					"Accept", "application/json")
			.when()
			.body(json)
			.post()
			.then();
	
	System.out.println("Status Code is: ");
	ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " +response.extract().asString());
	response.statusCode(200);
	ExtentTestManager.getTest().log(LogStatus.INFO, "Have Verified the Status Code Successfully.");
	response.contentType(ContentType.JSON);
	ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the ContentType Successfully.");
	response.body("code", Matchers.containsString("SUCCESS"));
	ExtentTestManager.getTest().log(LogStatus.INFO, "Verified Response Body Code");
	response.body("message", Matchers.containsString("Success"));

	}

}
