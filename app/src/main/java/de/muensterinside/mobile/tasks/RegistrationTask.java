package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.entities.Device;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class RegistrationTask extends AsyncTask<String, Integer, Device> {
    private Context context;
    private MuensterInsideAndroidApplication myApp;
    public static final String TAG = "RegistrationTask";

    public RegistrationTask(Context context, MuensterInsideAndroidApplication myApp){
        this.context = context;
        this.myApp = myApp;
    }

    @Override
    protected Device doInBackground(String... params){
        Log.d(TAG, "doInBackground() gestartet");
        String androidId = params[0];
        String username = params[1];
        try {
            Device device = this.myApp.getMuensterInsideMobile().register(androidId, username);
            Log.i(TAG, "doInBackground() erfolgreich");
            return device;
        }
        catch (Exception e){
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Device device){
        Log.d(TAG, "onPostExecute() gestartet");
        if(device != null){
            CharSequence text = "Registrierung erfolgreich! Registrierter Benutzername: "
                    + device.getUsername() + " Registrierte AndroidId: " + device.getAndroidUuid();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Log.i(TAG, "Registrierung erfolgreich");
        }
        else {
            CharSequence text = "Registrierung fehlgeschlagen!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Log.e(TAG, "Registrierung fehlgeschlagen");
        }
    }
}
