package meiyu.core.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import meiyu.core.application.MyApplication;

/**
 * toast工具类
 * Created by teikasei on 2017/1/10.
 */
public class ToastUtils {

    /**
     * 弹出短暂的toast
     *
     * @param msg
     */
    public static void showToast(String msg) {
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT)
                .show();
    }

    /**
     * 弹出短暂的toast
     *
     * @param stringId 要发出的信息的 stringId
     */
    public static void showToast(@StringRes int stringId) {
        Toast.makeText(MyApplication.getContext(), stringId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出长时间的toast
     *
     * @param msg
     */
    public static void showLongToast(String msg) {
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_LONG)
                .show();
    }
}
