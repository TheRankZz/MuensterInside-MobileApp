package de.muensterinside.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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



    }
}
