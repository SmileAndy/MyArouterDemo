package meiyu.core.mvp;

import android.content.Intent;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;


/**
 * Created by sakura on 2017/2/3.
 */

public class BasePresenter<V extends BaseView> extends MvpBasePresenter<V> {

    protected void showProgress(int progressType) {
        if (isViewAttached()) {
            getView().showProgress(progressType);
        }
    }

    protected void hideProgress() {
        if (isViewAttached()) {
            getView().hideProgress();
        }
    }

    protected void showShortToast(String message) {
        if (isViewAttached()) {
            getView().showShortToast(message);
        }
    }

    protected void startActivity(Class<?> tClass) {
        if (isViewAttached()) {
            getView().startActivity(tClass);
        }
    }

    protected void startActivity(Intent intent) {
        if (isViewAttached()) {
            getView().startActivity(intent);
        }
    }

    protected void finish() {
        if (isViewAttached()) {
            getView().finish();
        }
    }

}
