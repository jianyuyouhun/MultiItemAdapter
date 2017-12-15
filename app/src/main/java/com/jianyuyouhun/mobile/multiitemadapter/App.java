package com.jianyuyouhun.mobile.multiitemadapter;

import android.app.Application;

import com.jianyuyouhun.permission.library.EZPermission;

/**
 *
 * Created by wangyu on 2017/12/8.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EZPermission.Companion.init(this);
    }
}
