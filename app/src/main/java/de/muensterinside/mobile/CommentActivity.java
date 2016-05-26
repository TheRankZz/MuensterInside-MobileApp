package de.muensterinside.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import de.muensterinside.mobile.entities.Comment;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class CommentActivity extends AppCompatActivity{

    //Anlegen des Textfeldes
    EditText kommentar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        //Zuweisung der XML Objekte an unsere Variablen
        kommentar = (EditText)findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);


        Intent ii = getIntent();
        final String androidId = ii.getStringExtra("androidId");



        //ClickListener implementieren für den Button zum Wechsel der Activity
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                String s = kommentar.getText().toString();
                Intent ii = new Intent(CommentActivity.this,  ShowCommentActivity.class);

                ii.putExtra("name", s);
                ii.putExtra("deviceId", androidId);
                startActivity(ii);
            }
        });




    }





}
