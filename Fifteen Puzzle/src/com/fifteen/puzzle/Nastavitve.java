package com.fifteen.puzzle;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Nastavitve extends Activity implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nastavitve);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/backlash.ttf");
        TextView tvNaslov = (TextView) findViewById(R.id.tvNastavitveHead);
        tvNaslov.setTypeface(tf);
        
        Button bNazaj = (Button) findViewById(R.id.bNazaj2);
        TextView tv1 = (TextView) findViewById(R.id.tvNastavitve1);
        TextView tv2 = (TextView) findViewById(R.id.tvNastavitev2);
        Button bShrani = (Button) findViewById(R.id.nast_shrani);
        tv2.setTypeface(tf);
        tv1.setTypeface(tf);
        bShrani.setTypeface(tf);
        bNazaj.setTypeface(tf);
        bNazaj.setOnClickListener(this);
        bShrani.setOnClickListener(this);
       
        RadioButton rb1 = (RadioButton) findViewById(R.id.rb4x4);
        RadioButton rb2 = (RadioButton) findViewById(R.id.rb3x3);
        
        RadioButton rb3 = (RadioButton) findViewById(R.id.rbStevilke);
        RadioButton rb4 = (RadioButton) findViewById(R.id.rbSlika);
        
        CheckBox c = (CheckBox) findViewById(R.id.checkBox1);
        
        String imeDatoteke = "seznam";
        
		String nastavitve = null;
		try {
			 
			InputStream in = openFileInput(imeDatoteke);
			 
			if (in != null) {
			        
		         InputStreamReader input = new InputStreamReader(in);
		         BufferedReader buffreader = new BufferedReader(input);
			 
		         nastavitve = "";
		         nastavitve=buffreader.readLine();
			     in.close();
			          
			     Toast.makeText(getApplicationContext(),"Vsebina datoteke: " + nastavitve,Toast.LENGTH_SHORT).show();
			     if(nastavitve.charAt(0)==0)
		            	rb1.setChecked(true);
		            else
		            	rb2.setChecked(true);
		            
		            if(nastavitve.charAt(1)==0)
		            	rb4.setChecked(true);
		            else
		            	rb3.setChecked(true);
		            
		            if(nastavitve.charAt(2)==0)
		            	c.setChecked(false);
		            else
		            	c.setChecked(true);
			         
			}
		}
			 catch (IndexOutOfBoundsException e){
				Toast.makeText(getApplicationContext(),"Seznam je prazen.",Toast.LENGTH_SHORT).show();
				c.setChecked(false);
				rb4.setChecked(true);
				rb1.setChecked(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        
        
        
//        
//        FileInputStream fis;
//      try {
//        	fis = openFileInput(imeDatoteke);
//        	BufferedInputStream bif = new BufferedInputStream(fis);
//    		InputStreamReader isr = new InputStreamReader(bif);
//            BufferedReader reader = new BufferedReader(isr);
//            String nastavitve = reader.read()+"";
//            if(nastavitve.charAt(0)==0)
//            	rb1.setChecked(true);
//            else
//            	rb2.setChecked(true);
//            
//            if(nastavitve.charAt(1)==0)
//            	rb4.setChecked(true);
//            else
//            	rb3.setChecked(true);
//            
//            if(nastavitve.charAt(2)==0)
//            	c.setChecked(false);
//            else
//            	c.setChecked(true);
////            
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IndexOutOfBoundsException e){
//			c.setChecked(false);
//			rb4.setChecked(true);
//			rb1.setChecked(true);
//		}
        
        
        
        
		
        
        
    } 

	public void onClick(View v) {
		// TODO Auto-generated method stub
		int velikost=9, stevilke=8, zvok=7;
		
		String imeDatoteke = "seznam";
        File file = new File(imeDatoteke);
        boolean deleted = file.delete();
		String vrstica;
		FileOutputStream fos;
		
		RadioGroup g1 = (RadioGroup) findViewById(R.id.radio_group1);
		int selected = g1.getCheckedRadioButtonId();
		if(findViewById(selected) == findViewById(R.id.rb3x3))
			velikost=0;
		else
			velikost = 1;		
		
		RadioGroup g2 = (RadioGroup) findViewById(R.id.radio_group2);		
		int	selected2 = g2.getCheckedRadioButtonId();
		if(findViewById(selected2) == findViewById(R.id.rbStevilke))
			stevilke=1;
		else
			stevilke=0;
		
		CheckBox c = (CheckBox) findViewById(R.id.checkBox1);
		if(c.isChecked())
			zvok = 1;
		else
			zvok = 0;
		
		if(v.getId() == R.id.bNazaj2){
			Intent i = new Intent(this, MainMenu.class);
			startActivity(i);
		}	
		else if(v.getId() == R.id.nast_shrani){
			try {
				fos = openFileOutput(imeDatoteke, MODE_APPEND);
				BufferedOutputStream buf = new BufferedOutputStream(fos);
	            OutputStreamWriter osw = new OutputStreamWriter(buf);
	            BufferedWriter writer = new BufferedWriter(osw);

	            //writer.newLine();	  

	            
	            String tekst =""+velikost+stevilke+zvok+" ";
	            Toast.makeText(getApplicationContext(),tekst,Toast.LENGTH_SHORT).show();
	            writer.write(tekst);
	            
	            writer.flush();
	            writer.close();

			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
			
			
			
			
			//debug
			//Toast.makeText(getApplicationContext(),"Something is done",Toast.LENGTH_SHORT).show();
		}
	}
}
