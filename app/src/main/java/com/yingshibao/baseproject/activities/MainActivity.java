package com.yingshibao.baseproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yingshibao.baseproject.R;
import com.yingshibao.baseproject.db.DBManager;
import com.yingshibao.baseproject.model.User;

/**
 * Created by malk on 16/11/15.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = new User(null, "zhangsan", 20);
        DBManager.getUserDao().insert(user);

    }
}
