package se.mah.interaction.design;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
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

public class ShakeNSpeak extends ActionBarActivity implements SensorEventListener {

    // sensor variables
    private float mLastX, mLastY, mLastZ;
    private boolean mInitialized;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    // audio variables
    private final float NOISE = (float) 2.0;
    static final private double EMA_FILTER = 0.6;
    private MediaRecorder mRecorder = null;
    private double mEMA = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // call method for starting mediarecorder
        start();

        // init sensorstuff
        mInitialized = false;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);


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
        if (id == R.id.action_help) {

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            MainActivityHelp fm = new MainActivityHelp();
            fm.show(ft, "textview");

            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        //
        double soundLevel = getAmplitude();
        String sLevel = Double.toString(soundLevel);
        Log.i(sLevel, sLevel);


        // xy for accelerometer
        float x = event.values[0];
        float y = event.values[1];
        if (!mInitialized) {
            mLastX = x;
            mLastY = y;

            mInitialized = true;
        } else {
            float deltaX = Math.abs(mLastX - x);
            float deltaY = Math.abs(mLastY - y);
            if (deltaX < NOISE)
                deltaX = (float) 0.0;
            if (deltaY < NOISE)
                deltaY = (float) 0.0;

            mLastX = x;
            mLastY = y;


            // deltaX deltaY use for debug

            boolean speak = false;

            if(soundLevel > 8){
                speak = true;



            if(speak =true){

                // kind of shaking
                if (deltaX > 6 && deltaY > 6  ) {

                    Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 250 milliseconds
                    v.vibrate(250);

                    // start the next intent and stop mediarecorder
                    stop();
                    Intent i = new Intent(this, ListenActivity.class);
                    speak = false;
                    startActivity(i);


                }

                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void start() {

        // create mediarecorder
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
            Log.i("nej", "mig");
            mRecorder.start();
            mEMA = 0.0;

        }

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
}