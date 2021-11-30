package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class InsertActivity extends AppCompatActivity {
    EditText txtName3, txtId3, txtAge3, txtPhone3, txtResult3;
    Button btnIns3, btnDel3, btnSel3, goBack2;
    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        txtName3 = findViewById(R.id.txtPhone3);
        txtAge3 = findViewById(R.id.txtAge3);
        txtPhone3 = findViewById(R.id.txtName3);
        txtResult3 = findViewById(R.id.txtResult3);
        btnIns3 = findViewById(R.id.btnIns3);
        btnDel3 = findViewById(R.id.btnDel3);
        btnSel3 = findViewById(R.id.btnSel3);


        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext()); //DB, table 생성
        //뒤로가기
        goBack2 = findViewById(R.id.goBack2);
        goBack2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });


        //등록
        btnIns3.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase(); //DB
            String sqlInsert = "INSERT INTO emp " +
                    "(name, age, mobile) VALUES ('" + txtName3.getText().toString() + "', '" + txtAge3.getText().toString()
                    + "','" + txtPhone3.getText().toString() + "')";
            db.execSQL(sqlInsert);
            db.close();
            //???쓰는방법
//            String sqlInsert = "INSERT INTO emp " +
//                    "(name, age, mobile) VALUES ( ? , ? , ?)";
//            db.execSQL(sqlInsert, new Object[]{txtName.getText(),txtAge.getText(),txtPhone.getText()});
//            Toast.makeText( this, txtName.getText()+"님 등록완료",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
            startActivityForResult (intent,1);
            finish();

        });

        //삭제
        btnDel3.setOnClickListener(v -> {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
//                String sqlDel = "DELETE FROM emp WHERE _id='"+txtId.getText().toString()+"'";
//                db.execSQL(sqlDel);
                    db.delete("emp", "_id=?", new String[]{txtId3.getText().toString()});
                    Toast.makeText(this, txtId3.getText() + "번 삭제완료", Toast.LENGTH_LONG).show();
                }
        );

        //단건조회
        btnSel3.setOnClickListener(v -> {
                    list.removeAll(list);
                    SQLiteDatabase db = dbHelper.getReadableDatabase();

                    String sql = "SELECT _id, name, age, mobile FROM emp WHERE _id='" + txtId3.getText().toString()
                            + "'OR name='" + txtName3.getText().toString()
                            + "'OR age='" + txtAge3.getText().toString()
                            + "'OR mobile='" + txtPhone3.getText().toString() + "'";

                    Cursor cursor = db.rawQuery(sql, null);
                    while (cursor.moveToNext()) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("_id", cursor.getString(0));
                        map.put("name", cursor.getString(1));
                        map.put("age", cursor.getString(2));
                        map.put("mobile", cursor.getString(3));
                        list.add(map);
                    }
                    String sel = "";
                    for (int i = 0; i < list.size(); i++) {
                        sel += "id:" + list.get(i).get("_id") + " ,n:" + list.get(i).get("name")
                                + " ,age:" + list.get(i).get("age") + " ,ph:" + list.get(i).get("mobile") + "\n";
                    }
                    txtResult3.setText(sel);
                    Toast.makeText(this, "조건 조회", Toast.LENGTH_LONG).show();
                }
        );
    }
}