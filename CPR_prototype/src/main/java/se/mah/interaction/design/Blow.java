package se.mah.interaction.design;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaRecorder;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;


public class Blow extends Activity {

    Vibrator vb;

    private final float NOISE = (float) 2.0;
    private int lol = 12;
    static final private double EMA_FILTER = 0.6;
    private MediaRecorder mRecorder = null;
    private double mEMA = 0.0;
    private String s;
    public boolean isListening = true;
    private int inc = 0;
    double soundLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blow);
        vb = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        startTimerThread();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void start() {

        Log.i("hej", "hej");

        if (mRecorder == null) {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile("/dev/null");
            try {
                mRecorder.prepare();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            mRecorder.start();
            mEMA = 0.0;



        }
        soundLevel = getAmplitude();
        int inc = 0;
        s = Double.toString(soundLevel);
        Log.i(s, s);


    }

    public void stop() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

    public double getAmplitude() {
        if (mRecorder != null)
            return  (mRecorder.getMaxAmplitude()/2700.0);
        else
            return 0;

    }

    public double getAmplitudeEMA() {
        double amp = getAmplitude();
        mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
        return mEMA;
    }

    private void startTimerThread() {
        Thread th = new Thread(new Runnable() {
            private long startTime = System.currentTimeMillis();
            public void run() {
                while (isListening == true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            start();

                            if(soundLevel > 12){


                                inc++;
                                vb.vibrate(200);
                                String s1 = Integer.toString(inc);
                                Log.i(s1, "inc");
                                stop();
                            }
                        }
                    });
                    try {
                        Thread.sleep(4000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        th.start();
    }

}