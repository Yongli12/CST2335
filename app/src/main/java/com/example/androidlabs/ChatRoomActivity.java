package com.example.androidlabs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {

    private ArrayList<Message> messages;
    private Button receive;
    private Button send;
    private TextView rowText;
    private View newView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chatroom);

        //ListAdapter adt = new MyArrayAdapter(new String[] {"A", "B", "C"});
        ListAdapter adt = new MyOwnAdapter();

        ListView theList = (ListView)findViewById(R.id.the_list);
        EditText messageType = (EditText)findViewById(R.id.type);
        receive = (Button)findViewById(R.id.receiveB);
        send = (Button)findViewById(R.id.sendB);

        messages = new ArrayList<>();
        theList.setAdapter(adt);

        receive.setOnClickListener(c-> {
            messages.add(new Message(messageType.getText().toString(),"receive"));
            messageType.setText("");
        });
        send.setOnClickListener(c-> {
            messages.add(new Message(messageType.getText().toString(), "send"));
            messageType.setText("");
        });
    }

    private class MyOwnAdapter extends BaseAdapter{
        public MyOwnAdapter() {

            super();
        }

        @Override
        public int getCount() {

            return messages.size() ;
        }

        @Override
        public Message getItem(int position) {

            return messages.get(position);
        }

        @Override
        public View getView(int position, View old, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();

            Message me = getItem(position);

            if(me.getAction().equals("receive")){
                newView = inflater.inflate(R.layout.single_row_receive, null);
                Message stringToShow = getItem(position);
                rowText = newView.findViewById(R.id.receiveOnRow);
                rowText.setText(stringToShow.getMessage());
            }
            if(me.getAction().equals("send")){
                newView = inflater.inflate(R.layout.single_row_send, null);
                Message stringToShow = getItem(position);
                rowText = newView.findViewById(R.id.sendOnRow);
                rowText.setText(stringToShow.getMessage());
            }
            return newView;
        }

        @Override
        public long getItemId(int position) {

            return position;
        }
    }

    @Override
    protected void onPause(){
        super.onPause();

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
