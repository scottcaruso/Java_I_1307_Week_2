package com.scottcaruso.userinterface;

import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.scottcaruso.formgenerator.FormGenerator;
import com.scottcaruso.java_i_1307_week_2.R;
import com.scottcaruso.practiceecom.Money;
import com.scottcaruso.practiceecom.Phone;
import com.scottcaruso.practiceecom.Product;
import com.scottcaruso.utilities.JSONManagement;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.util.AttributeSet;
//import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	//RadioGroup productOptions;
	//ArrayList<Product> products;
	
	//get resources data to use in various layouts
	
	ArrayList<Spinner> spinners;
	ArrayList<TextView> labels;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	Resources res = getResources();
        
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(res.getColor(android.R.color.transparent));
        LayoutParams mainLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        scroll.setLayoutParams(mainLayoutParams);
        
        spinners = new ArrayList<Spinner>();
        labels = new ArrayList<TextView>();
        LinearLayout mainLayout = new LinearLayout(this);
        //mainLayout.setLayoutParams(mainLayoutParams);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        String[] genericStringArray = res.getStringArray(R.array.numericalvalues);
        
        //Create Date Picker
        DatePicker calendar = FormGenerator.createDatePicker(this);
        TextView calendarLabel = FormGenerator.createLabel(this, "Date of Game");
        
        //Create Edit Team Field
        EditText teamEntry = FormGenerator.createTextEntryField(this, "Enter Opponent Name");
        TextView entryLabel = FormGenerator.createLabel(this, "Opposing Team's Name");
        
        //Create At Bats
        Spinner atBatsSpinner = FormGenerator.createSpinner(this, genericStringArray);
        TextView atBatsLabel = FormGenerator.createLabel(this, "At Bats");
        spinners.add(atBatsSpinner);
        labels.add(atBatsLabel);
        
        //Create Hits
        Spinner hitsSpinner = FormGenerator.createSpinner(this, genericStringArray);
        TextView hitsLabel = FormGenerator.createLabel(this, "Hits");
        spinners.add(hitsSpinner);
        labels.add(hitsLabel);
        
        //Create Doubles
        Spinner doublesSpinner = FormGenerator.createSpinner(this, genericStringArray);
        TextView doublesLabel = FormGenerator.createLabel(this, "Doubles");
        spinners.add(doublesSpinner);
        labels.add(doublesLabel);
        
        //Create Triples
        Spinner triplesSpinner = FormGenerator.createSpinner(this, genericStringArray);
        TextView triplesLabel = FormGenerator.createLabel(this, "Triples");
        spinners.add(triplesSpinner);
        labels.add(triplesLabel);
        
        //Create Home Runs
        Spinner homerunsSpinner = FormGenerator.createSpinner(this, genericStringArray);
        TextView homerunsLabel = FormGenerator.createLabel(this, "Home Runs");
        spinners.add(homerunsSpinner);
        labels.add(homerunsLabel);
        
        //Create RBIs
        Spinner rbiSpinner = FormGenerator.createSpinner(this, genericStringArray);
        TextView rbiLabel = FormGenerator.createLabel(this, "RBIs");
        spinners.add(rbiSpinner);
        labels.add(rbiLabel);
        
        mainLayout.addView(calendarLabel);
        mainLayout.addView(calendar);
        
        mainLayout.addView(entryLabel);
        mainLayout.addView(teamEntry);
        
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
        
       
        /*LinearLayout mainLayout = new LinearLayout(this);
        LinearLayout entryBox = FormGenerator.singleEntryWithButton(this, "Type Something", "Click Me!");
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        mainLayout.setLayoutParams(lp);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        Button moneyButton = (Button) entryBox.findViewById(2);
        
        moneyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int selectedRadioID = productOptions.getCheckedRadioButtonId();
				String selectedRadioString = String.valueOf(selectedRadioID);
				Log.i("DEBUG:",selectedRadioString);
				
				RadioButton selectedRadio = (RadioButton) productOptions.findViewById(selectedRadioID);
				String radiotext = (String) selectedRadio.getText();
				
				double price = 0;
				for(int i = 0; i<products.size(); i++)
				{
					if (radiotext.compareTo(products.get(i).getName()) == 0) 
					{
						price = products.get(i).getPrice();
					}
				}
				
				EditText money = (EditText) v.getTag();
				//Editable textEntry = money.getText();
				//String textString = textEntry.toString();
				
				double moneyDouble = Double.parseDouble(money.getText().toString());
				double refund = moneyDouble - price;
				HashMap<Money, Integer> change = Money.getChange(refund);
				
				Log.i("BUTTON CLICKED",
						"Dollar: " + change.get(Money.DOLLAR) + "\r\n" +
						"Quarter: " + change.get(Money.QUARTER) + "\r\n" +
						"Dime: " + change.get(Money.DIME) + "\r\n" +
						"Nickel: " + change.get(Money.NICKEL) + "\r\n" +
						"Penny: " + change.get(Money.PENNY) + "\r\n"
						);	
			}
		});
        
        products = new ArrayList<Product>();
        products.add(new Phone("iPhone 4S", 99.99));
        products.add(new Phone("iPhone 5", 199.99));
        products.add(new Phone("Galaxy S3", 199.99));
        products.add(new Phone("Galaxy Nexus", 349.99));
        products.add(new Phone("HTC One X", 99.99));
        
        String[] productNames = new String[products.size()];
        for (int i=0; i<products.size(); i++)
        {
        	productNames[i] = products.get(i).getName();
        }
        
        RadioGroup productOptions = FormGenerator.getOptions(this, productNames);
        
        mainLayout.addView(productOptions);
        
        mainLayout.addView(entryBox);*/
        
        Button addNewGameButton = FormGenerator.createButton(this, "Add Game With These Stats", 1);
        Button displayStats = FormGenerator.createButton(this, "Display Saved Stats", 2);
        
        mainLayout.addView(addNewGameButton);
        mainLayout.addView(displayStats);
        
        scroll.addView(mainLayout);
        setContentView(scroll);
        
        //Generate a dummy/static JSON object with statistics in it.
        JSONObject objectOne = JSONManagement.createJSONObjectOne(this);
        JSONObject objectTwo = JSONManagement.createJSONObjectTwo(this);
        JSONObject objectThree = JSONManagement.createJSONObjectThree(this);
        JSONObject stats = JSONManagement.createMasterJSONObject(objectOne, objectTwo, objectThree);
        
        TextView statView = CreateDisplay.createStatDisplay(stats, this);
        mainLayout.addView(statView);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
