package com.allenliu.library;

import android.app.Dialog;
import android.content.Context;


/**
 * Created by Allen Liu on 2017/1/22.
 */

public class BaseProgressBar extends Dialog {
    public BaseProgressBar(Context context) {
        super(context, R.style.BaseDialog);
        // TODO 自动生成的构造函数存根
        setContentView(R.layout.loading_progressbar_layout);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();

    }
}
