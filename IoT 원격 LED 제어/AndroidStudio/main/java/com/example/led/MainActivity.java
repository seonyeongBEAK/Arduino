package com.example.led;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    Button ledOnBtn;
    Button ledOffBtn;
    TextView textView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();          //FirebaseDatabase와 연동
    DatabaseReference myRef = database.getReference("LED_STATUS"); //LED_STATUS 값 가져와서 myRef에 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.InitializeView();
        this.SetListener();
    }

    public void InitializeView() {
        setTitle("LED Remote Control");//title 지정
        ledOnBtn = (Button)findViewById(R.id.ledOnBtn);//button 가져오기
        ledOffBtn = (Button)findViewById(R.id.ledOffBtn);
        textView = (TextView)findViewById(R.id.textView);//textView 가져오기
        textView.setText(myRef.getKey());//textView에 LED_STATUS값 출력
    }

    public  void SetListener() {
        //DB에서 읽어온 LED_STATUS 값 textView에 넣어주기
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String ledState = (String) dataSnapshot.getValue();//LED_STATUS 가져와서 ledState에 저장
                textView.setText("LED is " + ledState);//LED_STATUS값 textView에 출력
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        // ledOnBtn 클릭 이벤트
        ledOnBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                textView.setBackgroundColor(Color.RED);//ledOnBtn 버튼 클릭 시 Background 색을 RED 설정
                myRef.setValue("ON");//DB값 ON으로 변경
            }
        });

        // ledOffBtn 클릭 이벤트
        ledOffBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                textView.setBackgroundColor(Color.WHITE);//ledOnBtn 버튼 클릭 시 Background 색을 WHITE 설정
                myRef.setValue("OFF");//DB값 OFF으로 변경
            }
        });
    }
}