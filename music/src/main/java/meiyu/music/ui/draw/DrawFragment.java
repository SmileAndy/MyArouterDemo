package meiyu.music.ui.draw;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import meiyu.core.mvp.BaseFragment;
import meiyu.core.router.provider.IMusicProvider;
import meiyu.music.R;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: DrawFragment
 * Date: 2018/11/27 15:16.
 */
@Route(path= IMusicProvider.MUSIC_DRAW_FRAGMENT)
public class DrawFragment extends BaseFragment<DrawView,DrawPresenter> {

    public static DrawFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DrawFragment fragment = new DrawFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {

    }

    @Override
    public int getViewLayoutId() {
        return  R.layout.fragment_draw;
    }

    @Override
    public DrawPresenter createPresenter() {
        return new DrawPresenter();
    }

    @Override
    public void showShortToast(String message) {

    }
}
