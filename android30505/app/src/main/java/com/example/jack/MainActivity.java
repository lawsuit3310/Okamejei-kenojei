package com.example.jack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final int case1 = R.id.radio1;
    final int case2 = R.id.radio2;
    final int case3 = R.id.radio3;
    final int case4 = R.id.btn_minus;
    final int case5 = R.id.btn_plus;
    final int case6 = R.id.btn_pay;

    ImageView img_product_main;
    EditText edit_count;
    TextView txt_price, txt_delivery, txt_pay;
    CheckBox chk_agree;

    int val_price = 0;
    int val_delivery = 2500;
    int val_pay = 0;
    int selected_price = 1500;
    int selected_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_count = findViewById(R.id.edit_count);
        img_product_main = findViewById(R.id.img_product_main);
        txt_delivery = findViewById(R.id.txt_delivery);
        txt_price = findViewById(R.id.txt_price);
        txt_pay = findViewById(R.id.txt_pay);
        chk_agree = findViewById(R.id.chk_agree);

        findViewById(R.id.radio1).setOnClickListener(this);
        findViewById(R.id.radio2).setOnClickListener(this);
        findViewById(R.id.radio3).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_pay).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
    }

    private void sumTotal(){
        selected_count= Integer.parseInt(edit_count.getText().toString());

        val_price = selected_price * selected_count;

        val_delivery = val_price >= 10000 ? 0 : 2500;

        val_pay = val_delivery + val_price;

        txt_price.setText(getString(R.string.price_text, val_price));
        txt_delivery.setText(getString(R.string.price_text, val_delivery));
        txt_pay.setText(getString(R.string.price_text, val_pay));
    }

    @Override
    public void onClick(View v) {
        String str_count;
        int count;
        switch(v.getId())
        {
            case case1:
                img_product_main.setImageResource(R.drawable.product1);
                selected_price = 1500;
                sumTotal();
                break;
            case case2:
                img_product_main.setImageResource(R.drawable.product2);
                selected_price = 2000;
                sumTotal();
                break;
            case case3:
                img_product_main.setImageResource(R.drawable.product3);
                selected_price = 3000;
                sumTotal();
                break;

            case case4:
                str_count = edit_count.getText().toString().trim();
                count = Integer.parseInt(str_count);
                if(count == 1)
                {
                    Toast.makeText(getApplicationContext(), "주문할 수 있는 최소수량은 1개입니다.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    edit_count.setText(String.format(Locale.KOREA,"%d",--count));
                    sumTotal();
                }
                break;
            case case5:
                str_count = edit_count.getText().toString().trim();
                count = Integer.parseInt(str_count);
                if(count == 5)
                {
                    Toast.makeText(getApplicationContext(), "주문할 수 있는 최대수량은 5개입니다.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    edit_count.setText(String.format(Locale.KOREA,"%d",++count));
                    sumTotal();
                }
                sumTotal();
                break;

            case case6:
                if(!chk_agree.isChecked())
                    Toast.makeText(getApplicationContext(), "결제 동의 버튼을 체크해주세요", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(getApplicationContext(), String.format(Locale.KOREA, "%d 원을 결제하겠습니다.", val_pay), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    startActivity(intent);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}