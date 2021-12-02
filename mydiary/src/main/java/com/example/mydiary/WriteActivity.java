package com.example.mydiary;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class WriteActivity extends AppCompatActivity {
    DBHelper dbHelper;
    Button btnSave, btnImage;
    EditText editTitle, editContent;
    DiaryDAO diaryDAO;
    DiaryVO diaryVO;
    String id ="";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        btnSave = findViewById(R.id.btnSave);
        btnImage = findViewById(R.id.btnImage);
        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        dbHelper = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            id = intent.getExtras().getString("id");
            String title = intent.getExtras().getString("title");
            String content = intent.getExtras().getString("content");
            editTitle.setText(title);
            editContent.setText(content);
        }


        //등록 & 수정 버튼
        btnSave.setOnClickListener(v -> {
            if (id.equals("")) {
                System.out.println("id"+id);
                diaryDAO = new DiaryDAO();
                diaryVO = new DiaryVO();
                diaryVO.setTitle(editTitle.getText().toString());
                diaryVO.setContent(editContent.getText().toString());

                diaryDAO.insert(dbHelper, diaryVO);

                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
                finish();
            } else {
                diaryDAO = new DiaryDAO();
                diaryVO = new DiaryVO();
                diaryVO.setId(id);
                diaryVO.setTitle(editTitle.getText().toString());
                diaryVO.setContent(editContent.getText().toString());
                diaryDAO.update(dbHelper,diaryVO);
                Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent3);
                finish();
            }
        });
    }
}
