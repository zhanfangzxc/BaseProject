package gift.makemoney.fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import gift.makemoney.adapters.AdapterItem;
import gift.makemoney.adapters.HeaderAndFooterRecyclerViewAdapter;
import gift.makemoney.adapters.UnsplashAdapterItem;
import gift.makemoney.fragment.base.BasePageListLoadDataFragment;
import gift.makemoney.model.Unsplash;
import gift.makemoney.network.RestApi;
import rx.Observable;

/**
 * Created by malk on 2017/3/6.
 */

public class TestFragment extends BasePageListLoadDataFragment<Unsplash> {


    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getActivity(), 2);
    }

    @Override
    public HeaderAndFooterRecyclerViewAdapter<Unsplash> createAdapter() {
        return new HeaderAndFooterRecyclerViewAdapter<Unsplash>(getActivity(), mDataList,mLayoutManager) {
            @Override
            public AdapterItem<Unsplash> createAdapterItem() {
                return new UnsplashAdapterItem(getActivity());
            }
        };
    }


    @Override
    public Observable<List<Unsplash>> getApi() {
        return RestApi.getApiService().getUnsplash(mPageCount,PAGE_SIZE,"popular",RestApi.CLIENT_ID);
    }
}
