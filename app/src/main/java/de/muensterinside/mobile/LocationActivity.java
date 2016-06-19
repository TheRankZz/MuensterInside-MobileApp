package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.muensterinside.mobile.adapter.CommentListViewAdapters;
import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Device;
import de.muensterinside.mobile.entities.Location;
import de.muensterinside.mobile.tasks.DownVoteTask;
import de.muensterinside.mobile.tasks.LocationTask;
import de.muensterinside.mobile.tasks.ShowCommentTask;
import de.muensterinside.mobile.tasks.UpVoteTask;

import static de.muensterinside.mobile.Constants.FIRST_COLUMN;
import static de.muensterinside.mobile.Constants.SECOND_COLUMN;

/**
 * Created by Julia Bracht and Nicolas Burchert
 *  @author Julia Bracht, Nicolas Burchert
 */
public class LocationActivity extends AppCompatActivity {
    private int loc_id;
    private int cat_id;
    private Context context;
    private Device device;
    private Location location;
    private List<Comment> comments;
    private CommentListViewAdapters adapter;
    private MuensterInsideAndroidApplication myApp;
    private String voteString;
    private Button up;
    private Button down;
    private TextView exampleVote;
    private int device_id;
    private String url;
    public static final String TAG = "LocationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        // Hier wird das Aussehen der LocationActivity ausgewählt
        setContentView(R.layout.activity_location);

        // Die von den Category Fragments übergebenden Parameter werden hier zugewiesen
        Intent intent = getIntent();

        // Ein Application Objekt wird erzeugt
        myApp = (MuensterInsideAndroidApplication) getApplication();

        SharedPreferences bool = getSharedPreferences("MyCommentBoolPref", Context.MODE_PRIVATE);
        SharedPreferences myCatIdPref = getSharedPreferences("MyCatIdPref", Context.MODE_PRIVATE);

        SharedPreferences sharedpreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        device_id = sharedpreferences.getInt("deviceId", 0);

        context = this;

        if(bool.getBoolean("newCommentBool", false)==true){
            // Wenn ein neuer Kommentar erstellt wurde, wird die gleiche Location/Category angezeigt
            loc_id = bool.getInt("loc_id", 0);
            cat_id = bool.getInt("cat_id", 0);
        }
        else {
            // Sonst wird die Location angezeigt, die in der MainActivity ausgewählt wurde
            loc_id = intent.getIntExtra("loc_id", 0);
            cat_id = myCatIdPref.getInt("catId", 0);
        }

        /* Die newCommentBool ist dafür da, um auf die Location zurück zu kommen,
         * auf der man vorher war, bevor man WriteCommentActivity aufgerufen hat.
         * Und hier wird der boolean, der vorher für die Überprüfung gebraucht wurde, wieder auf false
         * gesetzt.
         */
        SharedPreferences.Editor edit = bool.edit();
        edit.putBoolean("newCommentBool", false);
        edit.commit();

        // TextView für die Darstellung des Namens wird erzeugt
        TextView exampleName = (TextView) findViewById(R.id.textViewExampleName);

        // TextView für die Darstellung des VoteValues wird erzeugt
        exampleVote = (TextView) findViewById(R.id.textViewExampleVote);

        // TextView für die Darstellung des Links wird erzeugt
        TextView exampleLink = (TextView) findViewById(R.id.textViewExampleLink);

        // TextView für die Darstellung der Beschreibung wird erzeugt
        TextView exampleDescription = (TextView) findViewById(R.id.textViewExampleDescription);

        // ListView für die Darstellung der 3 neusten Kommentare wird erzeugt
        ListView kommentare = (ListView) findViewById(R.id.smallCommentList);

        // Button zum Schreiben eines Kommentares wird erzeugt
        Button writeComment = (Button) findViewById(R.id.writeComment);

        // Button für den Upvote wird erzeugt
        up = (Button) findViewById(R.id.up);

        // Button für den Downvote wird erzeugt
        down = (Button) findViewById(R.id.down);

