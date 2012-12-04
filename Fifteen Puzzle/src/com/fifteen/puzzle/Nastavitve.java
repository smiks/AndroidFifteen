package com.fifteen.puzzle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Nastavitve extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nastavitve);

		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/backlash.ttf");
		TextView tvNaslov = (TextView) findViewById(R.id.tvNastavitveHead);
		tvNaslov.setTypeface(tf);

		Button bNazaj = (Button) findViewById(R.id.bNazaj2);
		Button bShrani = (Button) findViewById(R.id.nast_shrani);

		bShrani.setTypeface(tf);
		bNazaj.setTypeface(tf);
		bNazaj.setOnClickListener(this);
		bShrani.setOnClickListener(this);
		CheckBox c = (CheckBox) findViewById(R.id.checkBox1);

		String PREFS_NAME = "MyPrefsFile";
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		boolean zvok = settings.getBoolean("zvok", false);
		if (zvok)
			c.setChecked(true);
		else
			c.setChecked(false);

	}

	public void onClick(View v) {
		int zvok;

		CheckBox c = (CheckBox) findViewById(R.id.checkBox1);
		if (c.isChecked())
			zvok = 1;
		else
			zvok = 0;

		if (v.getId() == R.id.bNazaj2) {
			Intent i = new Intent(this, MainMenu.class);
			startActivity(i);
		} else if (v.getId() == R.id.nast_shrani) {

			String PREFS_NAME = "MyPrefsFile";
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			SharedPreferences.Editor editor = settings.edit();
			editor.putBoolean("zvok", zvok == 1 ? true : false);
			editor.commit();
			Toast.makeText(getApplicationContext(), " Nastavitve shranjene! ",
					Toast.LENGTH_SHORT).show();

		}
	}
}
