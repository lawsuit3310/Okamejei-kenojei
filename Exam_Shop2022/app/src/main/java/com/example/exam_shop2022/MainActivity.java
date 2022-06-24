package com.example.exam_shop2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView img_product_main;
    EditText edit_count;
    TextView txt_price, txt_delivery, txt_pay;
    CheckBox chk_agree;

    int val_price;
    int val_pay;
    int val_delivery;
    int selected_product = 1500;
    int selected_count;
    int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_product_main = findViewById(R.id.img_product_main);
        edit_count = findViewById(R.id.edit_count);
        txt_price = findViewById(R.id.txt_price);
        txt_delivery = findViewById(R.id.txt_delivery);
        txt_pay = findViewById(R.id.txt_pay);
        chk_agree = findViewById(R.id.chk_agree);

        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_pay).setOnClickListener(this);
        findViewById(R.id.radio1).setOnClickListener(this);
        findViewById(R.id.radio2).setOnClickListener(this);
        findViewById(R.id.radio3).setOnClickListener(this);

        sumTotal();
    }

    @Override
    public void onClick(View v) {
        count = Integer.parseInt(edit_count.getText().toString());
        switch (v.getId()){
            case R.id.radio1:
                img_product_main.setImageResource(R.drawable.product1);
                selected_product=1500;
                sumTotal();
                break;
            case R.id.radio2:
                img_product_main.setImageResource(R.drawable.product2);
                selected_product=2000;
                sumTotal();
                break;
            case R.id.radio3:
                img_product_main.setImageResource(R.drawable.product3);
                selected_product=3000;
                sumTotal();
                break;

            case R.id.btn_minus:
                count = Integer.parseInt(edit_count.getText().toString());
                if (count <= 1){
                    Toast.makeText(this, "주문할 수 있는 최소 수량은 1개입니다.",Toast.LENGTH_SHORT).show();
                }
                else{
                    edit_count.setText(count-1 + "");
                    sumTotal();
                }
                break;

            case R.id.btn_plus:
                Log.d("JB",""+true);
                edit_count.setText(count+1 + "");
                sumTotal();
                break;

            case R.id.btn_pay:
                if(chk_agree.isChecked()){
                    Toast.makeText(this,String.format("%d원을 결제합니다.",val_pay),Toast.LENGTH_SHORT);
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this,"결제 동의 버튼을 눌러주십세요",Toast.LENGTH_SHORT).show();

                }
                break;
        }

    }
    private void sumTotal(){
        selected_count = Integer.parseInt(edit_count.getText().toString());

        val_price=selected_count*selected_product;

        val_delivery = val_price >= 10000 ? 0 : 2500;

        val_pay=val_price+val_delivery;

        txt_price.setText(String.format("%d원",val_price));
        txt_delivery.setText(String.format("%d원",val_delivery));
        txt_pay.setText(String.format("%d원",val_pay));
    }
}