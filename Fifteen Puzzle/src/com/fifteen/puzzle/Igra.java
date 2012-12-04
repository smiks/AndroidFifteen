package com.fifteen.puzzle;

import java.util.HashMap;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("UseSparseArrays")
public class Igra extends Activity implements OnClickListener {

	private HashMap<Integer, Integer> mButtonIds;
	private Polje mPolje;
	private int shufflelimit = 1000;
	private static final int DEBUG=0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_igra);

		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/backlash.ttf");
		TextView tvNaslov = (TextView) findViewById(R.id.igraHead);
		tvNaslov.setTypeface(tf);

	
		init();
	}

	private void animacija(boolean start){
		if(mPolje.getMoves() == 0){
		ImageView clock = (ImageView) findViewById(R.id.clock);
		Animation vrtenje = AnimationUtils.loadAnimation(this, R.anim.animacija);
		if(start)
			clock.startAnimation(vrtenje);
		else 
			clock.clearAnimation();
		}
	}
	private void init() {

			mPolje = new Polje(3);
		mButtonIds = new HashMap<Integer, Integer>();
		mButtonIds.put(R.id.b1, 1);
		mButtonIds.put(R.id.b2, 2);
		mButtonIds.put(R.id.b3, 3);
		mButtonIds.put(R.id.b4, 4);
		mButtonIds.put(R.id.b5, 5);
		mButtonIds.put(R.id.b6, 6);
		mButtonIds.put(R.id.b7, 7);
		mButtonIds.put(R.id.b8, 8);
		mButtonIds.put(R.id.b9, 9);

		if(DEBUG == 0)
			mPolje.shuffle(shufflelimit);
		ponovnoNarisi(false);

		for (int buttonId : mButtonIds.keySet()) {
			((Button) findViewById(buttonId))
					.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							int buton = mButtonIds.get(v.getId());
							int i = (buton - 1) / Polje.SIZE + 1;
							int j = buton % Polje.SIZE
									+ (buton % Polje.SIZE == 0 ? 3 : 0);
							boolean finished = mPolje.move(i, j);
								animacija(!finished);
							mPolje.incMoves();
							if (finished) {
								finishGame(mPolje);
							}
							ponovnoNarisi(finished);
						}
					});
		}

	}

	public void finishGame(Polje p) {
		long time;
		time = (System.currentTimeMillis() - mPolje.getTime()) / 1000; //time : cas igranja
		Toast.makeText(
				getApplicationContext(),
				" KONEC IGRE! \n Stevilo potez: " + p.getMoves()
						+ " \n Cas igranja: " + time + "s ", Toast.LENGTH_SHORT)
				.show();

		String PREFS_NAME = "MyPrefsFile";
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		boolean zvok = settings.getBoolean("zvok", false);
		if (zvok)
			startService(new Intent(getBaseContext(), Glasba.class));

		String FILEHS = "Highscore";
		SharedPreferences hs = getSharedPreferences(FILEHS, 0);
		float prvi = hs.getFloat("1", 0.0f);
		float drugi = hs.getFloat("2", 0.0f);
		float tretji = hs.getFloat("3", 0.0f);
		long prviCas = hs.getLong("c1", 0);
		long drugiCas = hs.getLong("c2", 0);
		long tretjiCas = hs.getLong("c3", 0);
		if (prvi == 0)
			prvi = 999;
		if (drugi == 0)
			drugi = 999;
		if (tretji == 0)
			tretji = 999;

		if (mPolje.getMoves() < prvi) {
			tretji = drugi;
			drugi = prvi;
			prvi = mPolje.getMoves();
			
			tretjiCas = drugiCas;
			drugiCas = prviCas;
			prviCas = time;
		} else if (mPolje.getMoves() < drugi) {
			tretji = drugi;
			drugi = mPolje.getMoves();
			
			tretjiCas = drugiCas;
			drugiCas = time;
		} else if (mPolje.getMoves() < tretji) {
			tretji = mPolje.getMoves();
			tretjiCas = time;
		}
		SharedPreferences.Editor editor = hs.edit();
		editor.putFloat("1", prvi);
		editor.putFloat("2", drugi);
		editor.putFloat("3", tretji);
		editor.putLong("c1", prviCas);
		editor.putLong("c2", drugiCas);
		editor.putLong("c3", tretjiCas);
		editor.commit();

		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setTitle("O aplikaciji").setCancelable(false)
				.setPositiveButton("Da", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						reset();
					}
				})
				.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						izhod();
					}
				}).setMessage(R.string.konecText);
		adb.show();
	}

	public void izhod() {
		Intent i = new Intent(this, MainMenu.class);
		startActivity(i);
	}

	public void reset() {
		mPolje.shuffle(shufflelimit);
		ponovnoNarisi(false);
		mPolje.setStPotez(0);
		mPolje.setTime(0);
		animacija(false);
	}

	public void ponovnoNarisi(boolean finished) {

		for (int id : mButtonIds.keySet()) {
			int buton = mButtonIds.get(id);
			int i = (buton - 1) / Polje.SIZE + 1;
			int j = buton % Polje.SIZE + (buton % Polje.SIZE == 0 ? 3 : 0);
			int stevilokmorebitnot = mPolje.getValueOn(i, j);
			if (stevilokmorebitnot == Polje.EMPTY_FIELD) {
				((Button) findViewById(id)).setVisibility(View.INVISIBLE);
			} else {
				((Button) findViewById(id)).setText("" + stevilokmorebitnot);
				((Button) findViewById(id)).setVisibility(View.VISIBLE);
			}
		}
	}

	// private int getIdFromInt(int i){
	// for (int id: mButtonIds.keySet()){
	// if (mButtonIds.get(id) == i){
	// return id;
	// }
	// }
	// return -1;
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_igra, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_novaIgra:
			reset();
		}
		return true;
	}

	public void onClick(View v) {

	}
}
