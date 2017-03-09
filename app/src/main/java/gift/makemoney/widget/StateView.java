package gift.makemoney.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gift.makemoney.R;

import static gift.makemoney.widget.StateView.State.CONTENT;
import static gift.makemoney.widget.StateView.State.EMPTY;
import static gift.makemoney.widget.StateView.State.ERROR;
import static gift.makemoney.widget.StateView.State.LOADING;


/**
 * @author malinkang
 */
public class StateView extends FrameLayout {


    public enum State {
        CONTENT, LOADING, EMPTY, ERROR
    }

    LayoutInflater inflater;
    LayoutParams layoutParams;
    List<View> mContentViews = new ArrayList<>();

    View mLoadingView;
    View mEmptyView;
    View mErrorView;

    TextView mEmptyHintTextView;
    TextView mErrorHintTextView;
    TextView mRetryButton;


    public StateView(Context context) {
        super(context);
    }

    public StateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StateView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.StateView);
        typedArray.recycle();
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        if (child.getTag() == null || (!child.getTag().equals("layout_empty") &&
                !child.getTag().equals("layout_error") && !child.getTag().equals("layout_loading"))) {
            mContentViews.add(child);
        }
    }

    public void showContent() {
        switchState(CONTENT, null, null);
    }


    public void showLoading() {
        switchState(LOADING, null, null);
    }


    public void showEmpty(String hint) {
        switchState(EMPTY, hint, null);
    }


    public void showError(String hint, View.OnClickListener onClickListener) {
        switchState(ERROR, hint, onClickListener);
    }


    private void switchState(State state, String hint, View.OnClickListener onClickListener) {

        switch (state) {
            case CONTENT:
                //Hide all state views to display content
                hideLoadingView();
                hideEmptyView();
                hideErrorView();

                setContentVisibility(true);
                break;
            case LOADING:
                hideEmptyView();
                hideErrorView();
                setLoadingView();
                setContentVisibility(false);
                break;
            case EMPTY:
                hideLoadingView();
                hideErrorView();
                setEmptyView();
                mEmptyHintTextView.setText(hint);
                setContentVisibility(false);
                break;
            case ERROR:
                hideLoadingView();
                hideEmptyView();
                setErrorView();
                mErrorHintTextView.setText(hint);
                mRetryButton.setOnClickListener(onClickListener);
                setContentVisibility(false);
                break;
        }
    }

    private void setLoadingView() {
        if (mLoadingView == null) {
            mLoadingView = inflater.inflate(R.layout.layout_loading, null);
            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            addView(mLoadingView, layoutParams);
        } else {
            mLoadingView.setVisibility(VISIBLE);
        }
    }

    private void setEmptyView() {
        if (mEmptyView == null) {
            mEmptyView = inflater.inflate(R.layout.layout_empty, null);
            mEmptyHintTextView = (TextView) mEmptyView.findViewById(R.id.tv_empty_hint);
            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            addView(mEmptyView, layoutParams);
        } else {
            mEmptyView.setVisibility(VISIBLE);
        }
    }

    private void setErrorView() {
        if (mErrorView == null) {
            mErrorView = inflater.inflate(R.layout.layout_error, null);
            mErrorHintTextView = (TextView) mErrorView.findViewById(R.id.tv_error_hint);
            mRetryButton = (Button) mErrorView.findViewById(R.id.btn_retry);
            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            addView(mErrorView, layoutParams);
        } else {
            mErrorView.setVisibility(VISIBLE);
        }
    }

    private void setContentVisibility(boolean visible) {
        for (View v : mContentViews) {
            v.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    private void hideLoadingView() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(GONE);
        }
    }

    /**
     * 隐藏View
     */
    private void hideEmptyView() {
        if (mEmptyView != null) {
            mEmptyView.setVisibility(GONE);
        }
    }

    private void hideErrorView() {
        if (mErrorView != null) {
            mErrorView.setVisibility(GONE);
        }
    }
}