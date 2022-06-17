package com.example.exam_shop2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final int[] imgs = {R.drawable.product1,R.drawable.product2,R.drawable.product3};
    private final int[] price = {1500,2000,3000};
    private int val_pay = 0;
    private int selected_product = 1500;
    private int selected_count = 1;
    private ImageView img_product_main;
    private RadioButton[] radioButtons;
    private EditText edit_count;
    private Button[] buttons;
    private Button btn_minus;
    private Button btn_plus;
    private Button btn_pay;
    private TextView txt_price;
    private TextView txt_delivery;
    private TextView txt_pay;
    private CheckBox chk_agree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_product_main = findViewById(R.id.img_product_main);
        img_product_main.setImageResource(imgs[0]);
        radioButtons = new RadioButton[]{findViewById(R.id.radio1),findViewById(R.id.radio2),findViewById(R.id.radio3)};
        edit_count = findViewById(R.id.edit_count);
        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);
        btn_pay = findViewById(R.id.btn_pay);
        buttons = new Button[]{btn_minus,btn_plus,btn_pay};
        chk_agree = findViewById(R.id.chk_agree);
        txt_price = findViewById(R.id.txt_price);
        txt_delivery = findViewById(R.id.txt_delivery);
        txt_pay = findViewById(R.id.txt_pay);


        for(int i = 0; i < radioButtons.length; i++){
            radioButtons[i].setOnClickListener(this);
        }
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setOnClickListener(this);
        }

        sumTotal();
    }

    @Override
    public void onClick(View v) {
        String str_count = edit_count.getText().toString();
        int count = Integer.parseInt(str_count);
        switch (v.getId()){
            case R.id.radio1:
                img_product_main.setImageResource(imgs[0]);
                selected_product = price[0];
                sumTotal();
                break;
            case R.id.radio2:
                img_product_main.setImageResource(imgs[1]);
                selected_product = price[1];
                sumTotal();
                break;
            case R.id.radio3:
                img_product_main.setImageResource(imgs[2]);
                selected_product = price[2];
                sumTotal();
                break;


            case R.id.btn_minus:
                if(count == 1)
                    Toast.makeText(this, "주문할 수 있는 최소 수량은 1개 입니다.",Toast.LENGTH_SHORT).show();
                else{
                    edit_count.setText(Integer.toString(--count));
                    sumTotal();
                }
                break;
            case R.id.btn_plus:
                edit_count.setText(Integer.toString(++count));
                sumTotal();
                break;
            case R.id.btn_pay:
                if(chk_agree.isChecked()){
                    Toast.makeText(this, String.format("%d원을 결제하겠습니다.",val_pay),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this,"결제 동의 버튼을 체크해주세요",Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void sumTotal(){
        int val_delivery;
        int val_price;
        selected_count = Integer.parseInt(edit_count.getText().toString());

        val_price = selected_count * selected_product;

        if(val_price >= 10000)
            val_delivery = 0;
        else
            val_delivery=2500;

        val_pay = val_price+ val_delivery;

        txt_price.setText(val_price+"원");
        txt_delivery.setText(val_delivery+"원");
        txt_pay.setText(val_pay+"원");

    }
}