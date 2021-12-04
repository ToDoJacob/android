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
    GridLayout grid;
    Button btn;
    int start = 1;
    List<Button> btns = new ArrayList<>();


    public void reset() {
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        Collections.shuffle(numList);
//        for (Object i : numList) { //1차원배열 16개의 임의의 순서
        for (int i=0; i < numList.size() ; i++) { //1차원배열 16개의 임의의 순서
            btns.get(numList.get(i)-1).setText(String.valueOf(i+1));
//            grid.addView(btn);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.grid);

        View.OnClickListener handler = v -> {
            int num = Integer.parseInt(((Button) v).getText().toString());
//          for (int i = 1 ; i < numList.size()+1; i++){
            if (num == start) {
                ((Button) v).setText("");
                Toast.makeText(this, "다음 번호를 눌러주세요.", Toast.LENGTH_SHORT).show();
                start++;
                if(start == 17){
                    Toast.makeText(this, "게임 승리", Toast.LENGTH_SHORT).show();
                    start = 1;
                    reset();
                }
            } else {
                Toast.makeText(this, "선택하신 번호가 순서에 맞지 않습니다.", Toast.LENGTH_SHORT).show();
            }
//            }
        };

        List numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        Collections.shuffle(numList);
        for (Object i : numList) { //1차원배열 16개의 임의의 순서
            btn = new Button(this);
            btn.setText(String.valueOf(i));
            grid.addView(btn);
            btns.add(btn);
            btn.setOnClickListener(handler);
        }


//        int[] list = new ArrayList<>();
//        List<Integer> list = new ArrayList<Integer>({1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});


//        Button btn = new Button(this);
//        btn.setText("생성된 버튼");
//        linear.addView(btn);
//        btn.setOnClickListener(handler);
//        btn.setOnClickListener((v ->{
//            Toast.makeText(this,"클릭됨",Toast.LENGTH_LONG).show();
//        }));

    }
}