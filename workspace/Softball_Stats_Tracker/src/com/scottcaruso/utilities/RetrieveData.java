package com.scottcaruso.utilities;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.scottcaruso.uielementgenerator.UIElementCreator;
import com.scottcaruso.userinterface.CreateDisplay;
import com.scottcaruso.userinterface.MainActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

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
			try 
			{
				JSONObject thisObject = new JSONObject (result);
				JSONArray rows = thisObject.getJSONArray("rows");
				int numberOfItems = rows.length();
				for (int x = 0; x < numberOfItems; x++)
				{
					String thisJson = rows.getJSONObject(x).getString("doc");
					String nameString = rows.getJSONObject(x).getJSONObject("doc").getString("username");
					if (nameString.compareTo(MainActivity.getTextThatWasEntered()) == 0)
					{
						MainActivity.setResultText(thisJson);
						CreateDisplay statDisplay = new CreateDisplay(MainActivity.getCurrentContext());
						MainActivity.updateView(statDisplay);
						break;
					}
					if (x == numberOfItems-1)
					{
						Context currentContext = MainActivity.getCurrentContext();
						final LinearLayout mainLayout = new LinearLayout(MainActivity.getCurrentContext());
						mainLayout.removeAllViews();
						final TextView userDoesntExist = UIElementCreator.createLabel(currentContext, "User "+MainActivity.getTextThatWasEntered()+" could not be found. Would you like to create a new entry or search again?");
						final Button createNewUser = UIElementCreator.createButton(currentContext, "Create New", 1);
						final Button searchAgain = UIElementCreator.createButton(currentContext, "Search Again", 2);
						mainLayout.addView(userDoesntExist);
						mainLayout.addView(createNewUser);
						mainLayout.addView(searchAgain);
						createNewUser.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) 
							{
								//Enter createNewUser flow		
							}
						});
						searchAgain.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) 
							{
								mainLayout.removeAllViews();
								mainLayout.addView(MainActivity.getUserName());
						        mainLayout.addView(MainActivity.getSearchForUserName());
							}
						});	

				    mainLayout.setOrientation(LinearLayout.VERTICAL);
					MainActivity.updateView(mainLayout);
					}
				}
			} catch (JSONException e) 
			{
				e.printStackTrace();
			}
			Log.i("URL_RESPONSE_ERROR",result);
		}
	}
		
}
