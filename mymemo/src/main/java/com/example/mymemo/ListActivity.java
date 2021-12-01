package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    Button btnAll, goBack;
    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    ListView lv2;
    DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext()); //DB, table 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lv2 = findViewById(R.id.lv2);

        btnAll = findViewById(R.id.btnAll);

        //Main으로 이동
        goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

//        //데이터 조회
        List<String> result = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                result);
        lv2.setAdapter(adapter);
        lv2.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(getApplicationContext(), result.get(i), Toast.LENGTH_LONG).show();
        });

//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String sql = "select * from emp";
//        Cursor cursor = db.rawQuery(sql, null);
//        while (cursor.moveToNext()){
//            result.add(cursor.getString(1));
//        }

        //전체조회
        btnAll.setOnClickListener(v->{
                    list.removeAll(list);
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    String sql = "SELECT _id, name, age, mobile FROM emp order by _id desc";
                    Cursor cursor=db.rawQuery(sql, null);
                    //map쓰는것
//                    while (cursor.moveToNext()){
//                        HashMap<String, String> map = new HashMap<String, String>();
//                        map.put("_id", cursor.getString(0)); map.put("name", cursor.getString(1));
//                        map.put("age", cursor.getString(2)); map.put("mobile", cursor.getString(3));
//                        list.add(map);
//                    }
//                    String all = "";
//                    for(int i=0; i<list.size(); i++ ){
//                        all += "id:"+list.get(i).get("_id") + " ,n:"+list.get(i).get("name")
//                                + " ,age:"+list.get(i).get("age") +  " ,ph:"+list.get(i).get("mobile")+"\n";
//                    }
//                    txtResult.setText(all);

                    //map안쓰는것
                    while (cursor.moveToNext()){
                        String name = cursor.getString(1);
                        result.add(name);
                    }
                    db.close();
                    lv2.getAdapter().notify();
                }
        );



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