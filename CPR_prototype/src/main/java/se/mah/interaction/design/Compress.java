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

public class Compress extends ActionBarActivity implements View.OnClickListener {
    int inc = 0;
    Button compress;
    Vibrator vb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compress);

    compress = (Button)findViewById(R.id.btnCompress);
    compress.setOnClickListener(this);
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
            CompressHelp fm = new CompressHelp();
            fm.show(ft, "textview");

            return true;


        }
        return super.onOptionsItemSelected(item);
    }

   @Override
    public void onClick(View v) {

        if(v == compress){

        inc++;
        vb.vibrate(120);
        }

        if(inc >= 30){


            vb.vibrate(250);
            Intent i = new Intent(this, Blow.class);
            startActivity(i);
        }


    }
}
