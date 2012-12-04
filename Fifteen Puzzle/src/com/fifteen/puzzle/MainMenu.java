package com.fifteen.puzzle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        
        //nastavitev fonta za gumbe
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/backlash.ttf");
        Button bIgra = (Button) findViewById(R.id.bIgra);
        bIgra.setTypeface(tf);
        
        Button bNajboljsi = (Button) findViewById(R.id.bNajboljsi);
        bNajboljsi.setTypeface(tf);
        Button bNastavitve = (Button) findViewById(R.id.bNastavitve);
        bNastavitve.setTypeface(tf);
        Button bPomoc = (Button) findViewById(R.id.bPomoc);
        bPomoc.setTypeface(tf);
        Button bVizitka = (Button) findViewById(R.id.bVizitka);
        bVizitka.setTypeface(tf);
        
        bIgra.setOnClickListener(this);
        
        bNajboljsi.setOnClickListener(this);
        bNastavitve.setOnClickListener(this);
        bPomoc.setOnClickListener(this);
        bVizitka.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bIgra:
			Intent iIgra = new Intent(this, Igra.class);
			startActivity(iIgra);
			break;		
		case R.id.bNajboljsi:
			Intent iNajboljsi = new Intent(this, NajboljsiCasi.class);
			startActivity(iNajboljsi);
			break;
		case R.id.bNastavitve:
			Intent iNastavitve = new Intent(this, Nastavitve.class);
			startActivity(iNastavitve);
			break;
		case R.id.bPomoc:
			Intent iPomoc = new Intent(this, Pomoc.class);
			startActivity(iPomoc);
			break;
		case R.id.bVizitka:
			Intent iVizitka = new Intent(this,Vizitka.class);
			//iVizitka.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);	
        	startActivity(iVizitka);
			break;
		}
	}
}
