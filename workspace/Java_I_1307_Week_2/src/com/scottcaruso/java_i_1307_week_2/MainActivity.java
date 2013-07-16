package com.scottcaruso.java_i_1307_week_2;

import com.scottcaruso.lib_practice.FormThings;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout ll = new LinearLayout(this);
        LinearLayout entryBox = FormThings.singleEntryWithButton(this, "Type Something", "Click Me!");
    
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
