package com.fifteen.puzzle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Pomoc extends Activity implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomoc);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/backlash.ttf");
        Button bNazaj = (Button) findViewById(R.id.bNazaj3);
        TextView tv1 = (TextView) findViewById(R.id.pomocHead);
        TextView tv2 = (TextView) findViewById(R.id.tvPomoc);
        bNazaj.setOnClickListener(this);
        bNazaj.setTypeface(tf);
        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pomoc, menu);
        return true;
        
        
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.bNazaj3){
			Intent i = new Intent(this, MainMenu.class);
			startActivity(i);
		}
			
		
	}
}
