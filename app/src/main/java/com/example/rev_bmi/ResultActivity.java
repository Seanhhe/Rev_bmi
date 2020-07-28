package com.example.rev_bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //  單純資料-取得前一Activity已放入Intent的資料
//        Intent intent = getIntent();    // 先取得intent
//        float bmi = intent.getFloatExtra("BMI_EXTRA", 0);   // 參數一:資料標籤、參數二:取不到資料時的預設值
//        TextView result = findViewById(R.id.result);
//        result.setText("您的 BMI 值為：" + bmi);

        //  複雜資料
        Intent intent = getIntent();
        //float bmi = intent.getExtras().getFloat("BMI_EXTRA", 0);
        Bundle bag = intent.getExtras();
        float bmi = bag.getFloat("BMI_EXTRA", 0);
        String test = bag.getString("TEST_EXTRA", null);
        TextView result = findViewById(R.id.result);
        result.setText("您的 BMI 值為：" + bmi);


        //  抽取字串成為資源檔(.xml)

    }
}