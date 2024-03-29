package com.example.sharedprefs;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText todoInput;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoInput = findViewById(R.id.todo_input);

        saveButton = findViewById(R.id.savebtn);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  message = todoInput.getText().toString();
                if(!TextUtils.isEmpty(message)){
                    // write if not empty
                    writeToFile(message);
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                }
            }
        });

        try {
            if(readFromFile() != null) {
                todoInput.setText(readFromFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void writeToFile(String message) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("todo_list.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(message);
            outputStreamWriter.close(); // close your stream always. need indeed
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromFile() throws IOException {
        String result = "";

        InputStream inputStream =  openFileInput("todo_list.txt");

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String tempString = "";

            StringBuilder stringBuilder = new StringBuilder();

            while ((tempString = bufferedReader.readLine()) != null) {
                stringBuilder.append(tempString);
            }

            inputStream.close();
            result = stringBuilder.toString();
        }

        return result;
    }
}
