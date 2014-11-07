package se.mah.interaction.design;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class IntroActivity extends ActionBarActivity implements View.OnClickListener {


    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        TextView tv = (TextView)findViewById(R.id.textviewintro);
        tv.setText(Html.fromHtml(getString(R.string.intro_text)));
         b = (Button)findViewById(R.id.intoButton);
        b.setOnClickListener(this);

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
            IntroHelp fm = new IntroHelp();
            fm.show(ft, "textview");
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(v == b){

            Intent i = new Intent(this, ShakeNSpeak.class);
            startActivity(i);
        }

    }
}
