package com.yingshibao.baseproject.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

/**
 *
 * 分页加载的Fragment
 * */

public abstract class BasePageListLoadDataFragment<T> extends BaseListLoadDataFragment<T> {

    private boolean isLoadMore=false;

    protected int mPageCount = 1;

    protected boolean isLoadAllFinish=true;

    public static final int PAGE_SIZE = 10;

    @Override
    public boolean isShowRefreshView() {
        return mPageCount==1;
    }

    @Override
    public boolean isShowEmptyView() {
        return mDataList.size()==0;
    }

    @Override
    public boolean isShowErrorView() {
        return mDataList.size()==0;//
    }

    @Override
    public boolean isClear() {
        return mPageCount==1;
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPageCount=1;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void handleResult(List<T> list) {
        isLoadAllFinish = list.size()<PAGE_SIZE;
        super.handleResult(list);
        mPageCount++;//页数增加
    }

    @Override
    public void handleError(Throwable throwable) {
        super.handleError(throwable);

    }

    @Override
    public void handleComplete() {
        super.handleComplete();
        isLoadMore = true;
    }
}
