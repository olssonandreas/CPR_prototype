package se.mah.interaction.design;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by andreas on 4/7/14.
 */
public class TextViewDialog extends DialogFragment implements View.OnClickListener{
    TextViewDialog td = new TextViewDialog();
    Button b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.textviewdialog, container, false);
        b = (Button) getDialog().findViewById(R.id.btnOk);
        b.setOnClickListener(this);
        return v;



    }


    @Override
    public void onClick(View v) {

        if(v == b){

            Intent intent = new Intent(getActivity(), ListenActivity.class);
            startActivityForResult(intent, 0);

        }

    }
}


