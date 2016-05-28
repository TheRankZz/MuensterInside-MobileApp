package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import de.muensterinside.mobile.MainActivity;
import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.entities.Device;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class LoginTask extends AsyncTask<String, Integer, Device> {
    private Context context;
    private MuensterInsideAndroidApplication myApp;

    public LoginTask(Context context, MuensterInsideAndroidApplication myApp){
        this.context = context;
        this.myApp = myApp;
    }

    @Override
    protected Device doInBackground(String... params){
        String androidId = params[0];
        String username = params[1];
        try {
            Device device = myApp.getMuensterInsideMobile().login(androidId);
            return device;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    protected void onPostExecute(Device device){
        if(device != null){
            Intent myIntent = new Intent(this.context, MainActivity.class);
            this.context.startActivity(myIntent);

            CharSequence text = "Login erfolgreich! Benutzername: " + device.getUsername() + "AndroidId: " + device.getAndroidUuid();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            CharSequence text = "Login fehlgeschlagen!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