        // Button zum Anzeigen der Kommentare wird erzeugt
        Button showComment = (Button) findViewById(R.id.showComment);
        // Button zum Anzeigen der Kommentare wird unterstrichen
        showComment.setPaintFlags(showComment.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        //Netz erreichbar vorhanden ? Konnektivität wird geprüft.
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            // LocationTask wird aufgerufen, um eine Location abzurufen
            LocationTask locationTask = new LocationTask(this, myApp, loc_id, device_id);
            locationTask.execute();

            // ShowCommentTask wird aufgerufen, um alle Kommentare der Location abzurufen
            ShowCommentTask showCommentTask = new ShowCommentTask(this, myApp, loc_id);
            showCommentTask.execute();

            //Speichert die in den TaskKlassen aufgerufen Locationen, Comments in Variablen/Listen
            try {
                location = locationTask.get();
                comments = showCommentTask.get();
                device = myApp.getDevice();
            } catch (Exception e) {
                location = null;
                comments = null;
                device = null;
                e.printStackTrace();
            }

            // Wenn keine Kommentare vorhanden ist, wird ein Toast ausgegeben (Keine Kommentare vorhanden)
            if (comments == null) {
                CharSequence text = "Keine Kommentare vorhanden.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
            }
            else {

                //Wenn keine Kommentare vorhanden sind, wird die Anzahl der Kommentare auf 0 gesetzt
                int size;
                if (comments == null) {
                    size = 0;
                } else {
                    size = comments.size();
                }

                /** Es sollen nur die ersten 3 Kommentare in der LocationActivity ausgegebn werden.
                 *  Überprüft, ob es überhaupt 3 Kommentare ausgegebn werden können/ es überhaupt 3 Kommentare gibt.
                 */
                //Erstellt eine Liste der angezeigten Kommentare
                ArrayList<HashMap<String, String>> list;
                list = new ArrayList<HashMap<String, String>>();

                switch (size) {

                    //wenn keine Kommentare vorhanden sind, passiert nichts
                    case 0:
                        break;

                    //wenn nur ein Kommentar vorhanden ist, wird nur der erste ausgegeben
                    case 1:
                        for (int i = 0; i < 1; i++) {
                            HashMap<String, String> temp = new HashMap<String, String>();
                            temp.put(FIRST_COLUMN, comments.get(i).getText());
                            temp.put(SECOND_COLUMN, String.valueOf(comments.get(i).getDate()));
                            list.add(temp);
                        }
                        break;

                    //Wenn nur zwei Kommentare vorhanden sind, werden nur die ersten zwei ausgegebn
                    case 2:
                        for (int i = 0; i < 2; i++) {
                            HashMap<String, String> temp = new HashMap<String, String>();
                            temp.put(FIRST_COLUMN, comments.get(i).getText());
                            temp.put(SECOND_COLUMN, String.valueOf(comments.get(i).getDate()));
                            list.add(temp);
                        }
                        break;


                    case 3:
                        for (int i = 0; i < 3; i++) {
                            HashMap<String, String> temp = new HashMap<String, String>();
                            temp.put(FIRST_COLUMN, comments.get(i).getText());
                            temp.put(SECOND_COLUMN, String.valueOf(comments.get(i).getDate()));
                            list.add(temp);
                        }
                        break;

                    // Bei  drei oder mehr Kommentare, werden die letzten drei ausgegebn
                    default:
                        for (int i = 0; i < 3; i++) {
                            HashMap<String, String> temp = new HashMap<String, String>();
                            temp.put(FIRST_COLUMN, comments.get(i).getText());
                            temp.put(SECOND_COLUMN, String.valueOf(comments.get(i).getDate()));
                            list.add(temp);
                        }
                        break;
                }

                // Es wird ein Adapter erstellt der die listView mit Einträgen befüllt
                adapter = new CommentListViewAdapters(context, list);
                kommentare.setAdapter(adapter);
            }


            //Überprüft, ob ein Device Objekt vorhanden ist
            if (device == null) {
                Log.e(TAG, "Device Objekt nicht gefunden.");
            }

            //Überprüft, ob ein Location Objekt vorhanden ist
            if (location == null) {
                Log.e(TAG, "Location Objekt nicht gefunden.");
            }

            final Location l = location;


            voteString = String.valueOf(location.getVoteValue());

            // TextView für den Namen der Location wird befüllt
            exampleName.setText(location.getName());

            // TextView für den VoteValue der Location wird befüllt
            exampleVote.setText(voteString);

            // TextView für den Link der Location wird befüllt
            exampleLink.setText(location.getLink());
            exampleLink.setPaintFlags(exampleLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

            url = exampleLink.getText().toString();

            exampleLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "exampleLink.onClick() gestartet");
                    if(URLUtil.isValidUrl(url)){
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(browserIntent);
                    }
                    else {
                        CharSequence text = "Kein valider Link";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            });


