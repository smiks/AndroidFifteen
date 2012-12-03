package com.fifteen.puzzle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Vizitka extends Activity implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizitka);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/backlash.ttf");
        TextView tvNaslov = (TextView) findViewById(R.id.tvVizitkaHead);
        tvNaslov.setTypeface(tf);
        TextView tvVsebina = (TextView) findViewById(R.id.tvVizitka);
        tvVsebina.setTypeface(tf);        
        
        Button bNazaj = (Button)findViewById(R.id.bNazaj);
        bNazaj.setTypeface(tf);
        bNazaj.setOnClickListener(this);
    }  

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.bNazaj){
			Intent i = new Intent(this, MainMenu.class);
			startActivity(i);
		}
	}
}
