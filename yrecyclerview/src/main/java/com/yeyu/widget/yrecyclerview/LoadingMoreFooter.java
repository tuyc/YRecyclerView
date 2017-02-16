package com.yeyu.widget.yrecyclerview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class LoadingMoreFooter extends LinearLayout implements BaseLoadFooter {

    private LinearLayout mContainer;
    private SimpleViewSwitcher mSimpleViewSwitcher;
    private BaseHeaderFooterView mBaseFooterView;
    private int mState = STATE_NORMAL;

    public int mMeasuredHeight;

	public LoadingMoreFooter(Context context) {
		super(context);
		initView();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public LoadingMoreFooter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

    public void initView(){
        // 初始情况，设置下拉刷新view高度为0
        mContainer = (LinearLayout) LayoutInflater.from(getContext()).inflate(
                R.layout.recyclerview_footer, null);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 0);
        this.setLayoutParams(lp);
        this.setPadding(0, 0, 0, 0);

        addView(mContainer, new LayoutParams(LayoutParams.MATCH_PARENT, 0));
        setGravity(Gravity.BOTTOM);

        mSimpleViewSwitcher = (SimpleViewSwitcher) findViewById(R.id.listview_foot_progressbar);
        mBaseFooterView = new LoadMoreView(getContext());
        mSimpleViewSwitcher.setView(mBaseFooterView);

//        measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mMeasuredHeight = (int) getResources().getDimension(R.dimen.refresh_footer_height);
    }

    public int getState() {
        return mState;
    }

    public void  setState(int state) {
        if (state == mState) return;

        switch (state) {
            case STATE_LOADING:
                mSimpleViewSwitcher.setVisibility(View.VISIBLE);
                smoothScrollTo(mMeasuredHeight);
                mBaseFooterView.startRefreshAnimation();
                break;
            case STATE_COMPLETE:
                if (mBaseFooterView.getAnimation() != null) {
                    mBaseFooterView.getAnimation().cancel();
                }
                loadComplete();
                mBaseFooterView.refreshComplete();
                mBaseFooterView.setVisibility(INVISIBLE);
                break;
        }
        mState = state;
    }

    public void setVisibleHeight(int height) {
        if (height < 0) height = 0;
        LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }

    public int getVisibleHeight() {
        LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
        return lp.height;
    }

    @Override
    public void onMove(float delta) {
        if (getVisibleHeight() > 0 || delta < 0) {
            if (mBaseFooterView != null && mBaseFooterView.getVisibility() != VISIBLE) {
                mBaseFooterView.setVisibility(VISIBLE);
            }
            delta = -delta;
            int height = (int) delta + getVisibleHeight();
            setVisibleHeight(height);
            mBaseFooterView.onMoveProgress(delta);
            if (mState != STATE_LOADING) { // 未处于刷新状态，更新箭头
                if (getVisibleHeight() > mMeasuredHeight) {
                    setState(STATE_RELEASE_TO_LOADING);
                } else {
                    setState(STATE_NORMAL);
                }
            }
        }
    }

    @Override
    public boolean releaseAction() {
        boolean isOnRefresh = false;
        int height = getVisibleHeight();
        if (height == 0) // not visible.
            isOnRefresh = false;

        if (getVisibleHeight() > mMeasuredHeight && mState < STATE_LOADING) {
            setState(STATE_LOADING);
            isOnRefresh = true;
        }
        // refreshing and header isn't shown fully. do nothing.
        if (mState == STATE_LOADING && height <= mMeasuredHeight) {
            //return;
        }
        if (mState != STATE_LOADING) {
            smoothScrollTo(0);
        }

        if (mState == STATE_LOADING) {
            int destHeight = mMeasuredHeight;
            smoothScrollTo(destHeight);
        }

        return isOnRefresh;
    }

    @Override
    public void loadComplete() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                setState(STATE_COMPLETE);
                reset();
            }
        }, 200);
    }

    @Override
    public void setLoadMoreView(BaseHeaderFooterView footerView) {
        if (footerView != null) {
            mBaseFooterView = footerView;
            mSimpleViewSwitcher.setView(mBaseFooterView);
        }
    }

    public void reset() {
        smoothScrollTo(0);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                setState(STATE_NOMORE);
            }
        }, 500);
    }

    private void smoothScrollTo(int destHeight) {
        ValueAnimator animator = ValueAnimator.ofInt(getVisibleHeight(), destHeight);
        animator.setDuration(300).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setVisibleHeight((int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }


}
