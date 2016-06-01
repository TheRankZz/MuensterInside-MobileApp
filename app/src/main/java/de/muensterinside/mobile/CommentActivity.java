package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import java.util.List;

import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Device;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class CommentActivity extends AppCompatActivity{

    public static final String TAG = "CommentActivity";
    //Anlegen des Textfeldes
    private EditText kommentar;
    private List<Comment> comments;
    private MuensterInsideAndroidApplication myApp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        myApp = (MuensterInsideAndroidApplication) getApplication();

        //Zuweisung der XML Objekte an unsere Variablen
        kommentar = (EditText)findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);

        Intent intent = getIntent();

        final int cat_id = intent.getIntExtra("cat_id", 0);
        final int loc_id = intent.getIntExtra("loc_id", 0);

        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);

        final String android_id = sharedPreferences.getString("android_id", "Default");
        final String username = sharedPreferences.getString("username", "Default");








        //ClickListener implementieren für den Button zum Wechsel der Activity
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Device device;
                Log.d(TAG, "button.onClick() gestartet");
                String s = kommentar.getText().toString();
                Intent intent = new Intent(CommentActivity.this,  MainActivity.class);
               try{ device = myApp.getMuensterInsideMobile().register(android_id,username);
                   myApp.getMuensterInsideMobile().saveComment(s, device.getId(), loc_id);}
               catch(Exception e) { e.printStackTrace();}
                intent.putExtra("name", s);
                startActivity(intent);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu() gestartet");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected() gestartet");
        //Wenn "Settings" gedrückt wurde, rufen wir die PrefsActivity auf
        if (item.getItemId() == R.id.action_settings) {
            Intent i = new Intent(this, PrefsActivity.class);
            startActivity(i);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }





}
