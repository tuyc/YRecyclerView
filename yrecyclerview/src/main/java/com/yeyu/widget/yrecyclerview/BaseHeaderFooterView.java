package com.yeyu.widget.yrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * HeaderView和FooterView的基础
 * Created by yeyu on 17/2/16.
 */

public abstract class BaseHeaderFooterView extends RelativeLayout {
    public BaseHeaderFooterView(Context context) {
        super(context);
    }

    public BaseHeaderFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseHeaderFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void onMoveProgress(float delta);

    public abstract void refreshComplete();

    public abstract void startRefreshAnimation();
}
