package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    DatePicker datePicker;
    Button btnEdit;
    EditText editText;
    String fileName;
    String Content;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_1, container, false);
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        btnEdit = view.findViewById(R.id.btnWrite);
        datePicker = view.findViewById(R.id.datePicker);
        editText = view.findViewById(R.id.editDiary);

        int cYear = datePicker.getYear();
        int cMonth = datePicker.getMonth();
        int cDay = datePicker.getDayOfMonth();

        fileName = cYear + "_" + (cMonth+1) + "_" + cDay + ".txt";

        Content = readFile(fileName);
        Log.d("JB", Content);
        editText.setHint(Content);

        datePicker.init(2022, 7, 26, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String str = String.format("%d년 %d월 %d일",year,monthOfYear+1, dayOfMonth);
                Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                fileName = year + "_" + (monthOfYear+1) + "_" + dayOfMonth + ".txt";
            }
        });

        btnEdit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnWrite:
                String text = editText.getText().toString();
                if(writeFile(fileName, text))
                {
                    btnEdit.setText("수정");
                }
                break;
        }
    }

    public boolean writeFile(String fileName, String text) {
        boolean result = false;
        try {
            FileOutputStream fsOut = getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            fsOut.write(text.getBytes());
            result = true;
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("JB", e.getMessage());
        }
        return result;
    }

    String readFile(String fileName)
    {
        String result = "";
        try {
            byte[] txt = new byte[30];
            FileInputStream fsIn = getContext().openFileInput(fileName);
            fsIn.read(txt);
            result = new String(txt);
            Log.d("JB", result);
            if (result == "")
            {
                throw new Exception("파일의 내용이 비어있음");
            }
            btnEdit.setText("수정");
            fsIn.close();
        }
        catch (Exception e)
        {
            result = "일기 없음";
            btnEdit.setText("새로 저장");
        }
        return result;
    }
}