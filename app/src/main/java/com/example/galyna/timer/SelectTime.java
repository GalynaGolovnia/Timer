package com.example.galyna.timer;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SelectTime extends ActionBarActivity {
    TextView TextHour,TextMinutes,TextSeconds,editTextMinutes,ViewSeconds,ViewMinutes,ViewHours;
    int textInDisplayH=0;
    public final static String getHours = "com.example.galyna.timer.getHours";
    public final static String getMinutes = "com.example.galyna.timer.getMinutes";
    public final static String getSeconds = "com.example.galyna.timer.getSeconds";
   // boolean isCheked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_time);
       TextHour= (TextView) findViewById(R.id.editTextHour);
       TextMinutes= (TextView) findViewById(R.id.editTextMinutes);
       TextSeconds= (TextView) findViewById(R.id.editTextSeconds);
       ViewSeconds= (TextView) findViewById(R.id.textSeconds);
        ViewMinutes= (TextView) findViewById(R.id.textMinutes);
       ViewHours= (TextView) findViewById(R.id.textHour);
        editTextMinutes= (TextView) findViewById(R.id.editTextMinutes);


        Typeface clockFonts= Typeface.createFromAsset(getAssets(), "fonts/source_sans_pro_light.ttf");
        TextHour.setTypeface(clockFonts);
        TextMinutes.setTypeface(clockFonts);
        TextSeconds.setTypeface(clockFonts);
        ViewSeconds.setTypeface(clockFonts);
        ViewMinutes.setTypeface(clockFonts);
        ViewHours.setTypeface(clockFonts);

    }
   public void onClick(View v){
        switch (v.getId()){
            case R.id.bHourPlus:
                            String  textH=new String(TextHour.getText().toString());
                            textInDisplayH= Integer.parseInt(textH);
                            if(textInDisplayH<99)
                                textInDisplayH++;


                            TextHour.setText(textInDisplayH+"");
                            break;
            case R.id.bMinutesPlus:
                            String textM=new String(TextMinutes.getText().toString());
                            int textInDisplayM= Integer.parseInt(textM);
                            if(textInDisplayM<59)
                                textInDisplayM++;
                            TextMinutes.setText(textInDisplayM+"");
                            break;
            case R.id.bSecondsPlus:
                            String textS=new String(TextSeconds.getText().toString());
                            int textInDisplayS= Integer.parseInt(textS);
                            if(textInDisplayS<59)
                                textInDisplayS++;
                            TextSeconds.setText(textInDisplayS+"");
                            break;
            case R.id.bHourMinus:
                            String  textHM=new String(TextHour.getText().toString());
                            int textInDisplayHM= Integer.parseInt(textHM);
                            if(textInDisplayHM>0)
                                textInDisplayHM--;
                            TextHour.setText(textInDisplayHM+"");
                            break;
            case R.id.bMinutesMinus:
                            String textMM=new String(TextMinutes.getText().toString());
                            int textInDisplayMM= Integer.parseInt(textMM);
                            if(textInDisplayMM>0)
                                textInDisplayMM--;
                            TextMinutes.setText(textInDisplayMM+"");
                            break;
            case R.id.bSecondsMinus:
                            String textSM=new String(TextSeconds.getText().toString());
                            int textInDisplaySM= Integer.parseInt(textSM);
                            if(textInDisplaySM>0)
                                textInDisplaySM--;
                            TextSeconds.setText(textInDisplaySM+"");
                            break;
            case R.id.Ok:

                            Intent intent=new Intent();
                            intent.putExtra(getHours,TextHour.getText().toString() );
                            intent.putExtra(getMinutes,TextMinutes.getText().toString() );
                            intent.putExtra(getSeconds,TextSeconds.getText().toString() );
                            setResult(RESULT_OK, intent);
                            finish();



        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_time, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
