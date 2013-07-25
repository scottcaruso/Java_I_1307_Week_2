/* Scott Caruso - Java 1307 - Week 2 Assignment
 * 
 * 7/18/2013
 */
package com.scottcaruso.userinterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.scottcaruso.statcalculation.Averages;
import com.scottcaruso.utilities.JSONManagement;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.TextView;


/*This class is exclusively for creating the display that shows the statistics at the bottom of the screen when the user clicks the
 *appropriate button.
 */
public class CreateDisplay extends GridLayout {
	
	TextView date;
	TextView opponent;
	TextView atbats;
	TextView hits;
	TextView doubles;
	TextView triples;
	TextView homeruns;
	TextView rbi;
	Context context;
	
	
	public CreateDisplay(Context thisContext)
	{
		super(thisContext);
		String thisPersonsData = MainActivity.getResultText();
		JSONArray listOfGames = JSONManagement.getListOfGames(thisPersonsData);
		int numberOfGames = listOfGames.length()+1;
		
		context = thisContext;
		this.setColumnCount(numberOfGames);
		this.setRowCount(8);
		
		TextView dateLabel = new TextView(context);
		dateLabel.setText("Date");
		TextView opponentLabel = new TextView(context);
		opponentLabel.setText("Opponent");		
		TextView atbatLabel = new TextView(context);
		atbatLabel.setText("AB");		
		TextView hitsLabel = new TextView(context);
		hitsLabel.setText("H");		
		TextView doublesLabel = new TextView(context);
		doublesLabel.setText("2B");		
		TextView triplesLabel = new TextView(context);
		triplesLabel.setText("3B");		
		TextView homerunsLabel = new TextView(context);
		homerunsLabel.setText("HR");		
		TextView rbiLabel = new TextView(context);
		rbiLabel.setText("RBI");
		
		
		date = new TextView(context);
		opponent = new TextView(context);
		atbats = new TextView(context);
		hits = new TextView(context);
		doubles = new TextView(context);
		triples = new TextView(context);
		homeruns = new TextView(context);
		rbi = new TextView(context);
		
		this.addView(dateLabel);
		for (int x = 0; x < numberOfGames; x++)
		{
			try {
				TextView thisTextView = new TextView(context);
				String thisDate = listOfGames.getJSONObject(x).getString("Date");
				thisTextView.setText(thisDate);
				this.addView(thisTextView);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		//this.addView(date);
		this.addView(opponentLabel);
		for (int x = 0; x < numberOfGames; x++)
		{
			try {
				TextView thisTextView = new TextView(context);
				String thisOpponent = listOfGames.getJSONObject(x).getString("Opponent Name");
				thisTextView.setText(thisOpponent);
				this.addView(thisTextView);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		//this.addView(opponent);
		this.addView(atbatLabel);
		for (int x = 0; x < numberOfGames; x++)
		{
			try {
				TextView thisTextView = new TextView(context);
				String thisABs = listOfGames.getJSONObject(x).getString("AB");
				thisTextView.setText(thisABs);
				this.addView(thisTextView);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		//this.addView(atbats);
		this.addView(hitsLabel);
		for (int x = 0; x < numberOfGames; x++)
		{
			try {
				TextView thisTextView = new TextView(context);
				String thisHits = listOfGames.getJSONObject(x).getString("H");
				thisTextView.setText(thisHits);
				this.addView(thisTextView);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		//this.addView(hits);
		this.addView(doublesLabel);
		for (int x = 0; x < numberOfGames; x++)
		{
			try {
				TextView thisTextView = new TextView(context);
				String thisDoubles = listOfGames.getJSONObject(x).getString("2B");
				thisTextView.setText(thisDoubles);
				this.addView(thisTextView);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		//this.addView(doubles);
		this.addView(triplesLabel);
		for (int x = 0; x < numberOfGames; x++)
		{
			try {
				TextView thisTextView = new TextView(context);
				String thisTriples = listOfGames.getJSONObject(x).getString("3B");
				thisTextView.setText(thisTriples);
				this.addView(thisTextView);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		//this.addView(triples);
		this.addView(homerunsLabel);
		for (int x = 0; x < numberOfGames; x++)
		{
			try {
				TextView thisTextView = new TextView(context);
				String thisHomeruns = listOfGames.getJSONObject(x).getString("HR");
				thisTextView.setText(thisHomeruns);
				this.addView(thisTextView);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		//this.addView(homeruns);
		this.addView(rbiLabel);
		for (int x = 0; x < numberOfGames; x++)
		{
			try {
				TextView thisTextView = new TextView(context);
				String thisRBI = listOfGames.getJSONObject(x).getString("RBI");
				thisTextView.setText(thisRBI);
				this.addView(thisTextView);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		//this.addView(rbi);

	}
		
}
