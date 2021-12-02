package com.example.mydiallog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1, btn2, btn3, btn4;

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        String[] city = new String[]{"대구", "서울", "부산"};

        btn1.setOnClickListener(v -> {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("제목")
                    .setMessage("alert")
                    .setPositiveButton("수정", (daialogInterface, i) -> {
                        Log.d("alert", "수정버튼");
                    })
                    .setNegativeButton("삭제", (di, i) -> {
                        Log.d("alert", "삭제버튼");
                    })
                    .create()
                    .show();
        });

//        btn2.setOnClickListener(v -> {
//            AlertDialog.Builder ad = new AlertDialog.Builder(this);
//            ad.setItems(city, (d, i) -> {
//                Log.d("alert", city[i]);
//            })
//                .create()
//                .show();
//        });

        ArrayList selectedItems = new ArrayList<String>();

        btn2.setOnClickListener(v -> {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setMultiChoiceItems(city, null,
                    (dialog, which, isChecked) -> {
                        if (isChecked) {
                            selectedItems.add(which);
                        } else if (selectedItems.contains(which)) {
                            selectedItems.remove(which);
                        }
                    })
                    // Set the action buttons
                    .setPositiveButton("확인", (dialog, id) ->{
                        //선택된 값들을 city배열에서 찾아서 출력
//                        city.stream().filter().map(System.out::print); stream filter쓰는법 찾아보기
                        for(Object i: selectedItems){
                            int pos = ((Integer)i).intValue();
                            Log.d("alert",city[pos]);
                        }

                    })
                    .setNegativeButton("취소", (dialog, id) ->{

                    })
                    .create()
                    .show();

        });
        //커스텀 다이얼로그
        btn3.setOnClickListener(v -> { customModal(); });
        btn4.setOnClickListener(v -> {

        });
    }
    //클릭하면 모달에 띄우는 메서드
    private void customModal(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(R.layout.activity_login)
                .create()
                .show();
    }
}