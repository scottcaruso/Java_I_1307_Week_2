/* Scott Caruso
 * Java I 1307
 * Week 3 Assignment
 */
package com.scottcaruso.userinterface;

import org.json.JSONArray;
import org.json.JSONException;

import com.scottcaruso.utilities.JSONManagement;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.TextView;


//This class creates the grid layout that displays the stats. It has not been given an aesthetic pass yet.

public class CreateDisplay extends GridLayout {
	
	Context context;
	
	public CreateDisplay(Context thisContext)
	{
		super(thisContext);
		String thisPersonsData = MainActivity.getResultText();
		JSONArray listOfGames = JSONManagement.getListOfGames(thisPersonsData);
		int numberOfGames = listOfGames.length()+1;

		context = thisContext;
		this.setColumnCount(numberOfGames); //Set the number of columns based on the size of the games array in the JSON object.
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
		
		//reset the total stats for average calculation
		MainActivity.setTotalAtBats(0);
		MainActivity.setTotalDoubles(0);
		MainActivity.setTotalHits(0);
		MainActivity.setTotalTriples(0);
		MainActivity.setTotalHomers(0);
		
		//The below code creates views to add to the main view, and also increments statistics in the MainActivity to generate batting and slugging averages.
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
		this.addView(atbatLabel);
		for (int x = 0; x < numberOfGames; x++)
		{
			try {
				TextView thisTextView = new TextView(context);
				String thisABs = listOfGames.getJSONObject(x).getString("AB");
				thisTextView.setText(thisABs);
				int oldABs = MainActivity.getTotalAtBats();
				MainActivity.setTotalAtBats(oldABs + Integer.parseInt(thisABs));
				addView(thisTextView);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		this.addView(hitsLabel);
		for (int x = 0; x < numberOfGames; x++)
		{
			try {
				TextView thisTextView = new TextView(context);
				String thisHits = listOfGames.getJSONObject(x).getString("H");
				thisTextView.setText(thisHits);
				int oldHits = MainActivity.getTotalHits();
				MainActivity.setTotalHits(oldHits + Integer.parseInt(thisHits));
				this.addView(thisTextView);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		this.addView(doublesLabel);
		for (int x = 0; x < numberOfGames; x++)
		{
			try {
				TextView thisTextView = new TextView(context);
				String thisDoubles = listOfGames.getJSONObject(x).getString("2B");
				thisTextView.setText(thisDoubles);
				int oldDoubles = MainActivity.getTotalDoubles();
				MainActivity.setTotalDoubles(oldDoubles + Integer.parseInt(thisDoubles));
				this.addView(thisTextView);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		this.addView(triplesLabel);
		for (int x = 0; x < numberOfGames; x++)
		{
			try {
				TextView thisTextView = new TextView(context);
				String thisTriples = listOfGames.getJSONObject(x).getString("3B");
				thisTextView.setText(thisTriples);
				int oldTriples = MainActivity.getTotalTriples();
				MainActivity.setTotalTriples(oldTriples + Integer.parseInt(thisTriples));
				this.addView(thisTextView);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		this.addView(homerunsLabel);
		for (int x = 0; x < numberOfGames; x++)
		{
			try {
				TextView thisTextView = new TextView(context);
				String thisHomeruns = listOfGames.getJSONObject(x).getString("HR");
				thisTextView.setText(thisHomeruns);
				int oldHomers = MainActivity.getTotalHomers();
				MainActivity.setTotalHomers(oldHomers + Integer.parseInt(thisHomeruns));
				this.addView(thisTextView);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
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
	}		
}
