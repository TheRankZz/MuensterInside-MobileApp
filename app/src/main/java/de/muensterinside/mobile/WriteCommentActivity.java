package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Device;
import de.muensterinside.mobile.tasks.WriteCommentTask;

/**
 * Created by Julia Bracht and Nicolas Burchert
 *  @author Julia Bracht, Nicolas Burchert
 */
public class WriteCommentActivity extends AppCompatActivity{

    public static final String TAG = "WriteCommentActivity";
    private EditText kommentar;
    private int cat_id;
    private int loc_id;
    private List<Comment> comments;
    private Context context;
    private MuensterInsideAndroidApplication myApp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        // Hier wird der Activity das Aussehen zugeordnet
        setContentView(R.layout.activity_new_comment);
        myApp = (MuensterInsideAndroidApplication) getApplication();

        //Zuweisung der XML Objekte an unsere Variablen
        kommentar = (EditText)findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);

        //Die von der MainActivity übergebenden Parameter werden hier zugewiesen
        Intent intent = getIntent();
        cat_id = intent.getIntExtra("selected", 0);
        loc_id = intent.getIntExtra("locId", 0);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final int device_id = sharedPreferences.getInt("deviceId", 0);

         /* In der SharedPreference wird die vorher ausgewählte
         * ID einer Kategorie gespeichert.
         */
        SharedPreferences sharedPreferences1;
        sharedPreferences1 = getSharedPreferences("MyCatIdPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        editor.putInt("catId", cat_id);
        editor.commit();

        context = this;


        //ClickListener implementieren für den Button zum Wechsel der Activity
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.d(TAG, "button.onClick() gestartet");
                String s = kommentar.getText().toString();
                Intent intent = new Intent(WriteCommentActivity.this,  LocationActivity.class);
                SharedPreferences newCommentLocationId = getSharedPreferences("MyCommentBoolPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = newCommentLocationId.edit();
                editor.putInt("loc_id", loc_id);
                editor.putInt("cat_id", cat_id);
                editor.putBoolean("newCommentBool", true);
                editor.commit();

                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    WriteCommentTask writeCommentTask = new WriteCommentTask(context, myApp, s, loc_id, device_id);
                    writeCommentTask.execute();
                    int code = 1;
                    try {
                        code = writeCommentTask.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (code == 0) {
                        intent.putExtra("name", s);

                        CharSequence text = "Kommentar " + s + " wurde erstellt.";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Log.i(TAG, "Kommentar wurde erfolgreich erstellt");
                    } else {
                        CharSequence text = "Kommentar " + s + " wurde nicht erstellt.";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Log.i(TAG, "Kommentar wurde nicht erfolgreich erstellt");
                    }
                    startActivity(intent);
                }
                else{

                    Log.d(TAG, "Keine Internetverbindung");
                    Toast.makeText(WriteCommentActivity.this, "Verbindung fehlgeschlagen", Toast.LENGTH_LONG).show();
                }
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu() gestartet");
        //Hier füllen (inflate) wir das Options Menu mit dem Menüeintrag,
        // den wir in der XML-Datei menu_menu_main.xml definiert haben.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected() gestartet");
        //Hier prüfen wir, ob unser Menüeintrag angeklickt wurde und führen die gewünschte Aktion aus.
        if (item.getItemId() == R.id.action_settings) {
            //Beim Klicken auf dem Button "Einstellung" öffnet es die passende Activity
            Intent i = new Intent(this, PrefsActivity.class);
            startActivity(i);
            return true;
        }
        // Home Button
        else if(item.getItemId() == R.id.action_home){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }





}
