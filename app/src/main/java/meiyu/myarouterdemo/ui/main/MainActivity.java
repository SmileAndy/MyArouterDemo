package meiyu.myarouterdemo.ui.main;

import android.app.Service;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.OnClick;
import meiyu.core.mvp.BaseActivity;
import meiyu.core.router.provider.IAppProvider;
import meiyu.core.router.provider.IMusicProvider;
import meiyu.core.router.provider.IUserProvider;
import meiyu.core.router.router.ServiceManager;
import meiyu.core.widget.BottomNavigationViewHelper;
import meiyu.myarouterdemo.R;
import meiyu.myarouterdemo.adapter.MainPagerAdapter;

@Route(path = IAppProvider.APP_MAIN_ACTIVITY)
public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {


    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.iv_toolbar_back)
    LinearLayout ivToolbarBack;
    @BindView(R.id.main_container)
    FrameLayout mainContainer;
    @BindView(R.id.bottomnavigation)
    BottomNavigationView bottomnavigation;

    FragmentTransaction ft;
    Fragment homeFragment, musicFragment, danceFragment, drawFragment, userFragment;
    private int selectPosition = 1;


    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        getPresenter().setData();
        tvToolbarTitle.setText("首页");
        selectFragment(selectPosition);
        BottomNavigationViewHelper.disableShiftMode(bottomnavigation);
        bottomnavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_one:
                        selectFragment(1);
                        return true;
                    case R.id.tab_two:
                        selectFragment(2);
                        return true;
                    case R.id.tab_three:
                        selectFragment(3);
                        return true;
                    case R.id.tab_four:
                        selectFragment(4);
                        return true;
                    case R.id.tab_five:
                        selectFragment(5);
                        return true;
                    default:
                        break;
                }
                return true;
            }


        });


    }

    private void selectFragment(int position) {

        ft = getSupportFragmentManager().beginTransaction();
        hideFragment(selectPosition);
        switch (position) {
            case 1:
                if (homeFragment == null) {

                    homeFragment = ServiceManager.getInstance().getUserProvider().newInstance(IUserProvider.USER_HOME_FRAGMENT, null);
                    ft.add(R.id.main_container, homeFragment);
                } else {
                    /*首页---已经加载过，直接显示*/
                    homeFragment.onResume();
                    ft.show(homeFragment);
                }
                tvToolbarTitle.setText("首页");
                break;
            case 2:
                if (musicFragment==null){
                    musicFragment = ServiceManager.getInstance().getMusicProvider().newInstance(IMusicProvider.MUSIC_MUSIC_FRAGMENT,null);
                    ft.add(R.id.main_container,musicFragment);
                }else{
                    musicFragment.onResume();
                    ft.show(musicFragment);
                }
                tvToolbarTitle.setText("音乐");
                break;
            case 3:
                if (danceFragment==null){
                    danceFragment = ServiceManager.getInstance().getMusicProvider().newInstance(IMusicProvider.MUSIC_DANCE_FRAGMENT,null);
                    ft.add(R.id.main_container,danceFragment);
                }else{
                    danceFragment.onResume();
                    ft.show(danceFragment);
                }
                tvToolbarTitle.setText("舞蹈");
                break;
            case 4:
                if (drawFragment==null){
                    drawFragment = ServiceManager.getInstance().getMusicProvider().newInstance(IMusicProvider.MUSIC_DRAW_FRAGMENT,null);
                    ft.add(R.id.main_container,drawFragment);
                }else{
                    drawFragment.onResume();
                    ft.show(drawFragment);
                }
                tvToolbarTitle.setText("绘画");
                break;
            case 5:
                if (userFragment==null){
                    userFragment = ServiceManager.getInstance().getUserProvider().newInstance(IUserProvider.USER_USER_FRAGMENT,null);
                    ft.add(R.id.main_container,userFragment);
                }else{
                    userFragment.onResume();
                    ft.show(userFragment);
                }
                tvToolbarTitle.setText("用户");
                break;
            default:
                break;


        }
        ft.commit();
        selectPosition = position;


    }

    /**
     * 隐藏对应的fragment
     *
     * @param index
     */
    private void hideFragment(int index) {
        switch (index) {
            case 1:
                if (homeFragment != null) {
                    ft.hide(homeFragment);
                }
                break;
            case 2:
                if (musicFragment != null) {
                    ft.hide(musicFragment);
                }
                break;
            case 3:
                if (danceFragment != null) {
                    ft.hide(danceFragment);
                }
                break;
            case 4:
                if (drawFragment != null) {
                    ft.hide(drawFragment);
                }
                break;
            case 5:
                if (userFragment != null) {
                    ft.hide(userFragment);
                }
                break;
           default:
                break;



        }
    }


    @Override
    public void showShortToast(String message) {

    }


    @OnClick({R.id.iv_toolbar_back})
    public void onViewClicked(View view) {

        if (view.getId() == R.id.iv_toolbar_back) {
            finish();
        }
    }

    @Override
    public void show(String data) {
        Toast.makeText(mActivity, data, Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

}
