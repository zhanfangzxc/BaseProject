package com.yingshibao.baseproject.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yingshibao.baseproject.R;
import com.yingshibao.baseproject.adapters.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 加载列表数据的Fragment
 *
 * @param <T>
 */
public abstract class BaseListLoadDataFragment<T> extends BaseLoadDataFragment<List<T>> implements  BaseRecyclerViewAdapter.OnItemClickListener {
    @BindView(R.id.recyclerview) public RecyclerView mRecyclerView;
    protected List<T> mDataList;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected BaseRecyclerViewAdapter<T> mAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDataList = new ArrayList<>();
        setupRecyclerView();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_base_list;
    }


    public void setupRecyclerView() {
        mLayoutManager = getLayoutManager();
        mAdapter = createAdatper();
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public abstract RecyclerView.LayoutManager getLayoutManager();

    public abstract  BaseRecyclerViewAdapter<T> createAdatper();

    public abstract boolean isClear();//是否清除所有

    @Override
    public void handleResult(List<T> list) {
        super.handleResult(list);
        if (isClear()) mDataList.clear();
        mDataList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    @Override
    public void onItemClick(View v, int position) {

    }
}