            //Wenn schon gevotet wurde, wird die Hintergrundfarbe für den Down/Up Vote  auf grau gesetzt
            boolean isVoted = location.isVoted();
            if (isVoted) {
                up.setEnabled(false);
                up.setBackgroundColor(Color.GRAY);
                down.setEnabled(false);
                down.setBackgroundColor(Color.GRAY);
            }

            //Wenn eine Description vorhanden ist, wird diese ausgegebn
            if (location.getDescription() != null) {
                exampleDescription.setText(location.getDescription());
            }
            //Wenn keine Description vorhanden ist, wird "Beschreibung" und der Beschreibungstext ausgeblendet
            else {
                TextView locationDescription = (TextView) findViewById(R.id.textViewDescription);
                locationDescription.setVisibility(View.INVISIBLE);
                exampleDescription.setVisibility(View.INVISIBLE);
            }


            // führt zur WriteCommentActivity, wenn der Button gedrückt wird
            writeComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "b.onClick() gestartet");

                    //Übergibt die Kategorie_Id und die Location_Id der WriteCommentActivity, um diese richtig zuzuordnern
                    Intent myIntent = new Intent(context, WriteCommentActivity.class);
                    myIntent.putExtra("selected", cat_id);
                    myIntent.putExtra("locId", loc_id);
                    startActivity(myIntent);
                }
            });

            //führt zur ShowCommentActivity, wenn der Button gedrückt wird
            showComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "c.onClick() gestartet");

                    //Übergibt die Location_Id der ShowCommentActivity, um die richtigen Kommentare anzuzeigen
                    Intent myIntent = new Intent(context, ShowCommentActivity.class);
                    myIntent.putExtra("selected", loc_id);
                    startActivity(myIntent);
                }
            });

            //führt ein UpVote durch, wenn der Button gedrückt wird
            up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "up.onClick() gestartet");
                    UpVoteTask upVoteTask = new UpVoteTask(context, myApp, loc_id, device_id);
                    upVoteTask.execute();
                    int code;
                    try {
                        //Wir holen uns den Return Code des Servers aus dem upVoteTask
                        code = upVoteTask.get();
                    } catch (Exception e) {
                        //Bei einem Fehler, wird der Return Code auf 1 gesetzt
                        code = 1;
                        e.printStackTrace();
                    }

                    //Bei einem Return Code von 0 war der UpVote erfolgreich und es wird ein Toast ausgegeben (UpVote erfolgreich)
                    if (code == 0) {
                        CharSequence text = "UpVote erfolgreich";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        LocationTask locationTask1 = new LocationTask(context, myApp, loc_id, device_id);
                        locationTask1.execute();

                        Location location;
                        try {
                            //Wir holen uns den Return Code des Servers aus der locationTask
                            location = locationTask1.get();
                        } catch (Exception e) {
                            //Bei einem Fehler, wird der Return Code auf 1 gesetzt
                            location = l;
                            e.printStackTrace();
                        }
                        String newVoteValue = String.valueOf(location.getVoteValue());
                        exampleVote.setText(newVoteValue);

                        //Wenn schon gevotet wurde, wird die Hintergrundfarbe für den Down/Up Vote  auf grau gesetzt
                        boolean isVoted = location.isVoted();
                        if (isVoted) {
                            up.setEnabled(false);
                            up.setBackgroundColor(Color.GRAY);
                            down.setEnabled(false);
                            down.setBackgroundColor(Color.GRAY);
                        }

                        Log.i(TAG, "UpVote erfolgreich");
                    }

                    //Wenn der ReturnCode = 2 ist, dann gab es schon ein Vote
                    else if (code == 2) {
                        CharSequence text = "Es gab schon ein Vote";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Log.i(TAG, "Es gab schon ein Vote");
                    }

                    //Wenn der ReturnCode nicht 0, dann war der UpVote nicht erfolgreich
                    else {
                        CharSequence text = "UpVote nicht erfolgreich";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Log.i(TAG, "UpVote nicht erfolgreich");
                    }
                }
            });

            //führt ein DownVote durch, wenn der Button gedrückt wird
            down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "down.onClick() gestartet");
                    DownVoteTask downVoteTask = new DownVoteTask(context, myApp, loc_id, device_id);
                    downVoteTask.execute();
                    int code;
                    try {
                        //Wir holen uns den Return Code des Servers aus dem downVoteTask
                        code = downVoteTask.get();
                    } catch (Exception e) {
                        //Bei einem Fehler, wird der Return Code auf 1 gesetzt
                        code = 1;
                        e.printStackTrace();
                    }

                    //Bei einem Return Code von 0 war der DownVote erfolgreich und es wird ein Toast ausgegebn (DownVote erfolgreich)
                    if (code == 0) {
                        CharSequence text = "DownVote erfolgreich";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();


                        LocationTask locationTask1 = new LocationTask(context, myApp, loc_id, device_id);
                        locationTask1.execute();

                        Location location;
                        try {
                            //Wir holen uns den Return Code des Servers aus der locationTask
                            location = locationTask1.get();
                        } catch (Exception e) {
                            //Bei einem Fehler, wird der Return Code auf 1 gesetzt
                            location = l;
                            e.printStackTrace();
                        }

                        //Die Zahl der Votes verringert sich
                        String newVoteValue = String.valueOf(location.getVoteValue());
                        exampleVote.setText(newVoteValue);

                        //Wenn schon gevotet wurde, wird die Hintergrundfarbe für den Down/Up Vote  auf grau gesetzt
                        boolean isVoted = location.isVoted();
                        if (isVoted) {
                            up.setEnabled(false);
                            up.setBackgroundColor(Color.GRAY);
                            down.setEnabled(false);
                            down.setBackgroundColor(Color.GRAY);
                        }

                        Log.i(TAG, "DownVote erfolgreich");
                    }
                    //Wenn der ReturnCode = 2 ist, dann gab es schon ein Vote
                    else if (code == 2) {
                        CharSequence text = "Es gab schon ein Vote";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Log.i(TAG, "Es gab schon ein Vote");
                    }
                    //Wenn der ReturnCode nicht 0, dann war der DownVote nicht erfolgreich
                    else {
                        CharSequence text = "UpVote nicht erfolgreich";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Log.i(TAG, "UpVote nicht erfolgreich");
                    }

                }
            });
        }
        else{

            //Ist keine Verbindung vorhanden, passiert nichts und der Toast wird ausgegeben (Verbindung fehlgeschlagen)
            Log.d(TAG, "Keine Internetverbindung");
            Toast.makeText(LocationActivity.this, "Verbindung fehlgeschlagen", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu() gestartet");
        //Hier füllen (inflate) wir das Options Menu mit dem Menüeintrag,
        // den wir in der XML-Datei menu_main.xml definiert haben.
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
        //Wenn "Home" gedrückt wurde, rufen wir die MainActivity auf
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
