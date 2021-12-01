package com.example.myhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Gson gson = new Gson();
    String sum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        Button btnIns = findViewById(R.id.button2);
        TextView tv = findViewById(R.id.textView);

        RequestQueue queue = Volley.newRequestQueue(this);

        btnIns.setOnClickListener( v->{
            String url = "http://10.0.2.2/insertUser";
            StringRequest request = new StringRequest(Request.Method.POST, url, s->{
                tv.setText(s);
            }, e->{}){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("id","pol");
                    map.put("name","포로리");
                    map.put("password","222");
                    map.put("role","Admin");
                    return map;
                }
            };
            queue.add(request);
        });


        button.setOnClickListener(v-> {
//            String url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20211130";
            String url = "http://10.0.2.2/userList";
            StringRequest request =  new StringRequest(url, s->{
                List list = gson.fromJson(s,List.class);
                for ( int i = 0; i < list.size(); i++){
                    sum += list.get(i).toString() + "\n";
                }

                tv.setText(sum);
            }, e->{
                Log.d("request",e.toString());
            });
            queue.add(request);

        });
    }
}