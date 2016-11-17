package com.yingshibao.baseproject.db;

import android.database.sqlite.SQLiteDatabase;

import com.yingshibao.baseproject.Application;
import com.yingshibao.baseproject.model.DaoMaster;
import com.yingshibao.baseproject.model.DaoSession;
import com.yingshibao.baseproject.model.UserDao;

/**
 * Created by malk on 16/11/17.
 */

public class DBManager {
    public static final String DB_NAME = "user.db";
    DaoSession mDaoSession;

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    private DBManager() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(Application.getInstance(), DB_NAME);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        mDaoSession = new DaoMaster(db).newSession();
    }

    private static DBManager instance;

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public static UserDao getUserDao() {
        return getInstance().getDaoSession().getUserDao();
    }


}
