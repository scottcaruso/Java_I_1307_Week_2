/* Scott Caruso - Java 1307 - Week 2 Assignment
 * 
 * 7/18/2013
 */
package com.scottcaruso.utilities;

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
	
	//creates one JSON object based on the available data
	public static JSONObject createJSONObjectOne(Context context)
	{	
		Resources res = context.getResources();
		
		final JSONObject statDetails = new JSONObject();
		try {
			statDetails.put("Date", res.getString(com.scottcaruso.java_i_1307_week_2.R.string.gameone_date));
			statDetails.put("Opponent", res.getString(com.scottcaruso.java_i_1307_week_2.R.string.gameone_opponent));
			statDetails.put("At Bats", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gameone_atbats));
			statDetails.put("Hits", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gameone_hits));
			statDetails.put("Doubles", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gameone_doubles));
			statDetails.put("Triples", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gameone_triples));
			statDetails.put("Home Runs", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gameone_homeruns));
			statDetails.put("RBIs", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gameone_rbis));
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		return statDetails;
	}
	
	//creates second JSON object based on the available data
	public static JSONObject createJSONObjectTwo(Context context)
	{	
		Resources res = context.getResources();
		
		final JSONObject statDetails = new JSONObject();
		try {
			statDetails.put("Date", res.getString(com.scottcaruso.java_i_1307_week_2.R.string.gametwo_date));
			statDetails.put("Opponent", res.getString(com.scottcaruso.java_i_1307_week_2.R.string.gametwo_opponent));
			statDetails.put("At Bats", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gametwo_atbats));
			statDetails.put("Hits", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gametwo_hits));
			statDetails.put("Doubles", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gametwo_doubles));
			statDetails.put("Triples", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gametwo_triples));
			statDetails.put("Home Runs", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gametwo_homeruns));
			statDetails.put("RBIs", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gametwo_rbis));
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		return statDetails;
	}
	
	//creates third JSON object based on the available data
	public static JSONObject createJSONObjectThree(Context context)
	{	
		Resources res = context.getResources();
		
		final JSONObject statDetails = new JSONObject();
		try {
			statDetails.put("Date", res.getString(com.scottcaruso.java_i_1307_week_2.R.string.gamethree_date));
			statDetails.put("Opponent", res.getString(com.scottcaruso.java_i_1307_week_2.R.string.gamethree_opponent));
			statDetails.put("At Bats", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gamethree_atbats));
			statDetails.put("Hits", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gamethree_hits));
			statDetails.put("Doubles", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gamethree_doubles));
			statDetails.put("Triples", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gamethree_triples));
			statDetails.put("Home Runs", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gamethree_homeruns));
			statDetails.put("RBIs", res.getInteger(com.scottcaruso.java_i_1307_week_2.R.integer.gamethree_rbis));
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
		return statDetails;
	}
	
	//Creates one large JSON object out of hte smaller ones above
	public static JSONObject createMasterJSONObject(JSONObject one, JSONObject two, JSONObject three)
	{		
		final JSONObject thisObject = new JSONObject();
		try {
			thisObject.put("Game 1", one);
			thisObject.put("Game 2", two);
			thisObject.put("Game 3", three);
		} catch (JSONException e) {
			e.printStackTrace();
		}
			
		return thisObject;
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
