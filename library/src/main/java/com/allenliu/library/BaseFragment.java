package com.allenliu.library;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.allenliu.library.eventbus.CommonEvent;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SnackbarUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by allenliu
 */

public class BaseFragment extends Fragment {
    public Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        mContext = context;

    }


    @Override
    public void onDetach() {
        super.onDetach();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFragmentReceiveEvent(CommonEvent commonEvent) {
        if (!commonEvent.isSuccessful()) {
            toast(commonEvent.getMessage());
        }
    }

    /**
     * show a snackbar using given text
     *
     * @param content
     */
    public void showSnack(String content) {
        if (StringUtils.isEmpty(content))
            return;
        SnackbarUtils.with(getActivity().findViewById(android.R.id.content)).setBgColor(ContextCompat.getColor(mContext, R.color.colorPrimary)).setMessage(content).setMessageColor(ContextCompat.getColor(mContext, R.color.white)).show();

        //SnackbarUtils.showShort(getActivity().findViewById(android.R.id.content), content, ContextCompat.getColor(getActivity(), R.color.white), ContextCompat.getColor(getActivity(), R.color.colorPrimary));
    }

    public void toast(String content) {
        if (StringUtils.isEmpty(content))
            return;
        ToastUtils.showShort(content);
    }

    public void showLoadingProgressBar() {
        if (getActivity() instanceof BaseActivity)
            ((BaseActivity) getActivity()).showLoadingProgressBar();
    }

    public void dimissProgressBar() {
        if (getActivity() instanceof BaseActivity)
            ((BaseActivity) getActivity()).dimissProgressBar();
    }


    @Override
    public void onResume() {
        super.onResume();

    }
}
