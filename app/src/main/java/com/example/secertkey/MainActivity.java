package com.example.secertkey;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText machinEditText;

    private EditText yearEidtText;
    private EditText monthEditText;
    private EditText dayEditText;
    private EditText hourEditText;

    private EditText secretNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.submit);
        button.setOnClickListener(this);

        //关联相应的控件
        machinEditText = (EditText)findViewById(R.id.machValue);

        yearEidtText   = (EditText) findViewById(R.id.year);
        monthEditText  = (EditText) findViewById(R.id.month);
        dayEditText    = (EditText) findViewById(R.id.day);
        hourEditText   = (EditText) findViewById(R.id.hour);

        secretNumberEditText =  (EditText) findViewById(R.id.secertValue);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
            {
                Log.d("onClick","onClick function called");
                //call function
                generatePassword();
                break;
            }
            default:
            {
                break;
            }
        }
    }


    public  void generatePassword(){
          String machinString = machinEditText.getText().toString();
          int machinID = Integer.parseInt(machinString);
          Log.d("generatePassword",machinString);
          Log.d("generatePassword","machinID="+ machinID);

          String yearString  =  yearEidtText.getText().toString();
          String monthString =  monthEditText.getText().toString();
          String dayString   =  dayEditText.getText().toString();
          String hourString  =  hourEditText.getText().toString();

          int  yearInt      = Integer.parseInt(yearString);
          int  monthInt     = Integer.parseInt(monthString);
          int  dayInt       = Integer.parseInt(dayString);
          int  hourInt      = Integer.parseInt(hourString);

          Log.d("generatePassword","yearInt="+yearInt);
          Log.d("generatePassword","monthInt="+monthInt);
          Log.d("generatePassword","dayInt="+dayInt);
          Log.d("generatePassword","hourInt="+hourInt);
          //  param[0] = ((byte)(width >>8));
          int valueFirst  = (machinID << 4) | (machinID >> 10);
          int valueSecond = ((machinID + yearInt + monthInt + dayInt + hourInt) << 10) | ( (machinID + yearInt + monthInt + dayInt + hourInt) >> 4);

          int secertValue = (valueFirst ^ valueSecond)&0x3fff;

          Log.d("generatePassword","secertValue="+secertValue);
          // String s = String.valueOf(i);
          String  secertString = String.valueOf(secertValue);
          Log.d("generatePassword","secertString="+secertString);
          secretNumberEditText.setText(secertString);

    }

}
