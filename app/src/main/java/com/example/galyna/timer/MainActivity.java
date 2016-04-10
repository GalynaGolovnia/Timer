package com.example.galyna.timer;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private TextView mTextTime;
    Intent intent;
    static final private int TIME = 0;
    String getHours,getMinutes,getSeconds;
    SpeechlyTimer timer;
    Handler handler;
    ImageButton clock;
    long milliseconds=0;
    private ToggleButton toggleButton;
    int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        mTextTime = (TextView) findViewById(R.id.textView);
        handler=new Handler();
        clock= (ImageButton) findViewById(R.id.pause);

        Typeface clockFonts = Typeface.createFromAsset(getAssets(), "fonts/source_sans_pro_light.ttf");
        mTextTime.setTypeface(clockFonts);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(this);
        timer= new SpeechlyTimer(handler) {
            @Override
            public void onTimerFinish() {
                toggleButton.setChecked(false);

            }

            @Override
            public void onTimerStopped() {
                if(index==0)
                 mTextTime.setText("00:00:00");

            }

            @Override
            public void onPlayNotification() {
                playSound();
            }

            @Override
            public void updateUI(long timeRemaining) {
                mTextTime.setText(SpeechlyTimer.ConvertToString(timeRemaining));


            }
        };




    }

    private void playSound() {
        try {
          AssetFileDescriptor assetFileDescriptor= getAssets().openFd("sounds/ring.mp3");
            MediaPlayer mediaPlayer=new MediaPlayer();
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v){
    switch (v.getId()){
        case R.id.pause:
            if(!mTextTime.getText().toString().equals("00:00:00")){

                timer.pause();
                 index=1;
                toggleButton.setChecked(false);
            }
            break;
    }
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
protected void onSaveInctanceState(Bundle outState){
super.onSaveInstanceState(outState);
    outState.putInt("TimeHours", Integer.parseInt(getHours));
    outState.putInt("TimeMinutes", Integer.parseInt(getMinutes));
    outState.putInt("TimeSeconds", Integer.parseInt(getSeconds));
}
    protected void onRestoreInctanceState(Bundle SaveInstance){
        super.onRestoreInstanceState(SaveInstance);
        getHours=SaveInstance.getString("TimeHour");
        getMinutes=SaveInstance.getString("TimeMinutes");
        getSeconds=SaveInstance.getString("TimeSeconds");

    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            index=0;
            timer.setIsOff(false);
            if(mTextTime.getText().toString().equals("00:00:00")){
            intent = new Intent(this, SelectTime.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityForResult(intent, TIME);
            }
           else{
               timer.setIsOff(false);
              long timeAfterPause=timer.getTimeRemaining();
                timer.setTime(timeAfterPause);
                timer.start();
            }

        }
        else{


            timer.stop();


         }}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == TIME ) {
            if (resultCode == RESULT_OK) {
            getHours=   data.getStringExtra(SelectTime.getHours);
            getMinutes=   data.getStringExtra(SelectTime.getMinutes);
            getSeconds=   data.getStringExtra(SelectTime.getSeconds);

            if(getSeconds.equals("0")&& getMinutes.equals("0")&& getHours.equals("0")){
                Toast.makeText(this,"Please, choose a time",Toast.LENGTH_SHORT).show();
                toggleButton.setChecked(false);}
            else {
                long milliseconds = SpeechlyTimer.ConvertToMilliseconds(getHours, getMinutes, getSeconds);

                timer.setTime(milliseconds);
                timer.start();
            }
            }



        }
    }  }