package com.scottcaruso.utilities;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.util.Log;

public class RetrieveData {
	
	public void retrieveCloudantData(String urlString)
	{
		URL dataURL;
		try {
			dataURL = new URL(urlString);
			CloudantRequest cloudReq = new CloudantRequest();
			cloudReq.execute(dataURL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}	
	}
	
	public static String getResponse(URL url)
	{
		String response = "";

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
			//Log.e("Error", String.valueOf(bin));
			
			byte[] contentBytes = new byte[1024];
			int bytesRead = 0;
			StringBuffer responseBuffer = new StringBuffer();
			
			while((bytesRead = bin.read(contentBytes)) != -1)
			{
				response = new String(contentBytes,0,bytesRead);
				responseBuffer.append(response);
			}
			
			Log.i("Data:",responseBuffer.toString());
			return responseBuffer.toString();
		
		} catch (IOException e) {
			Log.e("Error", "getURLStringResponse");
			e.printStackTrace();
		}
	
		return response;
	}
	
	private class CloudantRequest extends AsyncTask<URL, Void, String>
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
