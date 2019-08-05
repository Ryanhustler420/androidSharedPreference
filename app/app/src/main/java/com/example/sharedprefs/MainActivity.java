package com.example.sharedprefs;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText enterMessage;
    private TextView result;
    private Button saveButton;

    private SharedPreferences myPrefs;

    private static final String PREFS_NAME = "myPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterMessage = findViewById(R.id.enter_message);
        result = findViewById(R.id.result_textView);

        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 0 - access code for all user
                myPrefs = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putString("message", enterMessage.getText().toString());
                editor.apply();

                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        });

        // Get the data back
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);

        if (prefs.contains("message")) {
           String message = prefs.getString("message", "not found");
           result.setText(message);
        }

    }
}
