package se.mah.interaction.design;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by andreas on 4/11/14.
 */
public class ListenHelp extends DialogFragment {


    int width = 400;
    int height = 500;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.listenhelp, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);




        return v;


    }
    @Override
    public void onResume()
    {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(width, height);
        window.setGravity(Gravity.CENTER);
        //TODO:
    }

}
