package com.example.mymemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnList, btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnList = findViewById(R.id.btnList);
        btnList.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), ListActivity.class);
            startActivity(intent);
//            startActivityForResult(intent, 1);
        });

        btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), InsertActivity.class);
            startActivity(intent);
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            Toast.makeText(this,"등록이 완료되었습니다.",Toast.LENGTH_SHORT).show();
        }
    }
}