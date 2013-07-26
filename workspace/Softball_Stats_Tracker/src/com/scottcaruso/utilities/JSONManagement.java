/* Scott Caruso
 * Java I 1307
 * Week 3 Assignment
 */
package com.scottcaruso.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.scottcaruso.userinterface.CreateDisplay;
import com.scottcaruso.userinterface.MainActivity;


public class JSONManagement 
{
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
	
	//NOTE - most of this is NOT working. I need to debug the Cloudant API to find out where I've screwed up, but am unlikely to finish prior to EOD Thursday.
	//I've commented much of it out for now so I can at least demonstrate posting this data to an appended JSON Object within the application.
	public static void addObjectToLiveArray(JSONObject toadd)
	{
		try {
			JSONObject master = new JSONObject(MainActivity.getResultText());
			master.getJSONObject("stats").getJSONArray("Games").put(toadd);
			MainActivity.setResultText(master.toString());
			CreateDisplay statDisplay = new CreateDisplay(MainActivity.getCurrentContext());
			MainActivity.updateView(statDisplay);
		} catch (JSONException e) {
			e.printStackTrace();
		}
			/*String id = master.getString("_id");
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
