package com.example.mydiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);


        dbHelper = new DBHelper(getApplicationContext()); //db초기화

        Button btnWriterForm = findViewById(R.id.btnWriteForm);
        lv = findViewById(R.id.listDiary);

        ArrayList<DiaryVO> list = DiaryDAO.selectAll(dbHelper);
        //listview 초기화 adapter지정 ->
        DiaryAdapter adapter = new DiaryAdapter(list);
        lv.setAdapter(adapter);

        //아이템클릭 이벤트 : 수정, 삭제 (alertDialog)
        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            String id = list.get(i).getId();
            System.out.println("id"+id);
            String title = list.get(i).getTitle();
            String content = list.get(i).getContent();
            String img = list.get(i).getImg();
//            String time = list.get(i).getTime();
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("수정이나 삭제버튼을 눌러주세요.")
                    .setPositiveButton("수정",(dialogInterface, i1) -> {
                        Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                        intent.putExtra("id",id);
                        intent.putExtra("title",title);
                        intent.putExtra("content",content);
                        intent.putExtra("img",img);
                        startActivity(intent);
//                        startActivityForResult(intent,0);
                        ((DiaryAdapter)lv.getAdapter()).setData(DiaryDAO.selectAll(dbHelper));
                        ((DiaryAdapter)lv.getAdapter()).notifyDataSetChanged();
                    })
                    .setNegativeButton("삭제",(dialogInterface, i1) -> {
                        DiaryDAO.delete(dbHelper, id);
                        Log.d("string",i+"");
                        list.remove(i);
                        ((DiaryAdapter)lv.getAdapter()).notifyDataSetChanged();
                    })
                    .create()
                    .show();
        });
        //쓰기버튼 이벤트 지정 : writeActivity로 이동

        btnWriterForm.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
            startActivity(intent);

            });

    }
}