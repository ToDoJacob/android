package com.example.mylayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    GridLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linear = findViewById(R.id.linear);

        View.OnClickListener handler = v -> {
//            ((Button) v).getText();
//            for (int i = 0 ; i < v.length; i++){
//            }

            Toast.makeText(this,String.valueOf(v),Toast.LENGTH_LONG).show();
        };

        List numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        Collections.shuffle(numList);



//        int[] list = new ArrayList<>();
//        List<Integer> list = new ArrayList<Integer>({1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});



        for (Object i : numList) { //1차원배열 16개의 임의의 순서
            Button btn = new Button(this);
            btn.setText(String.valueOf(i));
            linear.addView(btn);
            btn.setOnClickListener(handler);
        }

//        Button btn = new Button(this);
//        btn.setText("생성된 버튼");
//        linear.addView(btn);
//        btn.setOnClickListener(handler);
//        btn.setOnClickListener((v ->{
//            Toast.makeText(this,"클릭됨",Toast.LENGTH_LONG).show();
//        }));

    }
}