package se.mah.interaction.design;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class StartActivity extends ActionBarActivity implements View.OnClickListener{
    Button p, i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        p = (Button)findViewById(R.id.practice);
        p.setOnClickListener(this);

        i = (Button)findViewById(R.id.instructions);
        i.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
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

    @Override
    public void onClick(View v) {

        if(v == p){

            Intent i = new Intent(this, ShakeNSpeak.class);
            startActivity(i);

        }
        if(v == i){

            Intent i = new Intent(this, IntroActivity.class);
            startActivity(i);
        }
    }
}
