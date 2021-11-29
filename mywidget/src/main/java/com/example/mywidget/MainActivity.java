package com.example.mywidget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Button btn, btn2, btn3, btn4;
    EditText txtNum1, txtNum2;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_calc);

        btn = findViewById(R.id.btnPlus);
        btn2 = findViewById(R.id.btnMinus);
        btn3 = findViewById(R.id.btnMulti);
        btn4 = findViewById(R.id.btnDevide);
        txtNum1 = findViewById(R.id.txtNum1);
        txtNum2 = findViewById(R.id.txtNum2);
        tv = findViewById(R.id.txtResult);

        btn.setOnClickListener(v -> {
            //계산 editText 입력값들을 더해서 textView에 출력
//            tv.setText(String.valueOf(Integer.parseInt(txtNum1.getText().toString()) + Integer.parseInt(txtNum2.getText().toString())));
            String n1 = txtNum1.getText().toString();
            String n2 = txtNum2.getText().toString();
            int result = Integer.parseInt(n1) + Integer.parseInt(n2);
            tv.setText(String.valueOf(result));
//            Toast.makeText(getApplicationContext(), "클릭됨", Toast.LENGTH_LONG).show();
        });
        View.OnClickListener handler = v ->{
            int n1 = Integer.parseInt(txtNum1.getText().toString());
            int n2 = Integer.parseInt(txtNum2.getText().toString());
            int result = 0;
            switch (v.getId()){
                case R.id.btnPlus: result = n1 + n2; break;
                case R.id.btnMinus: result = n1 - n2; break;
                case R.id.btnMulti: result = n1 * n2; break;
                case R.id.btnDevide: result = n1 / n2; break;

            }
            tv.setText(String.valueOf(result));
        };
        btn.setOnClickListener(handler);
        btn2.setOnClickListener(handler);
        btn3.setOnClickListener(handler);
        btn4.setOnClickListener(handler);

    }
//        setOnClickListener(new ClickHanler());  //버튼클릭했을때 오버라이딩한 메소드 onClick이 실행이 된다.
//
//        class ClickHanler implements View.OnClickListener {
//            @Override
//            public void onClick(View view) {
//
//        Toast.makeText(null,"클릭!!!",Toast.LENGTH_LONG);
//                Toast.makeText(getApplicationContext(), "클릭됨", Toast.LENGTH_LONG).show();
//                System.out.println("클릭됨");
//            }
//        }
//    }
}
