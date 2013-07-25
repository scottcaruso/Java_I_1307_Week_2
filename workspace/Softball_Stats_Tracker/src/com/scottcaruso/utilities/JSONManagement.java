/* Scott Caruso - Java 1307 - Week 2 Assignment
 * 
 * 7/18/2013
 */
package com.scottcaruso.utilities;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.scottcaruso.uielementgenerator.UIElementCreator;
import com.scottcaruso.userinterface.CreateDisplay;
import com.scottcaruso.userinterface.MainActivity;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class JSONManagement 
{
	
	//creates JSON object based on the entered data
	public static JSONObject createJSONObject(String date,String opponent,String atbats,String hits,String doubles,String triples,String homeruns,String rbis)
	{
		JSONObject newGame = new JSONObject();
		try {
			newGame.put("Date", date);
			newGame.put("Opponent Name",opponent);
			newGame.put("AB",atbats);
			newGame.put("H",hits);
			newGame.put("2B",doubles);
			newGame.put("3B",triples);
			newGame.put("HR",homeruns);
			newGame.put("RBI",rbis);
			return newGame;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return newGame;
		}
	}
	
	public static void addObjectToLiveArray(JSONObject toadd)
	{
		try {
			JSONObject master = new JSONObject(MainActivity.getResultText());
			String id = master.getString("_id");
			master.getJSONObject("stats").getJSONArray("Games").put(toadd);
			String urlParameters = master.toString();
			String request = "http://scottcaruso.cloudant.com/softballstats/"+id;
			URL url;
			try {
				url = new URL(request);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				url = null;
			} 
			HttpURLConnection connection;
			try {
				connection = (HttpURLConnection) url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				connection = null;
			}           
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false); 
			try {
				connection.setRequestMethod("PUT");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			connection.setRequestProperty("Content-Type", "application/json"); 
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
			connection.setUseCaches (false);

			DataOutputStream stream;
			try {
				stream = new DataOutputStream(connection.getOutputStream ());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				stream = null;
			}
			try {
				stream.writeBytes(urlParameters);
				stream.flush();
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection.disconnect();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//Parse data for the stat display
	public static JSONArray getListOfGames(String jsonString)
	{
		try {
			JSONObject thisObject = new JSONObject (jsonString);
			JSONObject stats = thisObject.getJSONObject("stats");
			JSONArray games = stats.getJSONArray("Games");
			return games;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
}
