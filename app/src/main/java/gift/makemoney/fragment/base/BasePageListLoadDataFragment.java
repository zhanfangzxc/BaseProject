package gift.makemoney.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import gift.makemoney.R;

/**
 * 分页加载的Fragment
 */

public abstract class BasePageListLoadDataFragment<T> extends BaseListLoadDataFragment<T> {


    protected int mPageCount = 1;

    protected boolean isLoadAllFinish = true;

    public static final int PAGE_SIZE = 10;

    private View mFooterView;

    @Override
    public boolean isShowLoadingView() {
        return mPageCount == 1&&!mSwipeRefreshLayout.isRefreshing();
    }

    @Override
    public boolean isShowEmptyView() {
        return mDataList.size() == 0;
    }

    @Override
    public boolean isShowErrorView() {
        return mDataList.size() == 0;//
    }

    @Override
    public boolean isClear() {
        return mPageCount == 1;
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPageCount = 1;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void handleResult(List<T> list) {
        isLoadAllFinish = list.size() < PAGE_SIZE;
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
        if (mAdapter.hasFooter()) {
            mAdapter.removeFooter(mFooterView);
        }
    }

    @Override
    public boolean isEmpty(List<T> list) {
        return false;
    }

    @Override
    public void setupRecyclerView() {
        super.setupRecyclerView();
        mFooterView = View.inflate(getActivity(), R.layout.item_loading_footer, null);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int visibleItemCount = recyclerView.getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && totalItemCount - visibleItemCount == firstVisibleItem) {
                    if (!isLoadAllFinish && !isLoading) {
                        if (!mAdapter.hasFooter()) {
                            mAdapter.addFooter(mFooterView);
                        }
                        getData();
                    }
                }
            }
        });
    }
}
