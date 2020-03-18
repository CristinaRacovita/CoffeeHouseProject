package com.example.crisscafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class YourOrderActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_order);
        String messageOrder = getIntent().getStringExtra(OrderActivity.mOrder);
        String messageInfo = getIntent().getStringExtra(OrderActivity.mInfo);
        TextView txt = findViewById(R.id.textView10);
        TextView txtt = findViewById(R.id.textView6);
        txt.setText(messageOrder);
        txtt.setText(messageInfo);

    }


}
