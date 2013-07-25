package com.scottcaruso.utilities;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.scottcaruso.userinterface.MainActivity;

import android.os.AsyncTask;
import android.util.Log;

public class RetrieveData {
	
	static String response = "";
	
	public static boolean doesThisUserExist(String textEntry)
	{
		//This function will be used to determine if the user exists in the Cloudant database
		return false;
	}
	
	public static String retrieveCloudantData(String urlString)
	{
		URL dataURL;
		try 
		{
			dataURL = new URL(urlString);
			CloudantRequest cloudReq = new CloudantRequest();
			cloudReq.execute(dataURL);
			return response;	
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		}	
		return response;
	}
	
	public static String getResponse(URL url)
	{

		try 
		{
			URLConnection connection = url.openConnection();
			Log.e("Error", String.valueOf(connection));
			BufferedInputStream bin = null;
			try {
				bin = new BufferedInputStream(connection.getInputStream());
			} catch (Exception e) {
				Log.e("Error","Failed at BufferedInputStream");
				e.printStackTrace();
			}
			
			byte[] contentBytes = new byte[1024];
			int bytesRead = 0;
			StringBuffer responseBuffer = new StringBuffer();
			
			while((bytesRead = bin.read(contentBytes)) != -1)
			{
				response = new String(contentBytes,0,bytesRead);
				responseBuffer.append(response);
			}
			
			return responseBuffer.toString();
		
		} catch (IOException e) {
			Log.e("Error", "getURLStringResponse");
			e.printStackTrace();
		}
	
		return response;
	}
	
	private static class CloudantRequest extends AsyncTask<URL, Void, String>
	{
		@Override
		protected String doInBackground(URL... urls)
		{
			String response = "";
			for(URL url: urls)
			{
				response = RetrieveData.getResponse(url);
			}
			
			return response;
		}
		
		@Override
		protected void onPostExecute(String result)
		{
			Log.i("URL_RESPONSE_ERROR",result);
		}
	}
		
}
