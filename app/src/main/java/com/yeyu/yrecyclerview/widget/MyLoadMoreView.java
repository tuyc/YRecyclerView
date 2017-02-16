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

public class MyLoadMoreView extends BaseHeaderFooterView {
    private ImageView mImgSnow;
    private ValueAnimator valueAnimator;

    public MyLoadMoreView(Context context) {
        this(context, null);
    }

    public MyLoadMoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.app_loadmore_view, this);

        mImgSnow = (ImageView) findViewById(R.id.imgSnow);
    }

    @Override
    public void onMoveProgress(float delta) {
        mImgSnow.setRotation(delta + mImgSnow.getRotation());
    }

    @Override
    public void refreshComplete() {
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    @Override
    public void startRefreshAnimation() {
        createAnimation();
    }

    private void createAnimation() {
        final float step = 5f;
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
}
