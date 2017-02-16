package com.yeyu.yrecyclerview.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.yeyu.widget.yrecyclerview.BaseHeaderFooterView;
import com.yeyu.yrecyclerview.R;

/**
 * Created by tuyc on 17/2/16.
 */

public class MyRefreshView extends BaseHeaderFooterView {
    private ImageView mImgSnow;
    private ValueAnimator valueAnimator;

    public MyRefreshView(Context context) {
        this(context, null);
    }

    public MyRefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    
    private void init() {
        inflate(getContext(), R.layout.app_refresh_view, this);

        mImgSnow = (ImageView) findViewById(R.id.snow_icon);
    }

    @Override
    public void onMoveProgress(float delta) {
        mImgSnow.setRotation(delta + mImgSnow.getRotation());
    }

    @Override
    public void refreshComplete() {
        if (valueAnimator != null) {
            valueAnimator.end();
        }
    }

    @Override
    public void startRefreshAnimation() {
        final float step = 5f;
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofInt(0, 500);
            valueAnimator.setDuration(500).start();
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    onMoveProgress(step);
                }
            });
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        }
        if (valueAnimator.isRunning()) {
            valueAnimator.end();
        }
        valueAnimator.start();
    }
}
