package com.example.rev_bmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edWeight;
    private EditText edHeight;
    private Button bHelp;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        bHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new AlertDialog.Builder(this)  // this在此匿名類別是指View.OnClickListener的物件，所以紅字
                new AlertDialog.Builder(MainActivity.this)  // 正確是外部公開類別 MainActivity
                        .setTitle("BMI說明")
                        .setMessage("體重(kg) / 身高的平方(cm)")
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }

    private void findViews() {
        edWeight = findViewById(R.id.ed_weight);
        edHeight = findViewById(R.id.ed_height);
        bHelp = findViewById(R.id.b_help);
        title = getString(R.string.mytitle); // 抽離為字串資源的使用方法
    }

    // 取得元件的值並計算BMI
    public void bmi(View v) {
        String w = edWeight.getText().toString();
        String h = edHeight.getText().toString();
        // 把字串轉成float，才進行運算
        float weight = Float.parseFloat(w);
        float height = (Float.parseFloat(h))/100;
        float bmi = weight/(height*height);
        Log.d("BMI", String.valueOf(bmi));
        //  單純資料-使用Intent轉換至另一個Activity
//        Intent intent = new Intent(this, ResultActivity.class);
//        intent.putExtra("BMI_EXTRA", bmi);
//        startActivity(intent);

        //  複雜的資料-使用Bundle類別
        Bundle bag = new Bundle();
        bag.putFloat("BMI_EXTRA", bmi); // putXX方法，放入不同型態資料
        bag.putString("TEST_EXTRA", "Testing");
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtras(bag);
        startActivity(intent);

        //Toast.makeText(this, String.valueOf(bmi),Toast.LENGTH_LONG).show();

        //  先產生Builder物件
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //  呼叫setMessage方法設定對話框內顯示字串
        builder.setMessage(bmi + "");
        builder.setTitle("BMI運算");
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("DELETE", null);
        builder.setNeutralButton("CANCEL", null);
        //  顯示對話框
        builder.show();

        /*  不需要重複使用物件的簡化寫法
         *  new AlertDialog.Builder(this)
         *          .setMessage(bmi + "")
         *          .setTitle("BMI運算")  // 設定對話框的標題字串
         *          .setPositiveButton("OK", null);
         *          .show();    //  產生對話框物件並顯示在畫面上
         */
    }
}