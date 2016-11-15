package com.yingshibao.baseproject.fragments.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.yingshibao.baseproject.R;
import com.yingshibao.baseproject.model.BaseResponseModel;
import com.yingshibao.baseproject.ui.StateView;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 加载数据的Fragment
 *
 * @param <T>
 */
public abstract class BaseLoadDataFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.state_view) StateView mStateView;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;


    private Handler mHandler;


    private Runnable mDeplayedLoadDataTask = () -> getData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
    }

    protected CompositeSubscription mCompositeSubscription;

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupSwipeRefreshLayout();
        getData();
    }

    public void getData() {
        Subscription rxSubscription = getApi()
                .compose(rxSchedulerHelper())
                .flatMap(new Func1<BaseResponseModel<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseResponseModel<T> tBaseResponseModel) {
                        return Observable.create(subscriber -> {
                            // subscriber.onNext(tBaseResponseModel.getResult());
                            subscriber.onCompleted();
                        });
                    }
                })
                .subscribe(t -> {
                    handleResult(t);
                }, throwable -> {
                    handleError(throwable);
                }, () -> {
                    handleComplete();
                });
        addSubscrebe(rxSubscription);

    }


    public void setupSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.blue, R.color.purple, R.color.green, R.color.purple);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    //
    public void handleError(Throwable throwable) {
//        if (throwable instanceof RetrofitException) {
//            if (((RetrofitException) throwable).getKind() == RetrofitException.Kind.NETWORK && isShowErrorView()) {
//                showNetWorkErrorView();
//            }
//        }
    }

    public boolean isShowRefreshView() {
        return true;
    }

    /**
     * 是否显示ErrorView 当分页的时候只有当pageCount=1的时候才显示
     *
     * @return
     */
    public boolean isShowErrorView() {
        return true;
    }

    /**
     * 是否显示EmptyView 当分页的时候只有当pageCount=1的时候才显示
     *
     * @return
     */
    public boolean isShowEmptyView() {
        return true;
    }

    public abstract boolean isEmpty(T t);//数据是否为空

    public void handleResult(T t) {
        //如果数据是空的并且isShowEmptyView为true,则显示
        if (isEmpty(t) && isShowEmptyView()) {
            mStateView.showEmpty("没有数据");
            return;
        }
        mStateView.showContent();
    }

    public void handleComplete() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    public abstract Observable<BaseResponseModel<T>> getApi();

    public <T> Observable.Transformer<T, T> rxSchedulerHelper() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public void showNetWorkErrorView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unSubscribe();
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mHandler.postDelayed(mDeplayedLoadDataTask, 500);
    }
}
