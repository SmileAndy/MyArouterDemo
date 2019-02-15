package meiyu.core.mvp;

import android.content.Intent;

import com.hannesdorfmann.mosby3.mvp.MvpView;


/**
 * Created by sakura on 2017/2/3.
 */

public interface BaseView extends MvpView {

    /**
     * 显示进度框
     * @param ProgressType 进度框的类型（现在只有一种）
     */
    public void showProgress(int ProgressType);

    /**
     * 隐藏进度框
     */
    public void hideProgress();

    /**
     * 显示 shortToast
     * @param message 显示的内容
     */
    public void showShortToast(String message);

    public void startActivity(Class<?> tClass);

    public void startActivity(Intent intent);

    public void finish();
    public void showProgress(int ProgressType, String content, Boolean canceledOnTouchOutside);
}
