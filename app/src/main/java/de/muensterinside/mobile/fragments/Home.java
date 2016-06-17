package de.muensterinside.mobile.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.R;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
public class Home extends Fragment{

    public static final String TAG = "Home";
    private TextView username;

    public Home(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.welcome, container, false);
        username = (TextView) rootView.findViewById(R.id.textView);

        MuensterInsideAndroidApplication myApp = (MuensterInsideAndroidApplication) getActivity().getApplication();
        String name;
        try {
            name = myApp.getUsername();
        }
        catch (Exception e){
            name = "Default";
            e.printStackTrace();
        }
        username.setText(name + ",");

        return rootView;
    }
}
