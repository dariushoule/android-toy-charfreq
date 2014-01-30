package com.toy.charfreq.utility;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public class CFHttp extends AsyncTask<String, Integer, byte[]> {
	
	// Interface for generic invokers
	public interface CFHttpDelegate {
		public void downloadComplete(byte[] downloaded);
		public void downloadFailed(String errorDescription);
	}
	
	private CFHttpDelegate mSender;
	
	public CFHttp(CFHttpDelegate sender) {
		mSender = sender;
	}

	@Override
	protected byte[] doInBackground(String... urls) {
		
		// Client does the dirt, request has information about how it should happen
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(urls[0]);
		
		try {
			
			// Fetch the response and return it as a byte array
	        HttpResponse response = httpClient.execute(getRequest);
	        return EntityUtils.toByteArray(response.getEntity());
	    } catch (ClientProtocolException e) {
	    	
	    	// Notify caller that something something is wrong
	    	mSender.downloadFailed("Houston we have a protocol issue, details: " + e.getMessage());
	    } catch (IOException e) {
	    	
	    	// Notify caller that something something is wrong
	    	mSender.downloadFailed("Houston we have an IO failure, details: " + e.getMessage());
	    }
		return null;
	}
	
	protected void onPostExecute(byte[] result) {
		if(result != null) {
			
			// Notify caller the download has completed
			mSender.downloadComplete(result);
		}
    }
}
