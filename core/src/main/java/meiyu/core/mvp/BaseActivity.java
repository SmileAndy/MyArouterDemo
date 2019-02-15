package meiyu.core.mvp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.ButterKnife;
import meiyu.core.R;


/**
 * 基本的activity
 * Created by teikasei on 2017/1/6.
 */

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>>
        extends MvpActivity<V, P> implements BaseView {

     protected BaseActivity mActivity;
     private InputMethodManager imm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResId());
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


            ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

    mActivity = this;
        ActivityCollector.addActivity(mActivity);

    setupToolbar();
    init();
}

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityCollector.removeActivity(mActivity);

    }

    /**
     * 获取Activity的布局id
     *
     * @return
     */
    protected abstract int getContentViewResId();

    /**
     * 初始化toolbar
     */
    protected void setupToolbar() {
    }

    /**
     * 初始化
     */
    protected abstract void init();

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



    public void startActivity(Class<?> tClass) {
        startActivity(new Intent(mActivity, tClass));
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
      }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View focus=getCurrentFocus();
                if(focus!=null){
                    Rect hit=new Rect();
                    focus.getGlobalVisibleRect(hit);
                    if(!hit.contains((int)ev.getX(),(int)ev.getY()) && imm.isActive()){
                        imm.hideSoftInputFromWindow(focus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    public void back(View view) {
        mActivity.finish();
    }

}
