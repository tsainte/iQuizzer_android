package br.com.mobwiz.iquizzer.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class WebService  {
	//final static String ip = "192.168.0.116"; //casa
	//final static String ip = "10.0.0.172"; //deti
	final static String ip = "iquizzer.herokuapp.com";
	final static int port = 80;
	//final static String ip = "localhost";		
	//final static int port = 3000;
	Context context;
	public WebService(Context context){
		this.context = context;
	}
	public WebService(){
		
	}
	private String getRequest(String parameter){
		String request = parameter;
		if (context != null){
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
			String token = preferences.getString("token", "");
			 request = request + "?auth_token=" + token;
		}
		return request;
	}
	public String get(String parameters) throws Exception{
		StringBuffer jsonBuilder = new StringBuffer();
		String urlString = "http://"+ip+":"+port+"/"+getRequest(parameters);
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(urlString);
		HttpResponse response = client.execute(httpGet);

		StatusLine statusLine = response.getStatusLine();
		int statusCode = statusLine.getStatusCode();
		if (statusCode == 200){
			HttpEntity entity = response.getEntity();
			InputStream content = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(content));
			String line;
			while ((line = reader.readLine()) != null) {
				jsonBuilder.append(line);
			}
		}
		return jsonBuilder.toString();
	}
	
	
	public String RESTCommand(String parameters, String httpMethod, String jsonBody){
	    String wResult = "";
	    //mount parameters
		String urlString = "http://"+ip+":"+port+"/"+getRequest(parameters);
		URL url;
		try {
			url = new URL(urlString);
			//create HTTP
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod(httpMethod);
			
		     connection.addRequestProperty("Content-Type", "application/json");

			  // Create the form content	
			  OutputStream out = connection.getOutputStream();
			  Writer writer = new OutputStreamWriter(out);
			  writer.write(jsonBody);
			  writer.close();
			  out.close();
			  
			  // Buffer the result into a string
			  BufferedReader rd = new BufferedReader(
			      new InputStreamReader(connection.getInputStream()));
			  StringBuilder sb = new StringBuilder();
			  String line;
			  while ((line = rd.readLine()) != null) {
			    sb.append(line);
			  }
			  rd.close();

			  connection.disconnect();
			  wResult = sb.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 return wResult;

	}
		// http://stackoverflow.com/questions/4205980/java-sending-http-parameters-via-post-method-easily
		
		//continua aqui...
		/*connection.setm
		HttpResponse response = client.execute(httpGet);
	    
	    NSURL* url = [NSURL URLWithString:urlstring];
	    NSMutableURLRequest* theRequest = [NSMutableURLRequest requestWithURL:url];
	    
	    [theRequest setHTTPBody: body];
	    [theRequest setValue:@"application/json" forHTTPHeaderField:@"Content-Type"];
	    [theRequest setHTTPMethod:method];
	    
	    NSLog(@"the json: %@", [[NSString alloc] initWithData:body encoding:NSUTF8StringEncoding]);
	    NSLog(@"the request: %@",[theRequest description]);
	    //make connection
	    NSURLConnection *theConnection = [[NSURLConnection alloc]initWithRequest:theRequest delegate:self];
	    if(theConnection){
	        NSLog(@"Connection ok");
	        receivedData = [[NSMutableData alloc] init];
	    }
	    else
	        NSLog(@"Connection is NULL");
	}

	/*
    private class GetXMLTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String output = null;
            for (String url : urls) {
                output = getOutputFromUrl(url);
            }
            return output;
        }
 
        private String getOutputFromUrl(String url) {
            String output = null;
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
 
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                output = EntityUtils.toString(httpEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return output;
        }
 
        @Override
        protected void onPostExecute(String output) {

        }
    }
*/


		
}
