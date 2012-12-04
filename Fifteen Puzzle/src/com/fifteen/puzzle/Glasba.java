package com.fifteen.puzzle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.media.MediaPlayer;

public class Glasba extends Service {
	
	MediaPlayer predvajalnik;
	
	@Override
	public IBinder onBind(Intent intent) {	
		return null;
	}
	
	@Override
	public void onCreate() {
		predvajalnik = MediaPlayer.create(this, R.raw.song);
		predvajalnik.setLooping(false);
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		predvajalnik.start();
	}
	@Override
	public void onDestroy() {
		predvajalnik.stop();
	}
}
