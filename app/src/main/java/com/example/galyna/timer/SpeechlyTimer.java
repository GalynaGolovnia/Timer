package com.example.galyna.timer;
import android.os.Handler;


/**
 * Created by Nadia on 12.03.2016.
 */
public abstract class SpeechlyTimer  implements Runnable{
    private long timeRemaning;
    private Handler handler;
    protected boolean isOff=false;

    public void setIsOff(boolean isOff){
        this.isOff=isOff;

    }

    public SpeechlyTimer(Handler handler,long timeRemaning){
        this.handler=handler;
        this.timeRemaning=timeRemaning;

    }

    public SpeechlyTimer(Handler handler) {

        this.handler=handler;
        this.timeRemaning=0;
    }

public void setTime(long timeRemaning){
    this.timeRemaning=timeRemaning;

}
    public void start(){
   handler.postDelayed(this, 1000);

}
    public void pause(){
        isOff=true;
       // onTimerStopped();
    }
public void stop(){
        pause();
    onTimerStopped();

    }
    public abstract void onTimerStopped();

    @Override
    public void run() {
        if(!isOff){
            updateUI(timeRemaning);

            timeRemaning = timeRemaning - 1000;
            if(timeRemaning==0){
                onPlayNotification();
            }
            if (timeRemaning >= 0) {
                handler.postDelayed(this, 1000);        }
            else{
                onTimerFinish();
            }
        }

    }

    public abstract void onPlayNotification();

    public long getTimeRemaining(){
    return timeRemaning;
}


    public static long ConvertToMilliseconds(String getHours,String getMinutes,String getSeconds){
    int hours=Integer.parseInt(getHours);
    int minutes=Integer.parseInt(getMinutes);
    int seconds=Integer.parseInt(getSeconds);
    long milliseconds=(hours*60*60+minutes*60+seconds)*1000;
    return milliseconds;
}
    public static  String ConvertToString(long timeInput){
long totalSeconds= timeInput/1000;
        int hours=(int)totalSeconds/(60*60);

        int min=(int)(totalSeconds-hours*(60*60))/60;
        int sec=(int)totalSeconds%60;
        String hoursString=(hours<10)?"0"+hours:hours+"";
        String minString=(min<10)?"0"+min:min+"";
        String secString=(sec<10)?"0"+sec:sec+"";
        return hoursString+":"+minString+":"+secString;
    }

public abstract void updateUI(long timeRemaning);
   public abstract void onTimerFinish();

}
