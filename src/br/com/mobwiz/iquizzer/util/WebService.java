package br.com.mobwiz.iquizzer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public class WebService  {
	//final static String ip = "192.168.0.116"; //casa
	//final static String ip = "10.0.0.172"; //deti
	final static String ip = "iquizzer.herokuapp.com";
	final static int port = 80;
			
	//final static int port = 3000;
	
	public String get(String parameters) throws Exception{
		StringBuffer jsonBuilder = new StringBuffer();
		String urlString = "http://"+ip+":"+port+"/"+parameters;
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(urlString);
		HttpResponse response = client.execute(httpGet);
        //GetXMLTask task = new GetXMLTask();
        //task.execute(new String[] { urlString });

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
