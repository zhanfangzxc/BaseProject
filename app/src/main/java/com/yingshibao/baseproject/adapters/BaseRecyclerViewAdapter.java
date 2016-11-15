package com.yingshibao.baseproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * RecyclerViewAdapter基类
 *
 * @param <T>
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter {
    protected List<T> mList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    protected static final int TYPE_ITEM = 100;


    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public BaseRecyclerViewAdapter(Context context, List<T> list) {
        mContext = context;
        mList = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterItem<T> item = createAdapterItem();
        View view = LayoutInflater.from(mContext).inflate(item.getLayoutResId(viewType), parent, false);
        return new ViewHolder(view, createAdapterItem());

    }

    public abstract AdapterItem<T> createAdapterItem();

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        T t = getItem(position);
        ((ViewHolder) holder).item.bindData(mContext, position, t, getItemViewType(position));

    }

    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    static class ViewHolder<T> extends RecyclerView.ViewHolder {
        protected AdapterItem<T> item;

        protected ViewHolder(View itemView, AdapterItem<T> item) {
            super(itemView);
            this.item = item;
            this.item.bindViews(itemView);
        }
    }
}