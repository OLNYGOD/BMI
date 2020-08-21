package com.example.bmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText edWeight;                       //將edWeight & edheight提升為class成員
    private EditText edHeight;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate 生成物件
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate:");
        setContentView(R.layout.activity_main);
        findViews();                              //將edWeight & edheight變為方法
    }

    public MainActivity() {
        super();
    }

    @Override
    protected void onStart() {  //onStart 進入畫面前
        super.onStart();
        Log.d(TAG, "onStart:");
    }

    @Override
    protected void onStop() {  //onStop畫面結束
        super.onStop();
        Log.d(TAG, "onStop:");
    }

    @Override
    protected void onDestroy() {  //onDestroy 物件結束
        super.onDestroy();
        Log.d(TAG, "onDestroy:");
    }

    @Override
    protected void onPause() { //onPause 畫面結束前
        super.onPause();
        Log.d(TAG, "onPause:");
    }

    @Override
    protected void onResume() {  //onResume 到畫面後
        super.onResume();
        Log.d(TAG, "onResume:");
    }

    @Override
    protected void onRestart() {  //onRestart  重啟前一個物件

        super.onRestart();
        Log.d(TAG, "onRestart:");
    }

    private void findViews() {                     //將edWeight & edheight變為方法
        edWeight = findViewById(R.id.ed_weight);
        edHeight = findViewById(R.id.ed_height);
        result = findViewById(R.id.result);
        Button help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.help)
                        .setMessage(R.string.bmi_info)
                        .setPositiveButton(R.string.ok, null)
                        .show();
            }
        });
    }
    public void bmi(View view) {
        if (edWeight.getText().toString().equals("") && edHeight.getText().toString().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.problem);
            builder.setMessage(R.string.pleasr_inter_weight_height);
            builder.setPositiveButton(R.string.ok, null);
            builder.show();
        }
        else if (edWeight.getText().toString().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.problem);
            builder.setMessage(R.string.please_enter_weight);
            builder.setPositiveButton(R.string.ok, null);
            builder.show();
        }
        else if (edHeight.getText().toString().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.problem);
            builder.setMessage(R.string.please_enter_height);
            builder.setPositiveButton(R.string.ok, null);
            builder.show();
        }
        else {
            int cm = 100;
            String w = edWeight.getText().toString();
            String h = edHeight.getText().toString();
            float weight = Float.parseFloat(w);
            float height = Float.parseFloat(h);
            float height_cm = height / cm;
            float bmi = weight / (height_cm * height_cm);
            Log.d("MainActivity", "BMI" + bmi);  //show designer
            Toast.makeText(this, getString(R.string.your_bmi_is) + bmi, Toast.LENGTH_LONG).show();  ////show float result
            result.setText(getString(R.string.your_bmi_is) +bmi);  //show result
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
            intent.putExtra("BMI", bmi);
            Log.d("MainActivity", "BMI" + bmi);  //show designer
            AlertDialog show = new AlertDialog.Builder(this)//show result interface for user
                    .setTitle("BMI")
                    .setMessage(getString(R.string.your_bmi_is) + bmi)
                    //                .setPositiveButton("OK", null)  //show exit button ，listener sohw evnt after end
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            edWeight.setText("");
                            edHeight.setText("");
                        }
                    })
                    .show();
        }
    }
}
