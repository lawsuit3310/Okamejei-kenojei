package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Fragment1 f1;
    Fragment2 f2;
    Fragment3 f3;
    FragmentManager fm;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btn_frag_1);
        Button btn2 = findViewById(R.id.btn_frag_2);
        Button btn3 = findViewById(R.id.btn_frag_3);

        //프래그먼트 객체 생성(Model)
        f1 = new Fragment1();
        f2 = new Fragment2();
        f3 = new Fragment3();

        //프래그먼트 매니저 (Controller)
        fm = getSupportFragmentManager();
        //프래그먼트 트랜잭션으로 프래그먼트 추가, 삭제, 교체가 가능
        ft = fm.beginTransaction();

        //리플레이스로 교체 후 커밋 해줘야됨.
        ft.add(R.id.fragment,f1);
        ft.commit();

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ft = fm.beginTransaction();
        switch (v.getId())
        {
            case R.id.btn_frag_1:
                ft.replace(R.id.fragment,f1);
                ft.commit();
                break;
            case R.id.btn_frag_2:
                ft.replace(R.id.fragment,f2);
                ft.commit();
                break;
            case R.id.btn_frag_3:
                ft.replace(R.id.fragment,f3);
                ft.commit();
                break;
        }
    }
}