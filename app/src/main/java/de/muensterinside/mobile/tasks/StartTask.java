package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import de.muensterinside.mobile.MainActivity;
import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.entities.Device;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class StartTask extends AsyncTask<String, Integer, Device> {
    private Context context;
    private MuensterInsideAndroidApplication myApp;
    private String android_id;

    public StartTask(Context context, MuensterInsideAndroidApplication myApp, String android_id){
        this.context = context;
        this.myApp = myApp;
        this.android_id = android_id;
    }

    @Override
    protected Device doInBackground(String... params){
        Device result = null;
        try {
             result = this.myApp.getMuensterInsideImpl().login(this.android_id);
            this.myApp.setDevice(result);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void onPostExecute(Device device){
        if(device != null){
            Intent myIntent = new Intent(this.context, MainActivity.class);
            this.context.startActivity(myIntent);
        }
    }
}
