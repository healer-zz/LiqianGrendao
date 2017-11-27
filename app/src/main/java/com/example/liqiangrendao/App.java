package com.example.liqiangrendao;

import android.app.Application;

import com.baway.liqianqian.dialog.DaoMaster;
import com.baway.liqianqian.dialog.DaoSession;
import com.baway.liqianqian.dialog.UserDao;

/**
 * Created by 李倩 on 2017/11/23.
 */

public class App extends Application {
    public static UserDao userDao;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "lenvess.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }
}
