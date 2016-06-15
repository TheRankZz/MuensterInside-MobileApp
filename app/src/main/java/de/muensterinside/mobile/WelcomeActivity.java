package de.muensterinside.mobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */


public class WelcomeActivity extends AppCompatActivity {

    private TextView username;
    private Button button;
    private String TAG ="WelcomeActivity";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hier wird der Activity das Aussehen zugeordnet
        setContentView(R.layout.activity_welcome);
        username = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button2);

        context = this;

        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        username.setText(name + ",");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "b.onClick() gestartet");
                Intent myIntent = new Intent(context, MainActivity.class);
                startActivity(myIntent);

            }
        });

    }
}
