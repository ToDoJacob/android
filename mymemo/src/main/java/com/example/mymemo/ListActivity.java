package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {

    EditText txtResult;
    Button btnAll, goBack;
    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        txtResult = findViewById(R.id.txtResult);

        btnAll = findViewById(R.id.btnAll);

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext()); //DB, table 생성
        //Main으로 이동
        goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

//        //등록
//        btnIns.setOnClickListener(v->{
//            SQLiteDatabase db = dbHelper.getWritableDatabase(); //DB
//            String sqlInsert = "INSERT INTO emp " +
//                    "(name, age, mobile) VALUES ('"+ txtName.getText().toString() +"', '"+ txtAge.getText().toString()
//                    +"','" +txtPhone.getText().toString()+ "')";
//            db.execSQL(sqlInsert);
//            //???쓰는방법
////            String sqlInsert = "INSERT INTO emp " +
////                    "(name, age, mobile) VALUES ( ? , ? , ?)";
////            db.execSQL(sqlInsert, new Object[]{txtName.getText(),txtAge.getText(),txtPhone.getText()});
////            Toast.makeText( this, txtName.getText()+"님 등록완료",Toast.LENGTH_LONG).show();
//        });

        //전체조회
        btnAll.setOnClickListener(v->{
                list.removeAll(list);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String sql = "SELECT _id, name, age, mobile FROM emp order by _id desc";
                Cursor cursor=db.rawQuery(sql, null);
                while (cursor.moveToNext()){
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("_id", cursor.getString(0)); map.put("name", cursor.getString(1));
                    map.put("age", cursor.getString(2)); map.put("mobile", cursor.getString(3));
                    list.add(map);
                }
                String all = "";
                for(int i=0; i<list.size(); i++ ){
                    all += "id:"+list.get(i).get("_id") + " ,n:"+list.get(i).get("name")
                            + " ,age:"+list.get(i).get("age") +  " ,ph:"+list.get(i).get("mobile")+"\n";
                }
                    txtResult.setText(all);
                }
        );
//        //삭제
//        btnDel.setOnClickListener(v->{
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
////                String sqlDel = "DELETE FROM emp WHERE _id='"+txtId.getText().toString()+"'";
////                db.execSQL(sqlDel);
//                db.delete("emp", "_id=?", new String[]{txtId.getText().toString()});
//                Toast.makeText( this, txtId.getText()+"번 삭제완료",Toast.LENGTH_LONG).show();
//            }
//        );

//        //단건조회
//        btnSel.setOnClickListener(v->{
//                    list.removeAll(list);
//                    SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//                    String sql = "SELECT _id, name, age, mobile FROM emp WHERE _id='"+txtId.getText().toString()
//                            +"'OR name='"+ txtName.getText().toString()
//                            +"'OR age='"+ txtAge.getText().toString()
//                            +"'OR mobile='"+txtPhone.getText().toString()+"'";
//
//                    Cursor cursor=db.rawQuery(sql, null);
//                    while (cursor.moveToNext()){
//                        HashMap<String, String> map = new HashMap<String, String>();
//                        map.put("_id", cursor.getString(0)); map.put("name", cursor.getString(1));
//                        map.put("age", cursor.getString(2)); map.put("mobile", cursor.getString(3));
//                        list.add(map);
//                    }
//                    String sel = "";
//                    for(int i=0; i<list.size(); i++ ){
//                        sel += "id:"+list.get(i).get("_id") + " ,n:"+list.get(i).get("name")
//                                + " ,age:"+list.get(i).get("age") +  " ,ph:"+list.get(i).get("mobile")+"\n";
//                    }
//                    txtResult.setText(sel);
//                    Toast.makeText( this, "조건 조회",Toast.LENGTH_LONG).show();
//                }
//        );
    }
}