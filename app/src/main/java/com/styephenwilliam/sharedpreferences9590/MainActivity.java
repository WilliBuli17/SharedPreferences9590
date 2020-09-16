package com.styephenwilliam.sharedpreferences9590;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    public static final int mode = Activity.MODE_PRIVATE;

    private String firstName = "";
    private String lastName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadPreverences();
        setProfile();
        Button addBtn = findViewById(R.id.btn_save);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePreferences();
            }
        });
    }

    private void setProfile(){
        TextInputEditText firstNameText = findViewById(R.id.input_first);
        TextInputEditText lastNameText = findViewById(R.id.input_last);
        firstNameText.setText(firstName);
        lastNameText.setText(lastName);
    }

    private void loadPreverences(){
        String name = "profile";
        preferences = getSharedPreferences(name,mode);
        if(preferences!=null){
            firstName = preferences.getString("firstName", "");
            lastName = preferences.getString("lastName", "");
        }
    }

    private void savePreferences(){
        TextInputEditText firstNameText = findViewById(R.id.input_first);
        TextInputEditText lastNameText = findViewById(R.id.input_last);
        SharedPreferences.Editor editor = preferences.edit();
        if(!firstNameText.getText().toString().isEmpty() && !lastNameText.getText().toString().isEmpty()){
            editor.putString("firstName", firstNameText.getText().toString());
            editor.putString("lastName", lastNameText.getText().toString());
            editor.apply();
            Toast.makeText(this, "Profile Saved", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Fill Correctly", Toast.LENGTH_SHORT).show();
        }
    }
}