package com.example.mywebview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editUrl;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUrl = findViewById(R.id.editUrl);
        webView = findViewById(R.id.webView);

        findViewById(R.id.btnClear).setOnClickListener(this);
        findViewById(R.id.btnGoNaver).setOnClickListener(this);
        findViewById(R.id.btnGoGoogle).setOnClickListener(this);
        findViewById(R.id.btnBack).setOnClickListener(this);

        webView.setWebViewClient(new WebViewClient());
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
                break;
            case R.id.btnGoGoogle:
                webView.loadUrl("http://www.google.com/search?q=" + editUrl.getText().toString());
                break;
            case R.id.btnBack:
                webView.goBack();
                break;
        }
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
                Log.d("JB", "item1");
                break;
            case R.id.menu2:
                Log.d("JB", "item2");
                break;
            case R.id.menu3:
                finishAffinity();
                System.runFinalization();
                System.exit(0);
                break;
        }
        return true;
    }
}