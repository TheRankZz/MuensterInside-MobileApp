package de.muensterinside.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import de.muensterinside.mobile.entities.Comment;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class CommentActivity extends AppCompatActivity{

    public static final String TAG = "CommentActivity";
    //Anlegen des Textfeldes
    private EditText kommentar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() gestartet");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        //Zuweisung der XML Objekte an unsere Variablen
        kommentar = (EditText)findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);




        //ClickListener implementieren für den Button zum Wechsel der Activity
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.d(TAG, "button.onClick() gestartet");
                String s = kommentar.getText().toString();
                Intent ii = new Intent(CommentActivity.this,  ShowCommentActivity.class);

                ii.putExtra("name", s);
                startActivity(ii);
            }
        });




    }





}
