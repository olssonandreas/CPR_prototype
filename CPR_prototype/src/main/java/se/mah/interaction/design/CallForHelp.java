package se.mah.interaction.design;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CallForHelp extends Activity implements View.OnClickListener {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnCl, btnCall;
    TextView numbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_for_help);

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(this);

        btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(this);

        btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(this);

        btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(this);

        btn8 = (Button) findViewById(R.id.btn8);
        btn8.setOnClickListener(this);

        btn9 = (Button) findViewById(R.id.btn9);
        btn9.setOnClickListener(this);

        btn0 = (Button) findViewById(R.id.btn0);
        btn0.setOnClickListener(this);

        btnCl = (Button) findViewById(R.id.btnCl);
        btnCl.setOnClickListener(this);

        btnCall = (Button) findViewById(R.id.btnCall);
        btnCall.setOnClickListener(this);

        numbers = (TextView) findViewById(R.id.textView);
        numbers.setFocusable(false);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.listen, menu);
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

        switch(v.getId())
        {
            case R.id.btn0: numbers.append("0");
                break;
            case R.id.btn1: numbers.append("1");
                break;
            case R.id.btn2: numbers.append("2");
                break;
            case R.id.btn3: numbers.append("3");
                break;
            case R.id.btn4: numbers.append("4");
                break;
            case R.id.btn5: numbers.append("5");
                break;
            case R.id.btn6: numbers.append("6");
                break;
            case R.id.btn7: numbers.append("7");
                break;
            case R.id.btn8: numbers.append("8");
                break;
            case R.id.btn9: numbers.append("9");
                break;
            case R.id.btnCl:

                Editable currentText = (Editable) numbers.getText();

                if (currentText.length() > 0) {
                    currentText.delete(currentText.length() - 1,
                            currentText.length());
                    numbers.setText(currentText);
                }
                break;

            case R.id.btnCall:

               String s = numbers.getText().toString();
                if(s.equals("112")){

                Vibrator vb = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                 vb.vibrate(250);
                    Intent i = new Intent(this, StartCPR.class);
                 startActivity(i);
                }
                else
                {
                    int dur = Toast.LENGTH_SHORT;
                  Toast toast =  Toast.makeText(this, "Wrong number!", dur);
                  toast.show();

                    numbers.setText("");
                }

                break;
        }
    }
}
