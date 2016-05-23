package de.muensterinside.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowCommentActivity extends AppCompatActivity {

    private  TextView Textv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_comment);

        Textv = (TextView)findViewById(R.id.textView);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        String j = (String) b.get("name");
        Textv.setText(j);


        Button button = (Button) findViewById(R.id.buttonZuerueck);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {


                Intent ii = new Intent(ShowCommentActivity.this,  CommentActivity.class);
                startActivity(ii);
            }
        });





    }
}
