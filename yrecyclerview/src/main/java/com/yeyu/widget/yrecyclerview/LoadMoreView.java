package com.yeyu.widget.yrecyclerview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by yeyu on 17/2/16.
 */

public class LoadMoreView extends BaseHeaderFooterView {
    private ValueAnimator valueAnimator;

    public LoadMoreView(Context context) {
        this(context, null);
    }

    public LoadMoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.loadmore_view, this);
    }


    @Override
    public void onMoveProgress(float delta) {
        setRotation(delta + getRotation());
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
