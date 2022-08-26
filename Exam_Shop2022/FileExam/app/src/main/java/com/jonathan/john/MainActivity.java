package com.jonathan.john;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.write).setOnClickListener(this);
        findViewById(R.id.read).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.write:
                if(writeFile("흙흙 모래모래 자갈자갈 "))
                {
                    Log.d("JB","성공");
                }
                else
                {
                    Log.d("JB","실패");
                }
                break;
            case R.id.read:
                Log.d("JB",readFile());
                Toast.makeText(this, readFile(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
    public boolean writeFile(String content)
    {
        boolean result = false;
        try {
           FileOutputStream fsOut = openFileOutput("file.txt", Context.MODE_PRIVATE);
           fsOut.write(content.getBytes());
           fsOut.close();
           result = true;
        }
        catch (Exception e)
        {
            Log.d("JB",e.getMessage());
        }
        return result;
    }
    public String readFile()
    {
        String result = "";
        try {
            FileInputStream fsIn = openFileInput("file.txt");
            byte[] txt = new byte[30] ;
            fsIn.read(txt);
            result = new String(txt);
            fsIn.close();
        }
        catch (Exception e)
        {
            Log.d("JB",e.getMessage());
            result = e.getMessage();
        }
        return result;
    }
}