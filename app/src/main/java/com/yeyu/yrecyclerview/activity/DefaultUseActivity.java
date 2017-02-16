package com.yeyu.yrecyclerview.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.yeyu.widget.yrecyclerview.YRecyclerView;
import com.yeyu.yrecyclerview.R;
import com.yeyu.yrecyclerview.apdater.MyItemadapter;

/**
 * Created by tuyc on 17/2/16.
 */

public class DefaultUseActivity extends AppCompatActivity {
    private YRecyclerView mRecyclerView;
    private MyItemadapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_use);
        initView();
    }

    private void initView() {
        mRecyclerView = (YRecyclerView) findViewById(R.id.yrecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLoadingListener(new YRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.refreshComplete();
                    }
                }, 2000);

            }

            @Override
            public void onLoadMore() {
                // load more data here
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.loadMoreComplete();
                    }
                }, 2000);
            }
        });
        mAdapter = new MyItemadapter(getData());
        mRecyclerView.setAdapter(mAdapter);
    }

    private String[] getData() {
        String[] datas = new String[10];
        for (int i = 0; i < 10; i++) {
            datas[i] = "hello girl" + i;
        }
        return datas;
    }
}
