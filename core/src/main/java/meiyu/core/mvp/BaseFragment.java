package meiyu.core.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by admin on 2017/2/9.
 */

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>>
        extends MvpFragment<V, P> implements BaseView {

    protected BaseActivity mActivity;

    View view;
    Unbinder unbinder  ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();


    }

    @Override
    public void showProgress(int ProgressType) {

    }

    @Override
    public void showProgress(int ProgressType, String content, Boolean canceledOnTouchOutside) {

    }

    @Override
    public void hideProgress() {

    }

    public void toast(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getViewLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    /**
     * 初始化
     */
    protected abstract void init();
    /**
     * 布局
     *
     * @return
     */
    public abstract int getViewLayoutId();


    @Override
    public void startActivity(Class<?> tClass) {
        startActivity(new Intent(mActivity, tClass));
    }

    @Override
    public void finish() {
        mActivity.finish();
    }

    @Override
    public void onResume() {
        super.onResume();
     }

    @Override
    public void onPause() {
        super.onPause();
     }

    public void back(View view) {
        mActivity.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
