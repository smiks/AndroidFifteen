package com.fifteen.puzzle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NajboljsiCasi extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_najboljsi_casi);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/backlash.ttf");
        TextView tvNaslov = (TextView) findViewById(R.id.najboljsiCasiHead);
        tvNaslov.setTypeface(tf);
        Button topnazaj = (Button) findViewById(R.id.topNazaj);
        topnazaj.setTypeface(tf);
        topnazaj.setOnClickListener(this);
        
        String FILEHS = "Highscore";
	    SharedPreferences hs = getSharedPreferences(FILEHS, 0);
	    int prvi,drugi,tretji;
	    prvi = (int)hs.getFloat("1", 0);
	    drugi = (int)hs.getFloat("2", 0);
	    tretji = (int)hs.getFloat("3", 0);
	    long prviCas = hs.getLong("c1", 0);
	    long drugiCas = hs.getLong("c2", 0);
	    long tretjiCas = hs.getLong("c3", 0);
	    TextView tv1 = (TextView) findViewById(R.id.t1);
	    TextView tv2 = (TextView) findViewById(R.id.t2);
	    TextView tv3 = (TextView) findViewById(R.id.t3);
	    tv1.setTypeface(tf);
	    tv2.setTypeface(tf);
	    tv3.setTypeface(tf);
    
	    tv1.setText("\n1. "+prviCas+"s "+prvi+" potez");
	    tv2.setText("2. "+drugiCas+"s "+drugi+" potez");
	    tv3.setText("3. "+tretjiCas+"s "+tretji+" potez");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_najboljsi_casi, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case R.id.menu_pocisti:
    	    String FILEHS = "Highscore";
    	    SharedPreferences hs = getSharedPreferences(FILEHS, 0);
    	   
    	   float prvi = 999;
    	   
    	   float  drugi= 999;
    	    
    	   float tretji= 999;  
    	    SharedPreferences.Editor editor = hs.edit();	
    		editor.putFloat("1",prvi);
    		editor.putFloat("2",drugi);
    		editor.putFloat("3",tretji);
    		editor.putLong("c1", 0);
    		editor.putLong("c2", 0);
    		editor.putLong("c3", 0);
    	    editor.commit();
    	    Toast.makeText(getApplicationContext(), " Seznam je pociscen. ",
					Toast.LENGTH_SHORT).show();
    	    Intent iNajboljsi = new Intent(this, NajboljsiCasi.class);
			startActivity(iNajboljsi);
    	   break;
        }
		return true;
	}

	public void onClick(View v) {
		if(v.getId()==R.id.topNazaj){
			Intent i = new Intent(this, MainMenu.class);
			startActivity(i);
		}
		
	}
}
