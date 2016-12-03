package syntel.training.github;

import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

//import org.apache.http.client.ClientProtocolException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
/*
 * Simple program with built in Http connection to read from a file
 */

public class GitHubClient {

	public static void main(String[] args) {
		GitHubClient client = new GitHubClient();
		//client.readFromFile();
client.fetchRepositories();
		}
	
	/**
	 * Reads the content of the file from GitHub 
	 */
	private void readFromFile(){
		try {
			System.out.println("=== Reading From File Start ====");
			// Git Hub URL to invoke
//			URL url = new URL("https://api.github.com/repos/octocat/Hello-World");
			URL url = new URL("https://api.github.com/users/octocat/repos");
			// opening the connection for the specified url
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// Specify Get / Post method
			connection.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			//closing the Buffer Reader
			br.close();
			//Closing the connection
			connection.disconnect();
			System.out.println("=== End Reading From File ====");
		} catch (MalformedURLException e) {
			System.out.println("!!! Please Enter proper URL ");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("!!! Please Check for the file / permission ");
			e.printStackTrace();
		}
	} 

	/**
	 * Retrieving the repositories using Apache Library for Restful service call
	 * Can also use Jersey / Restlet to use the JAX RS Call
	 * URL provides the available services for repositories: https://developer.github.com/v3/repos/
	 */
	private void fetchRepositories(){
		try{
			System.out.println("=== Fetching the Repositories ====");
			// Git Hub URL to invoke
			ClientConfig config = new DefaultClientConfig();
	        Client client = Client.create(config);
	        WebResource service = client.resource(UriBuilder.fromUri("https://api.github.com/").build());
	        System.out.println(service.path("users").path("defunkt").path("repos").accept(MediaType.APPLICATION_JSON).get(String.class));
		} catch (Exception e) {
			System.out.println("!!! Please Check for the file / permission in fetchRepositories()");
			e.printStackTrace();
		}
	}
	
}
