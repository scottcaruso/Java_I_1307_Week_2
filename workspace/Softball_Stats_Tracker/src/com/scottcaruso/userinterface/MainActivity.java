/* Scott Caruso
 * Java I 1307
 * Week 3 Assignment
 */
package com.scottcaruso.userinterface;

import java.util.ArrayList;

import org.json.JSONObject;

import com.scottcaruso.java_i_1307_week_2.R;
import com.scottcaruso.statcalculation.Averages;
import com.scottcaruso.uielementgenerator.UIElementCreator;
import com.scottcaruso.utilities.Connection_Verification;
import com.scottcaruso.utilities.JSONManagement;
import com.scottcaruso.utilities.RetrieveData;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
//import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	static ArrayList<Spinner> spinners;
	static ArrayList<TextView> labels;
	TextView statView;
	private static String responseFromURL = "";
	private static String textThatWasEntered = "";
	static EditText userName;
	static Button searchForUserName;
	static Boolean exists = false;
	static LinearLayout mainLayout;
	static Context currentContext;
	static LayoutParams mainLayoutParams;
	
	//For calculating averages
	//Create setters to access these variables outside of the Activity
	static int totalAtBats;
	public static int getTotalAtBats() {
		return totalAtBats;
	}

	public static void setTotalAtBats(int atbats) {
		totalAtBats = atbats;
	}

	static int totalHits;
	public static int getTotalHits() {
		return totalHits;
	}

	public static void setTotalHits(int hits) {
		totalHits = hits;
	}

	static int totalDoubles;
	public static int getTotalDoubles() {
		return totalDoubles;
	}

	public static void setTotalDoubles(int doubles) {
		totalDoubles = doubles;
	}

	static int totalTriples;
	public static int getTotalTriples() {
		return totalTriples;
	}

	public static void setTotalTriples(int triples) {
		totalTriples = triples;
	}

	static int totalHomers;
	
    public static int getTotalHomers() {
		return totalHomers;
	}

	public static void setTotalHomers(int homers) {
		totalHomers = homers;
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	
        mainLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        
        //Start out with a simple entry field - Username and an action button
        final EditText userName = UIElementCreator.createTextEntryField(this, "Enter User Name");
        final Button searchForUserName = UIElementCreator.createButton(this, "Search for User Name", 1);
        
        //save these for easy use later
        setUserName(userName);
        setSearchForUserName(searchForUserName);
        
        //set click on button
        searchForUserName.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) 
			{
				//Check connection, then retrieve data to verify if this element exists or not
				boolean connection = Connection_Verification.areWeConnected(MainActivity.this);
				if (connection)
				{
					textThatWasEntered = userName.getText().toString();
					setCurrentContext(MainActivity.this);
					RetrieveData.retrieveCloudantData("http://scottcaruso.cloudant.com/softballstats/_all_docs?include_docs=true");
				} else
				{
					Toast toast = Toast.makeText(MainActivity.this, "No Internet Connection!", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
        
        //Create a debug text view to provide help to the instructor
        TextView help = UIElementCreator.createTextEntryField(this, "For testing purposes, please use the account name 'testuser'. Functionality has not been added to support creating new accounts.")
;
        mainLayout.addView(userName);
        mainLayout.addView(searchForUserName);
        mainLayout.addView(help);
        
        //Add the layout to the scroll view and show it
        setContentView(mainLayout);
        
    }
	
	//Static method that allows this view to be updated from the Create Display class
	public static void updateView(CreateDisplay newDisplay)
	{
		mainLayout.removeAllViews();
		mainLayout.addView(newDisplay);
		final Button addNewGame = UIElementCreator.createButton(getCurrentContext(), "Add New Game", 1);
		float battingAverage = Averages.battingAverage(totalAtBats, totalHits);
		float sluggingPercent = Averages.sluggingPercentage(totalAtBats, totalHits, totalDoubles, totalTriples, totalHomers);
		TextView averageLabel = UIElementCreator.createLabel(MainActivity.getCurrentContext(), "Batting Average: " + String.valueOf(battingAverage));
		TextView sluggingLabel = UIElementCreator.createLabel(MainActivity.getCurrentContext(), "Slugging Percentage: " + String.valueOf(sluggingPercent));
		mainLayout.addView(averageLabel);
		mainLayout.addView(sluggingLabel);
		mainLayout.addView(addNewGame);
		mainLayout.setOrientation(LinearLayout.VERTICAL);
		addNewGame.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addGameScreen(getCurrentContext());
			}
		});
	}
	
	//Static method that allows this screen to be updated with the Add Game interface
	public static void addGameScreen(Context context)
	{
		mainLayout.removeAllViews();
    	Resources res = context.getResources();
		
    	spinners = new ArrayList<Spinner>();
        labels = new ArrayList<TextView>();
        String[] genericStringArray = res.getStringArray(R.array.numericalvalues);
        
        //Create Date Picker
        final DatePicker calendar = UIElementCreator.createDatePicker(context);
        TextView calendarLabel = UIElementCreator.createLabel(context, "Date of Game");
        
        //Create Edit Team Field
        final EditText teamEntry = UIElementCreator.createTextEntryField(context, "Enter Opponent Name");
        TextView entryLabel = UIElementCreator.createLabel(context, "Opposing Team's Name");
        
        //Create At Bats and add them to the appropriate array
        final Spinner atBatsSpinner = UIElementCreator.createSpinner(context, genericStringArray);
        TextView atBatsLabel = UIElementCreator.createLabel(context, "At Bats");
        spinners.add(atBatsSpinner);
        labels.add(atBatsLabel);
        
        //Create Hits and add them to the appropriate array
        final Spinner hitsSpinner = UIElementCreator.createSpinner(context, genericStringArray);
        TextView hitsLabel = UIElementCreator.createLabel(context, "Hits");
        spinners.add(hitsSpinner);
        labels.add(hitsLabel);
        
        //Create Doubles and add them to the appropriate array
        final Spinner doublesSpinner = UIElementCreator.createSpinner(context, genericStringArray);
        TextView doublesLabel = UIElementCreator.createLabel(context, "Doubles");
        spinners.add(doublesSpinner);
        labels.add(doublesLabel);
        
        //Create Triples and add them to the appropriate array
        final Spinner triplesSpinner = UIElementCreator.createSpinner(context, genericStringArray);
        TextView triplesLabel = UIElementCreator.createLabel(context, "Triples");
        spinners.add(triplesSpinner);
        labels.add(triplesLabel);
        
        //Create Home Runs and add them to the appropriate array
        final Spinner homerunsSpinner = UIElementCreator.createSpinner(context, genericStringArray);
        TextView homerunsLabel = UIElementCreator.createLabel(context, "Home Runs");
        spinners.add(homerunsSpinner);
        labels.add(homerunsLabel);
        
        //Create RBIs and add them to the appropriate array
        final Spinner rbiSpinner = UIElementCreator.createSpinner(context, genericStringArray);
        TextView rbiLabel = UIElementCreator.createLabel(context, "RBIs");
        spinners.add(rbiSpinner);
        labels.add(rbiLabel);
        
        //Add the views that don't exist within arrays
        mainLayout.addView(calendarLabel);
        mainLayout.addView(calendar);
        mainLayout.addView(entryLabel);
        mainLayout.addView(teamEntry);
        
        LinearLayout rowOne = new LinearLayout(context);
        LinearLayout rowTwo = new LinearLayout(context);
        LayoutParams statParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        rowOne.setLayoutParams(statParams);
        rowTwo.setLayoutParams(statParams);
        
        //Loop through arrays and create spinner elements for Main Menu
        try {
			if (spinners.size() == labels.size())
			{
			    for (int x = 1; x <= spinners.size()-3; x++)
			    {
			    	rowOne.addView(labels.get(x-1));
			    	rowOne.addView(spinners.get(x-1));
			    }
			    for (int x = spinners.size()-2; x <= spinners.size(); x++)
			    {
			    	rowTwo.addView(labels.get(x-1));
			    	rowTwo.addView(spinners.get(x-1));
			    }
			}
			Button addStats = UIElementCreator.createButton(getCurrentContext(), "Add Game", 1);
			Button returnToView = UIElementCreator.createButton(getCurrentContext(), "Back", 2);
			
			addStats.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					int Month = calendar.getMonth();
					int Day = calendar.getDayOfMonth();
					int Year = calendar.getYear();
					String date = String.valueOf(Month) + "/" + String.valueOf(Day) + "/" + String.valueOf(Year);
					JSONObject newGame = JSONManagement.createJSONObject(date, teamEntry.getText().toString(), atBatsSpinner.getSelectedItem().toString(), hitsSpinner.getSelectedItem().toString(), doublesSpinner.getSelectedItem().toString(), triplesSpinner.getSelectedItem().toString(), homerunsSpinner.getSelectedItem().toString(), rbiSpinner.getSelectedItem().toString());
					JSONManagement.addObjectToLiveArray(newGame);
				}
			});
			
			returnToView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast toast = Toast.makeText(getCurrentContext(), "Not Yet Configured! Check back next week!", Toast.LENGTH_SHORT);
					toast.show();	
				}
			});
			
			mainLayout.addView(rowOne);
			mainLayout.addView(rowTwo);
			mainLayout.addView(addStats);
			mainLayout.addView(returnToView);

		} catch (IndexOutOfBoundsException e) {
			Log.d("Crash:","Index Out Of Bounds Error");
		}
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	//Various getters and setters for passing data to and from the Main Activity
	public static String getTextThatWasEntered() 
	{
		return textThatWasEntered;
	}
	
	public static void setResultText(String text) 
	{
		responseFromURL = text;
	}
	
	public static String getResultText() 
	{
		return responseFromURL;
	}
	
	public static void setExists (Boolean bool)
	{
		exists = bool;
	}
	
	public static void setCurrentContext (Context context)
	{
		currentContext = context;
	}
	
	public static Context getCurrentContext ()
	{
		return currentContext;
	}
	
    public static EditText getUserName() {
		return userName;
	}

	public static void setUserName(EditText thisName) {
		userName = thisName;
	}

	public static Button getSearchForUserName() {
		return searchForUserName;
	}

	public static void setSearchForUserName(Button thisSearchFor) {
		searchForUserName = thisSearchFor;
	}

	public static void updateView(LinearLayout newLayout)
	{
		mainLayout.removeAllViews();
		mainLayout.addView(newLayout);
	}
	
}
