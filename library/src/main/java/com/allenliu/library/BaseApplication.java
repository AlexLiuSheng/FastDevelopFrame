package com.allenliu.library;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;

/**
 * Created by allenliu on 2017/6/27.
 */

public class BaseApplication extends Application {
    public static Context globalContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        globalContext = this;

    }
}
