package com.scottcaruso.java_i_1307_week_2;

import java.util.ArrayList;
import java.util.HashMap;

import com.scottcaruso.lib_practice.FormThings;
import com.scottcaruso.practiceecom.Money;
import com.scottcaruso.practiceecom.Phone;
import com.scottcaruso.practiceecom.Product;

import android.os.Bundle;
import android.app.Activity;
//import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout ll = new LinearLayout(this);
        LinearLayout entryBox = FormThings.singleEntryWithButton(this, "Type Something", "Click Me!");
      
        Button moneyButton = (Button) entryBox.findViewById(2);
        
        moneyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				EditText money = (EditText) v.getTag();
				//Editable textEntry = money.getText();
				//String textString = textEntry.toString();
				
				double moneyDouble = Double.parseDouble(money.getText().toString());
				HashMap<Money, Integer> change = Money.getChange(moneyDouble);
				
				Log.i("BUTTON CLICKED",
						"Dollar: " + change.get(Money.DOLLAR) + "\r\n" +
						"Quarter: " + change.get(Money.QUARTER) + "\r\n" +
						"Dime: " + change.get(Money.DIME) + "\r\n" +
						"Nickel: " + change.get(Money.NICKEL) + "\r\n" +
						"Penny: " + change.get(Money.PENNY) + "\r\n"
						);	
			}
		});
        
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Phone("iPhone 4S", 99.99));
        products.add(new Phone("iPhone 5", 199.99));
        products.add(new Phone("Galaxy S3", 199.99));
        products.add(new Phone("Galaxy Nexus", 349.99));
        products.add(new Phone("HTC One X", 99.99));
        
        ll.addView(entryBox);
        
        setContentView(ll);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
