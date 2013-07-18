package com.scottcaruso.utilities;

import org.json.JSONException;
import org.json.JSONObject;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;

public class JSONManagement 
{
	
	//creates a JSON object based on the available data
	public static JSONObject createJSONObjectOne(Context context)
	{	
		Resources res = context.getResources();
		
		final JSONObject gameDetails = new JSONObject();
		final JSONObject statDetails = new JSONObject();
		//savedStatistics.put("Game", 1);
		try {
			statDetails.put("At Bats", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gameone_atbats));
			statDetails.put("Hits", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gameone_hits));
			statDetails.put("Doubles", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gameone_doubles));
			statDetails.put("Triples", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gameone_triples));
			statDetails.put("Home Runs", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gameone_homeruns));
			statDetails.put("RBIs", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gameone_rbis));
			gameDetails.put("Game 1", statDetails);
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		return gameDetails;
	}
	
	//Get the JSON data form the Cloudant service
	public static JSONObject getJSONFromDataSource()
	{
		return null;
	}
}
