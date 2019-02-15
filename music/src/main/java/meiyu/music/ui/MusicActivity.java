package meiyu.music.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import meiyu.core.mvp.BaseActivity;
import meiyu.core.router.provider.IAppProvider;
import meiyu.core.router.provider.IMusicProvider;
import meiyu.music.R;
import meiyu.music.R2;


@Route(path = IMusicProvider.MUSIC_MAIN_ACTIVITY)
public class MusicActivity extends BaseActivity<MusicView, MusicPresenter> {

    @BindView(R2.id.btn_jump)
    Button mBtn;
    @BindView(R2.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R2.id.iv_toolbar_back)
    LinearLayout ivToolbarBack;
    @BindView(R2.id.iv_toolbar_share_img)
    ImageView ivToolbarShareImg;
    @BindView(R2.id.iv_toolbar_share)
    LinearLayout ivToolbarShare;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;


    @NonNull
    @Override
    public MusicPresenter createPresenter() {
        return new MusicPresenter();
    }


    @Override
    protected int getContentViewResId() {
        return R.layout.activity_music;
    }

    @Override
    protected void init() {

        tvToolbarTitle.setText("音乐");

    }


    @OnClick({R2.id.btn_jump,R2.id.iv_toolbar_back})
    public void onClick(View view) {
        if (view.getId()==R.id.btn_jump){
            ARouter.getInstance().build(IAppProvider.APP_MAIN_ACTIVITY).navigation();

        }else if (view.getId()==R.id.iv_toolbar_back){
            finish();
        }
    }

    @Override
    public void showShortToast(String message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
