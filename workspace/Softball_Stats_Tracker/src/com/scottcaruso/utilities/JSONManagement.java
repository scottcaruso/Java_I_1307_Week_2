/* Scott Caruso
 * Java I 1307
 * Week 3 Assignment
 */
package com.scottcaruso.utilities;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.scottcaruso.userinterface.CreateDisplay;
import com.scottcaruso.userinterface.MainActivity;


public class JSONManagement 
{
	
	static String id;
	static JSONObject master;
	
	//creates a "game" JSON object based on data passed from the context from which it is called
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
	
	//NOTE - I've only gotten post to work once. I have no idea how.
	//Everything seems fine, but I can't seem to find why its not actually putting the new data in.
	//I've commented much of it out for now so I can at least demonstrate posting this data to an appended JSON Object within the application.
	
	//
	public static void addObjectToLiveArray(JSONObject toadd)
	{
		try 
		{
			master = new JSONObject(MainActivity.getResultText());
			master.getJSONObject("stats").getJSONArray("Games").put(toadd);
			master.remove("_rev");
			MainActivity.setResultText(master.toString());
			CreateDisplay statDisplay = new CreateDisplay(MainActivity.getCurrentContext());
			id = master.getString("_id");
			MainActivity.updateView(statDisplay);
		} catch (JSONException e) {
			e.printStackTrace();
		}
        final String server = "scottcaruso.cloudant.com";

        URL url = null;
        try {
            url = new URL("http://" + server + "/softballstats/" + id);
        } catch (MalformedURLException ex) {
        	Log.d("Error","There was a problem with your URL construction.");
        }

        HttpURLConnection newConnection = null;
        try {
            // URL connection channel.
            newConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException ex) {
        	Log.d("Error","There was a problem with the connection.");
        }

        // Let the run-time system (RTS) know that we want input.
        newConnection.setDoInput (true);

        // Let the RTS know that we want to do output.
        newConnection.setDoOutput (true);

        // No caching, we want the real thing.
        newConnection.setUseCaches (false);

        try {
        	newConnection.setRequestMethod("PUT");
        } catch (ProtocolException ex) {
        	Log.d("Error","There was a problem with the request method.");        }

        // Specify the content type if needed.
    	newConnection.setRequestProperty("Content-Type","application/json");
        
        try {
        	newConnection.connect();
        } catch (IOException ex) {
        	Log.d("Error","There was a problem with connecting.");   
        }

        DataOutputStream output = null;
        //DataInputStream input = null;

        try {
            output = new DataOutputStream(newConnection.getOutputStream());
        } catch (IOException ex) {
        	Log.d("Error","There was a problem with the output stream.");  
        }

        // Construct the POST data.
        String content = master.toString();

        // Send the request data.
        try {
            output.writeBytes(content);
            output.flush();
            output.close();
        } catch (IOException ex) {
        	Log.d("Error","There was a problem with the output stream.");  
        }
        
        /* Get response data.
        String str = null;
        try {
        	PostRequest pr = new PostRequest();
        	pr.execute(url);
            input = new DataInputStream (newConnection.getInputStream());
            while (null != ((str = input.readUTF()))) {
                Log.i("Response",str);
            }
            input.close ();
        } catch (IOException ex) {
        	Log.d("Error","There was a problem with the output stream.");  
        }*/
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

