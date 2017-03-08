package gift.makemoney.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import gift.makemoney.R;


/**
 * Created by malk on 16/9/7.
 */

public abstract class BaseActivity extends AppCompatActivity  {

    protected Unbinder unbinder;

    protected ArrayList<BaseActivity> activities;

    @BindView(R.id.toolbar) Toolbar mToolBar;
    protected ActionBar mActionBar;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities = new ArrayList<>();
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        mActionBar = getSupportActionBar();
        setupToolBar();
    }




    public abstract
    @LayoutRes
    int getLayout();

    public abstract void setupToolBar();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
