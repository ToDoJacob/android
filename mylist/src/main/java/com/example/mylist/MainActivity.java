package com.example.mylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Map<String,String>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);
//        String[] data = getResources().getStringArray(R.array.city);
        String[] data = new String[]{""};
        list = new ArrayList<Map<String,String>>();

        Map<String,String> map = new HashMap<String,String>();
        map.put("name","홍길동"); map.put("addr","대구");
        list.add(map);
        map = new HashMap<String,String>();
        map.put("name","김길동"); map.put("addr","서울");
        list.add(map);
        map = new HashMap<String,String>();
        map.put("name","강감찬"); map.put("addr","부산");
        list.add(map);

//        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
//                list,
//                android.R.layout.simple_list_item_2,
//                new String[]{"name","addr"},
//                new int[]{android.R.id.text1,android.R.id.text2});
//        lv.setAdapter(adapter);

        MyAdapter adapter = new MyAdapter(list);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(getApplicationContext(), list.get(i).get("name"), Toast.LENGTH_LONG).show();
        });

        //어댑터
//        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
//                android.R.layout.simple_list_item_1,data);
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener((adapterView, view, i, l) -> {
//            Toast.makeText(getApplicationContext(), data[i], Toast.LENGTH_LONG).show();
//        });
    }


}