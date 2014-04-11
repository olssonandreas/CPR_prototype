package se.mah.interaction.design;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class ListenActivity extends ActionBarActivity implements SensorEventListener {



    SensorManager mSensorManager;
    Sensor mSensor;
    MediaPlayer mp;
    AudioManager am;
    Vibrator v;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
        // create a sensor manager
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // create a manager for proximity sensor
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        // audiomanager
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        // set phone to incall for use of earpiece speaker
        am.setMode(AudioManager.MODE_IN_CALL);
        am.setSpeakerphoneOn(false);
        am.setBluetoothScoOn(true);
        // point to sound-file and create mediaplayer.
        Resources res = this.getResources();
        String breath = "breathing";
        int fileId = getResources().getIdentifier(breath, "raw",
                "se.mah.interaction.design");

        mp = MediaPlayer.create(this, fileId);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shake_nspeak, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_help) {


            
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        // float who saves proximity values
        float value = event.values[0];
        // just for debugging the proximity
        String sValue = String.valueOf(value);
        Log.i(sValue, "VALUE TOWN");

        if(value == 0){
            // create a vibration which activates when you finish this activity
            v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            // start playing sound through earpiece
            mp.start();
            // declare new intent
            i = new Intent(this, CallForHelp.class);

            // completionlistener who checks if audio sample is done playing
            // then vibrates and switches activity.
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // Vibrate for 250 milliseconds
                    v.vibrate(250);
                    startActivity(i);
                }

            });



        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


}




