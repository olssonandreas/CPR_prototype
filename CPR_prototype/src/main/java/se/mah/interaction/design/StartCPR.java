package se.mah.interaction.design;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;


public class StartCPR extends ActionBarActivity implements View.OnClickListener {
    Button a, b;
    boolean chest = false;
    boolean mouth = false;
    boolean finished;
    Vibrator vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_cpr);

        a = (Button) findViewById(R.id.chestpress);
        a.setOnClickListener(this);
        b = (Button) findViewById(R.id.mouthpress);
        b.setOnClickListener(this);
        vb = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);


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
            StartCPRHelp fm = new StartCPRHelp();
            fm.show(ft, "textview");
            return true;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        if (v == b) {
            vb.vibrate(250);
            mouth = true;

            if (chest == true && mouth == true) {

                finished = true;

            }
        }
            if (v == a) {
                chest = true;
                vb.vibrate(250);
                if (mouth == true && chest == true) {


                    finished = true;

                }
            }
            if (finished == true) {
                View background = findViewById(R.id.backgroundimage);
                background.setBackgroundResource(R.drawable.openshirtmouthfinal);
                try {

                    Thread.sleep(2000);
                    Intent i = new Intent(this, Compress.class);
                    startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


    }
}


