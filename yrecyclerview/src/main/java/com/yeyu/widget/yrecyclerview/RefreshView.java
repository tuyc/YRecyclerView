package com.yeyu.widget.yrecyclerview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by yeyu on 17/2/16.
 */

public class RefreshView extends BaseHeaderFooterView {
    private ImageView mImgSnow;
    private ValueAnimator valueAnimator;

    public RefreshView(Context context) {
        this(context, null);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.refresh_view, this);
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
