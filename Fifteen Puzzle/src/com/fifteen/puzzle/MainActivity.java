package com.fifteen.puzzle;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
        new Handler().postDelayed(new Runnable() {
            public void run() {
                final Intent mainIntent = new Intent(MainActivity.this, MainMenu.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 1000);
    }   
}
