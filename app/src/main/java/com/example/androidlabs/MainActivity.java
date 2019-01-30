package com.example.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //declare the variables
    SharedPreferences sp;
    EditText inputEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab3);

        //give the login button action to go to the Profile page
        Button loginButton = (Button)findViewById(R.id.login);
        inputEmail = (EditText)findViewById(R.id.inputEmail);
        loginButton.setOnClickListener(b -> {
            Intent nextPage = new Intent(MainActivity.this, ProfileActivity.class);
            //let the email address which is taped in the login page to show up in the profile page
            nextPage.putExtra("typed", inputEmail.getText().toString());
            startActivityForResult(nextPage, 1);
        });

        sp = getSharedPreferences("EmailAddress", Context.MODE_PRIVATE);
        String saveString = sp.getString("Email", "");
        inputEmail.setText(saveString);


    }

    @Override
    protected void onPause(){
        super.onPause();

        //get an editor object
        SharedPreferences.Editor editor = sp.edit();
        //save what was typed under the name "Email"
        String whatWasTyped = inputEmail.getText().toString();
        editor.putString("Email", whatWasTyped);
        //write it to disk:
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
