package com.yeyu.yrecyclerview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.yeyu.widget.yrecyclerview.YRecyclerView;
import com.yeyu.yrecyclerview.R;
import com.yeyu.yrecyclerview.apdater.MyItemadapter;

public class MainActivity extends AppCompatActivity {
    private TextView tvDefaultUse;
    private TextView tvCustomUse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tvDefaultUse = (TextView) findViewById(R.id.tvDefaultUse);
        tvCustomUse = (TextView) findViewById(R.id.tvCustomUse);

        tvDefaultUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DefaultUseActivity.class);
                startActivity(intent);
            }
        });

        tvCustomUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomUseActivity.class);
                startActivity(intent);
            }
        });
    }



}
