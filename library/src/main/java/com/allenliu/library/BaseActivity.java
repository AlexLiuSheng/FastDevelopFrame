package com.allenliu.library;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;


import com.allenliu.library.eventbus.CommonEvent;
import com.blankj.utilcode.util.SnackbarUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by cqing on 30/3/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Activity mActivity;
    private BaseProgressBar progressBar;


    public abstract void init();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mActivity = this;
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        // init();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void startLoading() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void stopLoading() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    /**
     * show a snackbar using given text
     *
     * @param content
     */
    public void showSnack(String content) {
        if (StringUtils.isEmpty(content))
            return;
        SnackbarUtils.with(findViewById(android.R.id.content)).setBgColor(ContextCompat.getColor(mActivity, R.color.colorPrimary)).setMessage(content).setMessageColor(ContextCompat.getColor(mActivity, R.color.white)).show();
       //  SnackbarUtils.showShort(findViewById(android.R.id.content), content, ContextCompat.getColor(mActivity, R.color.white), ContextCompat.getColor(mActivity, R.color.colorPrimary));
    }

    public void toast(String content) {
        if (StringUtils.isEmpty(content))
            return;
        ToastUtils.showShort(content);
    }

    public void toast(int resourceId) {
        toast(getString(resourceId));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onActivityReceiveEvent(CommonEvent commonEvent) {
        dimissProgressBar();
        if (!commonEvent.isSuccessful()) {
            toast(commonEvent.getMessage());
        }
    }

    public void showLoadingProgressBar() {
        if (progressBar == null)
            progressBar = new BaseProgressBar(this);
        if (!progressBar.isShowing())
            progressBar.show();
        startLoading();

    }

    public void dimissProgressBar() {
        if (progressBar != null && progressBar.isShowing())
            progressBar.dismiss();
        stopLoading();
    }


}
