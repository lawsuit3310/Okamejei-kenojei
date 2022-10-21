package com.example.mywebview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.*;
import android.widget.*;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editUrl;
    Spinner history;
    WebView webView;
    ArrayAdapter<String> historyAdaper;
    ArrayList<String> items;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUrl = findViewById(R.id.editUrl);
        webView = findViewById(R.id.webView);
        history = findViewById(R.id.History);

        items = new ArrayList<String>();

        historyAdaper = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);

        history.setAdapter(historyAdaper);

        history.setVisibility(View.GONE);

        findViewById(R.id.btnClear).setOnClickListener(this);
        findViewById(R.id.btnGoNaver).setOnClickListener(this);
        findViewById(R.id.btnGoGoogle).setOnClickListener(this);
        findViewById(R.id.btnBack).setOnClickListener(this);

        history.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editUrl.setText(history.getItemAtPosition(position).toString());
                findViewById(R.id.btnGoGoogle).performClick();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        webView.setWebViewClient(new WebViewClient());
        editUrl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    history.setVisibility(View.VISIBLE);
                else
                    history.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editUrl.getWindowToken(),0);

        switch(v.getId())
        {
            case R.id.btnClear:
                editUrl.setText("");
                break;
            case R.id.btnGoNaver:
                webView.loadUrl("http://search.naver.com/search.naver?query=" + editUrl.getText().toString());
                addHistoryData(editUrl.getText().toString().trim());
                break;
            case R.id.btnGoGoogle:
                webView.loadUrl("http://www.google.com/search?q=" + editUrl.getText().toString());
                addHistoryData(editUrl.getText().toString().trim());
                break;
            case R.id.btnBack:
                webView.goBack();
                break;
        }
        historyAdaper.notifyDataSetChanged();
    }

    private void addHistoryData(String data){
        if(!items.contains(data))
            items.add(0,data);
        if(items.size()>5)
            items.remove(5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.menu1:
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("안녕하세요")
                        .setPositiveButton("확인", null)
                        .setMessage("내용을 써봅시다")
                        .setIcon(R.drawable.logo_google)
                        .show();
                break;
            case R.id.menu2:
                View dlgView = View.inflate(this, R.layout.profile, null);
                AlertDialog.Builder dlg2 = new AlertDialog.Builder(this);
                dlg2.setTitle("안녕하세요")
                        .setView(dlgView)
                        .setNegativeButton("취소", null)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText id = dlgView.findViewById(R.id.dlg_id);
                        String nickname = id.getText().toString().trim();
                        setTitle(nickname.length() > 0 ? id.getText().toString() + "의 검색기" : getTitle());
                    }
                })
                        .show();

                break;
            case R.id.menu3:
                finishAffinity();
                System.runFinalization();
                System.exit(0);
                break;

            case R.id.menu4:
                Intent intent = new Intent(MainActivity.this, SpinnerActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}