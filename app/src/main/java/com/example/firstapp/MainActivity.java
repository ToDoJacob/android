package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.Button);
        TextView tv = findViewById(R.id.txtHello);
        EditText et = findViewById(R.id.txtName);
        btn.setOnClickListener(v -> {
//            String msg = tv.getText().toString();
//            tv.setText("value has been changed.");
            String msget = et.getText().toString();
            Toast.makeText(this, msget, Toast.LENGTH_SHORT).show();
            tv.setText(msget);
        });
    }
}