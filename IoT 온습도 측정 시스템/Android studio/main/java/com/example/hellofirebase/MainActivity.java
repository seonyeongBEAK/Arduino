package com.example.hellofirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/* public class MainActivity extends AppCompatActivity {

FirebaseDatabase db = FirebaseDatabase.getInstance ();
        DatabaseReference myRef = db.getReference ("message");
//    DatabaseReference myRef = db.getReference ();

        TextView textView;
        EditText editText;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        setTitle ("Hello Firebase");

        textView = (TextView) findViewById (R.id.textView);
        editText = (EditText)findViewById(R.id.editText);
        }

public void btnPut(View view)
        {
        String inData = editText.getText().toString ();

        // Firebase에 key에 대한 value 저장
        myRef.setValue(inData);
        editText.setText ("");

        }

public void btnGet(View view) {
        // *********************** addValueEventListener *********************************
        myRef.addValueEventListener (new ValueEventListener() {
@Override
public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String value = dataSnapshot.getValue (String.class);
        textView.setText (value);
        }

@Override
public void onCancelled(@NonNull DatabaseError databaseError) {

        }
        });

        }
        }

 */

public class MainActivity extends AppCompatActivity {

        FirebaseDatabase db = FirebaseDatabase.getInstance ();
        DatabaseReference myRef = db.getReference ("log-DHT");
//    DatabaseReference myRef = db.getReference ();

        TextView textView;
        EditText editText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate (savedInstanceState);
                setContentView (R.layout.activity_main);
                setTitle ("Hello Firebase");

                textView = (TextView) findViewById (R.id.textView);
                editText = (EditText)findViewById(R.id.editText);
        }

        public void btnPut(View view)
        {
                String inData = editText.getText().toString ();
                //myRef.setValue(inData);
                //myRef.push.setValue(inData);
                myRef.push().child("Humidity").setValue(inData);
                editText.setText ("");
        }

        public  void btnGet(View view) {

                myRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                String data = dataSnapshot.getValue().toString();
                                textView.append (data.toString() + "\n");
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                });
        }
}