package gift.makemoney.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

import butterknife.BindView;
import gift.makemoney.R;
import gift.makemoney.fragment.TestFragment;

/**
 * Created by malk on 2017/3/3.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.drawerLayout) DrawerLayout mDrawerLayout;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupToolBar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, new TestFragment()).commit();
    }
}
