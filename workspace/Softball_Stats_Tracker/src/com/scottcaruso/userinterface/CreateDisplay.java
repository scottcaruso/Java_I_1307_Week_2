/* Scott Caruso - Java 1307 - Week 2 Assignment
 * 
 * 7/18/2013
 */
package com.scottcaruso.userinterface;

import org.json.JSONException;
import org.json.JSONObject;

import com.scottcaruso.statcalculation.Averages;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;


/*This class is exclusively for creating the display that shows the statistics at the bottom of the screen when the user clicks the
 *appropriate button.
 */
public class CreateDisplay {
	
	public static TextView createStatDisplay(JSONObject object, Context context)
	{
		JSONObject firstObject = null;
		JSONObject secondObject = null;
		JSONObject thirdObject = null;
		try {
			firstObject = object.getJSONObject("Game 1");
			secondObject = object.getJSONObject("Game 2");
			thirdObject = object.getJSONObject("Game 3");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject [] arrayOfObjects = {firstObject,secondObject,thirdObject};
		
		TextView thisTextView = new TextView (context);
		thisTextView.setGravity(Gravity.CENTER);
		for (int x = 1; x <= arrayOfObjects.length; x++)
		{
			JSONObject currentObject = arrayOfObjects[x-1];
			String gameNumber = String.valueOf(x);
			String opponent = null;
			String date = null;
			String atBats = null;
			String hits = null;
			String doubles = null;
			String triples = null;
			String homeruns = null;
			String rbis = null;
			//The floats need to be configured to use the appropriate number of characters still.
			float average = 0.0f;
			float slugging = 0.0f;
			try {
				opponent = currentObject.getString("Opponent");
				date = currentObject.getString("Date");
				int intAtBats = currentObject.getInt("At Bats");
				atBats = String.valueOf(intAtBats);
				int intHits = currentObject.getInt("Hits");
				hits = String.valueOf(intHits);
				int intDoubles = currentObject.getInt("Doubles");
				doubles = String.valueOf(intDoubles);
				int intTriples = currentObject.getInt("Triples");
				triples = String.valueOf(intTriples);
				int intHomers = currentObject.getInt("Home Runs");
				homeruns = String.valueOf(intHomers);
				int intRBI = currentObject.getInt("RBIs");
				rbis = String.valueOf(intRBI);
				//Use Averages class to get BA and slugging percentages.
				average = Averages.battingAverage(intAtBats, intHits);
				slugging = Averages.sluggingPercentage(intAtBats, intHits, intDoubles, intTriples, intHomers);
			} catch (JSONException e) {
				String debugString = (opponent + " " + date + " " + atBats + " " + hits + " " + doubles + " " + triples + " " + homeruns + " " + rbis);
						Log.i("Strings: ",debugString);
			}
			thisTextView.append("Game #" + gameNumber + "\r\n"
								+ "Opponent: " + opponent + "\r\n"
								+ "Date: " + date + "\r\n"
								+ "At Bats: " + atBats + "  Hits: " + hits + "  Doubles: " + doubles + "  Triples: " + triples +
								"  Home Runs: " + homeruns + "  RBIs: " + rbis + 
								" Batting Average: " + average + "  Slugging: " + slugging + "\r\n");
		}
		
		return thisTextView;
	}
	
}
