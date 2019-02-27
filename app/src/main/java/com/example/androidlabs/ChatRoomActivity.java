package com.example.androidlabs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {

    private List<Message> messages = new ArrayList<>();
    private Button receive;
    private Button send;
    private View newView;
    MyDataOpenHelper dbOpener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chatroom);

        //ListAdapter adt = new MyArrayAdapter(new String[] {"A", "B", "C"});
        MyOwnAdapter adt = new MyOwnAdapter();

        //Get the fields from the screen:
        ListView theList = (ListView)findViewById(R.id.the_list);
        EditText messageType = (EditText)findViewById(R.id.type);
        receive = (Button)findViewById(R.id.receiveB);
        send = (Button)findViewById(R.id.sendB);

        //get a database:
        dbOpener = new MyDataOpenHelper(this);
        SQLiteDatabase db = dbOpener.getWritableDatabase();

        //query all the results from the database:
        String [] columns = {MyDataOpenHelper.COL_ID, MyDataOpenHelper.COL_MESSAGE, MyDataOpenHelper.COL_SEND};
        Cursor results = db.query(false, MyDataOpenHelper.TABLE_NAME, columns, null,null, null, null, null, null);

        //find the column indices:
        int idIndex = results.getColumnIndex(MyDataOpenHelper.COL_ID);
        int messageIndex = results.getColumnIndex(MyDataOpenHelper.COL_MESSAGE);
        int sendIndex = results.getColumnIndex(MyDataOpenHelper.COL_SEND);

        //iterate over the results, return true if there is a next item:
        while(results.moveToNext()){
            long id = results.getLong(idIndex);
            String message = results.getString(messageIndex);
            String dataSend = results.getString(sendIndex);
            boolean send = false;
            if(dataSend.equals("1"))
                send = true;

            if(dataSend.equals("0"))
                send = false;
            //add the new Message to the array list:
            messages.add(new Message(id, message, send));
        }
        //using an adapter object and send it to the listVIew
        theList.setAdapter(adt);

        send.setOnClickListener(c-> {
            //get the information that were typed
            String message = messageType.getText().toString();
            Message newMessage = new Message();
            newMessage.setSent(true);

            //add to the database and get the new ID
            ContentValues newRowValues = new ContentValues();
            //put string message in the MESSAGE column:
            newRowValues.put(MyDataOpenHelper.COL_MESSAGE, message);
            newRowValues.put(MyDataOpenHelper.COL_SEND, newMessage.isSent());

            //insert in the database:
            long newId = db.insert(MyDataOpenHelper.TABLE_NAME, null, newRowValues);

            //now you have the newId, you can create the Message object

            newMessage = new Message(newId, message, newMessage.isSent());


            //add the new contact to the list:
            messages.add(newMessage);

            //update the listView:
            adt.notifyDataSetChanged();

            //clear the EditText fields:
            messageType.setText("");
        });

        receive.setOnClickListener(c-> {
            //get the information that were typed
            String message = messageType.getText().toString();
            Message newMessage = new Message();

            //add to the database and get the new ID
            ContentValues newRowValues = new ContentValues();
            //put string message in the MESSAGE column:
            newRowValues.put(MyDataOpenHelper.COL_MESSAGE, message);
            newRowValues.put(MyDataOpenHelper.COL_SEND, newMessage.isSent());

            //insert in the database:
            long newId = db.insert(MyDataOpenHelper.TABLE_NAME, null, newRowValues);

            //now you have the newId, you can create the Contact object

            newMessage = new Message(newId, message, newMessage.isSent());

            //add the new contact to the list:
            messages.add(newMessage);
            //update the listView:
            adt.notifyDataSetChanged();

            //clear the EditText fields:
            messageType.setText("");

        });
        printCursor(results);

    }

    public void printCursor(Cursor c){
        Log.d("Database version: ",String.valueOf(dbOpener.VERSION_NUM));
        Log.d("Column number: ", String.valueOf(c.getColumnCount()));
        for(int i = 0; i < c.getColumnCount(); i++){
            Log.d("Column name: ", c.getColumnNames()[i]);
        }
        Log.d("Result number: ", String.valueOf(c.getCount()));
        c.moveToFirst();
        while(!c.isAfterLast()){
            String id = c.getString(c.getColumnIndex(dbOpener.COL_ID));
            String message = c.getString(c.getColumnIndex(dbOpener.COL_MESSAGE));
            String isSend = c.getString(c.getColumnIndex(dbOpener.COL_SEND));
            Log.d("Database: ", "id: " + id + "    Message: " + message + "    IsSent(1 for true, 0 for false): " + isSend);
            c.moveToNext();
        }

    }

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //coming back from the Profile activity
        super.onActivityResult(requestCode, resultCode, data);
    }*/

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

            Message messageToShow = getItem(position);
            TextView rowText = null;

            if(messageToShow.isSent()){
                System.out.print(messageToShow.isSent());
                newView = inflater.inflate(R.layout.single_row_send, null);
                rowText = newView.findViewById(R.id.message);
                rowText.setText(messageToShow.getMessage());
            }
            if(! messageToShow.isSent()){
                newView = inflater.inflate(R.layout.single_row_receive, null);
                rowText = newView.findViewById(R.id.message);
                rowText.setText(messageToShow.getMessage());
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
