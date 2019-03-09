package com.example.androidlabs;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestToolbar extends AppCompatActivity {
    private String message = "";
    Toolbar tBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        tBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(tBar);
        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);


        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.item4:
                //Show the toast immediately:
                if(message.equals(""))
                    Toast.makeText(this, "You clicked on the overflow menu", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                message = "";
                break;
            case R.id.item1:
                //Show the toast immediately:
                Toast.makeText(this, "This is the initial message", Toast.LENGTH_LONG).show();
                break;
            case R.id.item2:
                customDialog();
                break;
            case R.id.item3:
                Snackbar sb = Snackbar.make(tBar, "Go Back?", Snackbar.LENGTH_LONG)
                        .setAction("Yes", e -> {
                            finish();
                        });
                sb.show();
        }

        return true;
    }

    private void customDialog(){
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.customdialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //find the edit box
        EditText edit = v.findViewById(R.id.newMessage);

        builder.setPositiveButton("Positive", (dialog, id)->{
            message = edit.getText().toString();
                } )
        .setNegativeButton("Negative", (dialog, id)->{

        }).setView(v);
        builder.create().show();
    }

}
