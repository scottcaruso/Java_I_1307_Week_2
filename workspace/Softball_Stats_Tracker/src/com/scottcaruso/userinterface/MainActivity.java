/* Scott Caruso - Java 1307 - Week 2 Assignment
 * 
 * 7/18/2013
 */
package com.scottcaruso.userinterface;

import java.util.ArrayList;

import org.json.JSONObject;

import com.scottcaruso.java_i_1307_week_2.R;
import com.scottcaruso.uielementgenerator.UIElementCreator;
import com.scottcaruso.utilities.JSONManagement;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
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

	//RadioGroup productOptions;
	//ArrayList<Product> products;
	
	
	ArrayList<Spinner> spinners;
	ArrayList<TextView> labels;
	TextView statView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	Resources res = getResources();
        
    	//Create a scrollview to hold the main view, and make it the appropriate size.
        final ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(res.getColor(android.R.color.transparent));
        LayoutParams mainLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        scroll.setLayoutParams(mainLayoutParams);
        
        final LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        
        spinners = new ArrayList<Spinner>();
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
        
        //Add the layout to the scroll view and show it
        scroll.addView(mainLayout);
        setContentView(scroll);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
