/* Scott Caruso - Java 1307 - Week 2 Assignment
 * 
 * 7/18/2013
 */
package com.scottcaruso.userinterface;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Currency;

import org.json.JSONException;
import org.json.JSONObject;

import com.scottcaruso.java_i_1307_week_2.R;
import com.scottcaruso.uielementgenerator.UIElementCreator;
import com.scottcaruso.utilities.Connection_Verification;
import com.scottcaruso.utilities.JSONManagement;
import com.scottcaruso.utilities.RetrieveData;

import android.media.ExifInterface;
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
import android.widget.ScrollView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;

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
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	//Create a scrollview to hold the main view, and make it the appropriate size.
        mainLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        
        //Start out with a simple entry field - Username
        final EditText userName = UIElementCreator.createTextEntryField(this, "Enter User Name");
        final Button searchForUserName = UIElementCreator.createButton(this, "Search for User Name", 1);
        setUserName(userName);
        setSearchForUserName(searchForUserName);
        searchForUserName.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) 
			{
				//JSON method to verify if this element exists or not
				textThatWasEntered = userName.getText().toString();
				setCurrentContext(MainActivity.this);
				RetrieveData.retrieveCloudantData("http://scottcaruso.cloudant.com/softballstats/_all_docs?include_docs=true");
			}
		});
        
        mainLayout.addView(userName);
        mainLayout.addView(searchForUserName);
        
        
        /*spinners = new ArrayList<Spinner>();
        labels = new ArrayList<TextView>();
        String[] genericStringArray = res.getStringArray(R.array.numericalvalues);
        
        //Create Date Picker
        DatePicker calendar = UIElementCreator.createDatePicker(this);
        TextView calendarLabel = UIElementCreator.createLabel(this, "Date of Game");
        
        //Create Edit Team Field
        EditText teamEntry = UIElementCreator.createTextEntryField(this, "Enter Opponent Name");
        TextView entryLabel = UIElementCreator.createLabel(this, "Opposing Team's Name");
        
        //Create At Bats and add them to the appropriate array
        Spinner atBatsSpinner = UIElementCreator.createSpinner(this, genericStringArray);
        TextView atBatsLabel = UIElementCreator.createLabel(this, "At Bats");
        spinners.add(atBatsSpinner);
        labels.add(atBatsLabel);
        
        //Create Hits and add them to the appropriate array
        Spinner hitsSpinner = UIElementCreator.createSpinner(this, genericStringArray);
        TextView hitsLabel = UIElementCreator.createLabel(this, "Hits");
        spinners.add(hitsSpinner);
        labels.add(hitsLabel);
        
        //Create Doubles and add them to the appropriate array
        Spinner doublesSpinner = UIElementCreator.createSpinner(this, genericStringArray);
        TextView doublesLabel = UIElementCreator.createLabel(this, "Doubles");
        spinners.add(doublesSpinner);
        labels.add(doublesLabel);
        
        //Create Triples and add them to the appropriate array
        Spinner triplesSpinner = UIElementCreator.createSpinner(this, genericStringArray);
        TextView triplesLabel = UIElementCreator.createLabel(this, "Triples");
        spinners.add(triplesSpinner);
        labels.add(triplesLabel);
        
        //Create Home Runs and add them to the appropriate array
        Spinner homerunsSpinner = UIElementCreator.createSpinner(this, genericStringArray);
        TextView homerunsLabel = UIElementCreator.createLabel(this, "Home Runs");
        spinners.add(homerunsSpinner);
        labels.add(homerunsLabel);
        
        //Create RBIs and add them to the appropriate array
        Spinner rbiSpinner = UIElementCreator.createSpinner(this, genericStringArray);
        TextView rbiLabel = UIElementCreator.createLabel(this, "RBIs");
        spinners.add(rbiSpinner);
        labels.add(rbiLabel);
        
        //Add the views that don't exist within arrays
        mainLayout.addView(calendarLabel);
        mainLayout.addView(calendar);
        mainLayout.addView(entryLabel);
        mainLayout.addView(teamEntry);
        
        //Loop through arrays and create spinner elements for Main Menu
        try {
			if (spinners.size() == labels.size())
			{
			    for (int x = 1; x <= spinners.size(); x++)
			    {
			    	mainLayout.addView(labels.get(x-1));
			    	mainLayout.addView(spinners.get(x-1));
			    }	
			}
		} catch (IndexOutOfBoundsException e) {
			Log.d("Crash:","Index Out Of Bounds Error");
		}
        
        //Create buttons for bottom of the screen and give them functionality
        Button addNewGameButton = UIElementCreator.createButton(this, "Add Game With These Stats", 1);
        Button displayStats = UIElementCreator.createButton(this, "Display Saved Stats - Below", 2);
        addNewGameButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//This one just proves that the click works. Functionality will come later.
				Log.i("Clicked!","This button will be hooked up when deeper functionality is attached.");
			}
		});
        
        displayStats.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//This loop removes any existing statView so that it doesn't duplicate on a second button press.
			    try {
					mainLayout.removeView(statView);
				} catch (Exception e) {
					e.printStackTrace();
				}		       
				//Generate a dummy/static JSON object with statistics in it, then display at the screen's bottom.
				JSONObject objectOne = JSONManagement.createJSONObjectOne(MainActivity.this);
				JSONObject objectTwo = JSONManagement.createJSONObjectTwo(MainActivity.this);
				JSONObject objectThree = JSONManagement.createJSONObjectThree(MainActivity.this);
				JSONObject stats = JSONManagement.createMasterJSONObject(objectOne, objectTwo, objectThree);
				statView = CreateDisplay.createStatDisplay(stats, MainActivity.this);
				mainLayout.addView(statView);
			}
		});
        
        //Add the buttons to the bottom of the layout
        mainLayout.addView(addNewGameButton);
        mainLayout.addView(displayStats);
        
        boolean connectionState = Connection_Verification.areWeConnected(this);
        Log.i("State",String.valueOf(connectionState));
        
        //String responseString = RetrieveData.getResponse("https://scottcaruso.cloudant.com/softballstats/_all_docs");
        //Log.i("State",responseString);
       
        RetrieveData rd = new RetrieveData();
        rd.retrieveCloudantData("http://scottcaruso:navybean@scottcaruso.cloudant.com/softballstats/6408c75b31783f5000e49b402838d5e5");
        */
        
        //Add the layout to the scroll view and show it
        setContentView(mainLayout);
        
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
	
	public static void updateView(CreateDisplay newDisplay)
	{
		mainLayout.removeAllViews();
		mainLayout.addView(newDisplay);
		final Button addNewGame = UIElementCreator.createButton(getCurrentContext(), "Add New Game", 1);
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
	
	public static void addGameScreen(Context context)
	{
		mainLayout.removeAllViews();
    	Resources res = context.getResources();
		
    	spinners = new ArrayList<Spinner>();
        labels = new ArrayList<TextView>();
        String[] genericStringArray = res.getStringArray(R.array.numericalvalues);
        
        //Create Date Picker
        DatePicker calendar = UIElementCreator.createDatePicker(context);
        TextView calendarLabel = UIElementCreator.createLabel(context, "Date of Game");
        
        //Create Edit Team Field
        EditText teamEntry = UIElementCreator.createTextEntryField(context, "Enter Opponent Name");
        TextView entryLabel = UIElementCreator.createLabel(context, "Opposing Team's Name");
        
        //Create At Bats and add them to the appropriate array
        Spinner atBatsSpinner = UIElementCreator.createSpinner(context, genericStringArray);
        TextView atBatsLabel = UIElementCreator.createLabel(context, "At Bats");
        spinners.add(atBatsSpinner);
        labels.add(atBatsLabel);
        
        //Create Hits and add them to the appropriate array
        Spinner hitsSpinner = UIElementCreator.createSpinner(context, genericStringArray);
        TextView hitsLabel = UIElementCreator.createLabel(context, "Hits");
        spinners.add(hitsSpinner);
        labels.add(hitsLabel);
        
        //Create Doubles and add them to the appropriate array
        Spinner doublesSpinner = UIElementCreator.createSpinner(context, genericStringArray);
        TextView doublesLabel = UIElementCreator.createLabel(context, "Doubles");
        spinners.add(doublesSpinner);
        labels.add(doublesLabel);
        
        //Create Triples and add them to the appropriate array
        Spinner triplesSpinner = UIElementCreator.createSpinner(context, genericStringArray);
        TextView triplesLabel = UIElementCreator.createLabel(context, "Triples");
        spinners.add(triplesSpinner);
        labels.add(triplesLabel);
        
        //Create Home Runs and add them to the appropriate array
        Spinner homerunsSpinner = UIElementCreator.createSpinner(context, genericStringArray);
        TextView homerunsLabel = UIElementCreator.createLabel(context, "Home Runs");
        spinners.add(homerunsSpinner);
        labels.add(homerunsLabel);
        
        //Create RBIs and add them to the appropriate array
        Spinner rbiSpinner = UIElementCreator.createSpinner(context, genericStringArray);
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

	public static String getTextThatWasEntered() 
	{
		return textThatWasEntered;
	}
	
	public static void setResultText(String text) 
	{
		responseFromURL = text;
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
	
}
