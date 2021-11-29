package com.example.mypet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text2;
    CheckBox chkAgree;
    RadioGroup rdoGroup;
    RadioButton rdoDog, rdoCat, rdoRabbit;
//    Button btnOk;
    ImageView imgPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chkAgree = findViewById(R.id.ChkAgree);
        rdoDog = findViewById(R.id.RdoDog);
        rdoCat = findViewById(R.id.RdoCat);
        rdoRabbit = findViewById(R.id.RdoRabbit);
        imgPet = findViewById(R.id.ImgPet);
        text2 = findViewById(R.id.Text2);
        rdoGroup = findViewById(R.id.RdoGroup);

        chkAgree.setOnClickListener(v -> {
            if (chkAgree.isChecked()) {
                text2.setVisibility(View.VISIBLE);
                rdoGroup.setVisibility(View.VISIBLE);
                imgPet.setVisibility(View.VISIBLE);
            } else {
                text2.setVisibility(View.INVISIBLE);
                rdoGroup.setVisibility(View.INVISIBLE);
                imgPet.setVisibility(View.INVISIBLE);
            }
        });
//        btnOk = findViewById(R.id.BtnOK);


        View.OnClickListener handler = v -> {
            switch (v.getId()){
                case R.id.RdoDog: imgPet.setImageResource(R.drawable.dog); break;
                case R.id.RdoCat: imgPet.setImageResource(R.drawable.bulldog); break;
                case R.id.RdoRabbit: imgPet.setImageResource(R.drawable.boxer); break;
            }
        };
        rdoDog.setOnClickListener(handler);
        rdoCat.setOnClickListener(handler);
        rdoRabbit.setOnClickListener(handler);

//        chkAgree.setonClickListener(v -> {
//            if ( chkAgree.isChecked()) {
//                imgPet.setVisibility(View.VISIBLE);
//            } else {
//
//            }
//        });

    }
}