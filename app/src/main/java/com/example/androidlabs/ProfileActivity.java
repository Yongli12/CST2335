package com.example.androidlabs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ProfileActivity extends AppCompatActivity {

    ImageButton mImageButton;
    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        //get the email address from the previous one
        Intent fromPrevious = getIntent();
        String previousTyped = fromPrevious.getStringExtra("typed");

        EditText editEmail = (EditText) findViewById(R.id.inEmail);
        editEmail.setText(previousTyped);

        //give the image button action to take a picture
        mImageButton = (ImageButton)findViewById(R.id.inPicture);
        mImageButton.setOnClickListener(m -> {
            dispatchTakePictureIntent();
        });
        Button chatB = (Button)findViewById(R.id.chatB);
        chatB.setOnClickListener(c ->{
            Intent chat = new Intent(ProfileActivity.this, ChatRoomActivity.class);
            startActivity(chat);
        });

        Button toolB = (Button)findViewById(R.id.toolbar);
        toolB.setOnClickListener(c -> {
            Intent tool = new Intent(ProfileActivity.this, TestToolbar.class);
            startActivity(tool);
        });

        Log.e(ACTIVITY_NAME,"In function: onCreate");
       // Log.d("test", "yongli");
    }

    //take a picture function
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    //if the picture ok, it will be saved under the name "data"
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
        }
        Log.e(ACTIVITY_NAME,"In function: onActivityResult");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(ACTIVITY_NAME,"In function: onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(ACTIVITY_NAME,"In function: onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(ACTIVITY_NAME,"In function: onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(ACTIVITY_NAME,"In function: onStart");
    }



}
