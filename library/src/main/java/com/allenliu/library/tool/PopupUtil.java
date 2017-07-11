package com.allenliu.library.tool;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;


/**
 * Created by Allen Liu on 2017/5/24.
 */

public class PopupUtil {
    /**
     *
     * @param context
     * @param res
     *            资源
     * @param popup_anim_style
     *            是否显示动画
     * @param isChangeBackground
     *            是否改变背景
     * @return
     */
    public static PopupWindow getPopPuPopupWindow(final Context context, int res, int  popup_anim_style,
                                                  boolean isChangeBackground) {

        View contentView = LayoutInflater.from(context).inflate(res, null);

        PopupWindow pop = new PopupWindow(contentView, -1, -2, true);
        pop.setTouchable(true);
        pop.setOutsideTouchable(true);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        // 设置背景颜色变暗
        if (isChangeBackground) {
            final WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
            lp.alpha = 0.4f;
            ((Activity) context).getWindow().setAttributes(lp);
            pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {
                    // TODO 自动生成的方法存根
                    lp.alpha = 1f;
                    ((Activity) context).getWindow().setAttributes(lp);

                }
            });
        }
        if (popup_anim_style>0)
            pop.setAnimationStyle(popup_anim_style);
        return pop;
    }
}
