package meiyu.myarouterdemo.ui.splash;


import android.os.Handler;
import android.support.annotation.NonNull;

import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;



import butterknife.BindView;
import butterknife.OnClick;
import meiyu.core.mvp.BaseActivity;
import meiyu.core.router.provider.IAppProvider;
import meiyu.core.router.provider.IMusicProvider;
import meiyu.myarouterdemo.R;

@Route(path = IAppProvider.APP_SPLASH_ACTIVITY)
public class SplashActivity extends BaseActivity<SplashView, SplashPresenter> implements SplashView {


    @BindView(R.id.btn_splash)
    Button splash;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.iv_toolbar_back)
    LinearLayout ivToolbarBack;


    @NonNull
    @Override
    public SplashPresenter createPresenter() {
        return new SplashPresenter();
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        getPresenter().setData();
        tvToolbarTitle.setText("splash");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ARouter.getInstance().build(IAppProvider.APP_MAIN_ACTIVITY).navigation();
            }
        },3000);
    }

    @Override
    public void showShortToast(String message) {

    }

    @OnClick({R.id.btn_splash,R.id.iv_toolbar_back})
    public void onClick(View view) {
        if (view.getId()==R.id.btn_splash){
            ARouter.getInstance().build(IMusicProvider.MUSIC_MAIN_ACTIVITY).navigation();
        }else if (view.getId()==R.id.iv_toolbar_back){
            finish();
        }

    }

    @Override
    public void show(String data) {
        Toast.makeText(mActivity, data, Toast.LENGTH_SHORT).show();
    }


}
