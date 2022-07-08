package com.example.myswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layout_1;
    ImageView img_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout_1 = findViewById(R.id.layout_1);
        img_view = findViewById(R.id.img_view);

        findViewById(R.id.switch1).setOnClickListener(this);
        findViewById(R.id.radio_button1).setOnClickListener(this);
        findViewById(R.id.radio_button2).setOnClickListener(this);
        findViewById(R.id.radio_button3).setOnClickListener(this);
        findViewById(R.id.btn_begin).setOnClickListener(this);
        findViewById(R.id.btn_exit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch1:
                if (((Switch) v).isChecked()) {
                    layout_1.setVisibility(View.VISIBLE);
                    Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
                } else {
                    layout_1.setVisibility(View.INVISIBLE);
                    Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.radio_button1:
                radiobtn1Select();
                break;
            case R.id.radio_button2:
                radiobtn2Select();
                break;
            case R.id.radio_button3:
                radiobtn3Select();
                break;
            case R.id.btn_begin:
                ((RadioButton) findViewById(R.id.radio_button1)).setChecked(true);
                radiobtn1Select();
                ((Switch) findViewById(R.id.switch1)).setChecked(false);
                layout_1.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_exit:
                this.finish();
                break;
        }


    }

    void radiobtn1Select() {
        img_view.setImageResource(R.drawable.an8);
    }

    void radiobtn2Select() {
        img_view.setImageResource(R.drawable.an9);
    }

    void radiobtn3Select() {
        img_view.setImageResource(R.drawable.an10);
    }
}