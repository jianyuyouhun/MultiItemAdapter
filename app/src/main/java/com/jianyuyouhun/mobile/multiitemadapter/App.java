package com.jianyuyouhun.mobile.multiitemadapter;

import android.app.Application;

import com.jianyuyouhun.permission.library.EZPermission;

import org.xutils.x;

/**
 *
 * Created by wangyu on 2017/12/8.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        EZPermission.Companion.init(this);
    }
}
