package com.allenliu.library;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;


/**
 * Created by Allen Liu on 2017/1/22.
 */

public class BaseProgressBar extends Dialog {
    public BaseProgressBar(final Context context) {
        super(context, R.style.BaseDialog);
        // TODO 自动生成的构造函数存根
        setContentView(R.layout.loading_progressbar_layout);
        setCanceledOnTouchOutside(false);
        setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if(context instanceof BaseActivity){
                    ((BaseActivity) context).stopLoading();
                }
            }
        });
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
