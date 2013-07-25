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
		int numberOfGames = listOfGames.length();
		
		context = thisContext;
		this.setColumnCount(numberOfGames);
		this.setRowCount(8);
		
		TextView dateLabel = new TextView(context);
		dateLabel.setText("Date");
		date = new TextView(context);
		TextView opponentLabel = new TextView(context);
		opponentLabel.setText("Opponent");
		opponent = new TextView(context);
		TextView atbatLabel = new TextView(context);
		atbatLabel.setText("AB");
		atbats = new TextView(context);
		TextView hitsLabel = new TextView(context);
		hitsLabel.setText("H");
		hits = new TextView(context);
		TextView doublesLabel = new TextView(context);
		doublesLabel.setText("2B");
		doubles = new TextView(context);
		TextView triplesLabel = new TextView(context);
		triplesLabel.setText("3B");
		triples = new TextView(context);
		TextView homerunsLabel = new TextView(context);
		homerunsLabel.setText("HR");
		homeruns = new TextView(context);
		TextView rbiLabel = new TextView(context);
		rbiLabel.setText("RBI");
		rbi = new TextView(context);
	
		this.addView(dateLabel);
		this.addView(date);
		this.addView(opponentLabel);
		this.addView(opponent);
		this.addView(atbatLabel);
		this.addView(atbats);
		this.addView(hitsLabel);
		this.addView(hits);
		this.addView(doublesLabel);
		this.addView(doubles);
		this.addView(triplesLabel);
		this.addView(triples);
		this.addView(homerunsLabel);
		this.addView(homeruns);
		this.addView(rbiLabel);
		this.addView(rbi);

	}
		
}
