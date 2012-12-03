package com.fifteen.puzzle;

import java.util.HashMap;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Igra extends Activity {

	private HashMap<Integer, Integer> mButtonIds;
	private Polje mPolje;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_igra);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/backlash.ttf");
        TextView tvNaslov = (TextView)findViewById(R.id.igraHead);
        tvNaslov.setTypeface(tf);
        
        init();
    }
    
    private void init() {
    	
    	mPolje = new Polje(3);
    	
    	mButtonIds = new HashMap<Integer, Integer>();
    	mButtonIds.put(R.id.b1,1);
    	mButtonIds.put(R.id.b2,2);
    	mButtonIds.put(R.id.b3,3);
    	mButtonIds.put(R.id.b4,4);
    	mButtonIds.put(R.id.b5,5);
    	mButtonIds.put(R.id.b6,6);
    	mButtonIds.put(R.id.b7,7);
    	mButtonIds.put(R.id.b8,8);
    	mButtonIds.put(R.id.b9,9);
    	
    	for(int buttonId : mButtonIds.keySet()){
    		((Button) findViewById(buttonId)).setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					int buton = mButtonIds.get(v.getId());
					int i = (buton-1) / Polje.SIZE + 1;
					int j = buton % Polje.SIZE + (buton % Polje.SIZE == 0 ? 3 : 0);
					boolean finished = mPolje.move(i, j);
					ponovnoNarisi(finished);
				}
			});
    	}
    	
	}

    public void ponovnoNarisi(boolean finished){
    	
    	for (int id : mButtonIds.keySet()){
    		int buton = mButtonIds.get(id);
			int i = (buton-1) / Polje.SIZE + 1;
			int j = buton % Polje.SIZE + (buton % Polje.SIZE == 0 ? 3 : 0);
			int stevilokmorebitnot = mPolje.getValueOn(i, j);
			if (stevilokmorebitnot == Polje.EMPTY_FIELD){
				((Button)findViewById(id)).setVisibility(View.INVISIBLE);
			}else{
				((Button)findViewById(id)).setText(""+stevilokmorebitnot);
				((Button)findViewById(id)).setVisibility(View.VISIBLE);
			}
    	}
    }
    
    private int getIdFromInt(int i){
    	for (int id: mButtonIds.keySet()){
    		if (mButtonIds.get(id) == i){
    			return id;
    		}
    	}
    	return -1;
    }
    
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_igra, menu);
        return true;
    }
}
