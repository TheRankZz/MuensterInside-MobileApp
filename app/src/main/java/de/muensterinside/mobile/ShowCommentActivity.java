package de.muensterinside.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.entities.Comment;


public class ShowCommentActivity extends AppCompatActivity {

    private MuensterInsideAndroidApplication myApp;
    private List<Comment> comments;
    private ListView listView;
    public static final String TAG = "ShowCommentActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_comment);
        myApp = (MuensterInsideAndroidApplication) getApplication();


        listView = (ListView) findViewById(R.id.listView);

        // Der Webservice wird aufgerufen und alle Comments werden in eine Liste gespeichert
        try {
            Intent myIntent = getIntent();
            final int loc_Id = myIntent.getIntExtra("selected", 0);
            comments = myApp.getMuensterInsideMobile().getCommentsByLocation(loc_Id);

            String name = myIntent.getStringExtra("name");




            List myList = new ArrayList<String>();
            myApp.getMuensterInsideMobile().saveComment(name, 2 ,loc_Id);
            for(int i=0; i < comments.size(); i++){
                myList.add(comments.get(i).getText());



            }




            // Es wird ein Adapter erstellt der die listView mit einträgen befüllt
            ArrayAdapter<String> adapter;
            adapter=new ArrayAdapter<String>(this, R.layout.content_item_list_category, myList);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            Log.i(TAG, "Kommentare werden erfolgreich angezeigt");
        }
        catch(Exception e){
            Log.e(TAG, "Kommentare werden nicht erfolgreich angezeigt");
            e.printStackTrace();
        }






    }
}
