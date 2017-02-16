package com.yeyu.widget.yrecyclerview;


/**
 * Created by yeyu on 17/2/16.
 */

interface BaseLoadFooter {

    int STATE_NORMAL = 0;
    int STATE_RELEASE_TO_LOADING = 1;
    int STATE_LOADING = 2;
    int STATE_COMPLETE = 3;
    int STATE_NOMORE = 4;

    void onMove(float delta);

    boolean releaseAction();

    void loadComplete();

    void setLoadMoreView(BaseHeaderFooterView view);
}
