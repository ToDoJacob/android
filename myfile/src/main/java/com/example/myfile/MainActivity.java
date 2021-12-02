package com.example.myfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    Button btnRead, btnWrite;
    DatePicker dp;
    TextView edtDiary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnWrite = (Button) findViewById(R.id.btnWrite);
        dp = (DatePicker) findViewById(R.id.datePicker1);
        edtDiary = findViewById(R.id.edtDiary);

        dp.init(2021, 11, 2, (datePicker, i, i1, i2) -> {
            //넘어오는 i i1 i2 년월일을 가지고 파일명 만들기 "2021
            //파일을 읽어서 editview에 보이도록함
            i1 = i1 + 1;
            String fileName = "" + i
                    + ((i1 < 10) ? "0" + i1 : i1)
                    + (i2 < 10 ? ("0" + i2) : i2) + ".txt";
            fileRead(fileName);
        });
        btnWrite.setOnClickListener(v -> {
            try {
                dp.getDayOfMonth();
                FileOutputStream outFs = openFileOutput("file.txt", Context.MODE_PRIVATE);
                String str = "쿡북 안드로이드";
                outFs.write(str.getBytes());
                outFs.close();
                Toast.makeText(getApplicationContext(), "file.txt가생성됨", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    FileInputStream inFs = openFileInput("file.txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                    inFs.close();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "파일없음", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void fileRead(String filename) {
        try {
            edtDiary.setText("");
            FileInputStream inFs = openFileInput(filename);
            byte[] txt = new byte[30];
            inFs.read(txt);
            String str = new String(txt);

            inFs.close();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "파일없음", Toast.LENGTH_SHORT).show();
        }
    }
}