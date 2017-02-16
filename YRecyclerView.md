# YRecyclerView

方便自定义Header和Footer，由XRecyclerView改写而来
XRecyclerView github地址：https://github.com/tuyc/XRecyclerView#xrecyclerview



## 使用

```
LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
mRecyclerView.setLayoutManager(layoutManager);
mRecyclerView.setAdapter(mAdapter);
```

## 刷新和加载更多

```
 mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
    @Override
    public void onRefresh() {
       //refresh data here
    }

    @Override
    public void onLoadMore() {
       // load more data here
    }
});
```

加载完后

```
mRecyclerView.loadMoreComplete();
```

刷新完成后

```
 mRecyclerView.refreshComplete();
```

设置刷新是否可用

```
mRecyclerView.setPullRefreshEnabled(false);
```

```
mRecyclerView.setPullRefreshEnabled(true);
```

## 自定义Header和Footer

```java
mRecyclerView.setFootView(new MyLoadMoreView(getBaseContext()));
mRecyclerView.setHeaderView(new MyRefreshView(getBaseContext()));
```

设置Header和Footer的View需要继承BaseHeaderFooterView来实现。可以参考`MyLoadMoreView`和`MyRefreshView`。



### UML图

![](https://github.com/tuyc/tuyc.github.io/blob/master/images/YRecyclerView.png?raw=true)