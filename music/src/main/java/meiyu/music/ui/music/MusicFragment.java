package meiyu.music.ui.music;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;


import java.util.ArrayList;
import java.util.List;

import meiyu.core.mvp.BaseFragment;
import meiyu.core.router.provider.IMusicProvider;
import meiyu.core.utils.ToastUtils;
import meiyu.music.R;
import meiyu.music.adapter.MusicRvAdapter;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: MusicFragment
 * Date: 2018/11/27 13:57.
 */
@Route(path = IMusicProvider.MUSIC_MUSIC_FRAGMENT)
public class MusicFragment extends BaseFragment<MusicFragmentView, MusicFragmentPresenter> implements OnRefreshLoadmoreListener {

    RecyclerView mRv, mRv4, mRv5, mRv101, mRv102;
    MusicRvAdapter mRvAdapter, mRvAdapter4, mRvAdapter5, mRvAdapter101, mRvAdapter102;
    List<String> mRvList = new ArrayList<>();
    List<String> mRvList4 = new ArrayList<>();
    List<String> mRvList5 = new ArrayList<>();
    List<String> mRvList101 = new ArrayList<>();
    List<String> mRvList102 = new ArrayList<>();

    ImageView imageView;

     private SmartRefreshLayout swipeRefreshLayout;
    LinearLayout mLlLayoutContainer;

    public static MusicFragment newInstance() {

        Bundle args = new Bundle();

        MusicFragment fragment = new MusicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.swipeRefreshLayout2);
        swipeRefreshLayout.setOnRefreshLoadmoreListener(this);
        swipeRefreshLayout.setEnableRefresh(true);
        swipeRefreshLayout.setEnableLoadmore(true);

        mLlLayoutContainer = (LinearLayout) view.findViewById(R.id.ll_layout_container);

        Glide.with(getContext()).load("").into(imageView);


        initModelData();


    }

    private void initModelData() {
        int fourNum = 2;
        int fiveNum = 3;
        int tenNum = 2;

        int index = mLlLayoutContainer.getChildCount();
        for (int i = 0; i < fourNum; i++) {
            View view = View.inflate(getActivity(), R.layout.item_four_model, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mLlLayoutContainer.addView(view, index, layoutParams);
            mRv4 = view.findViewById(R.id.rv_four_model);
            initData4();

            initAdapter4();
            index++;
        }

        for (int i = 0; i < fiveNum; i++) {
            View view = View.inflate(getActivity(), R.layout.item_five_model, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mLlLayoutContainer.addView(view, index, layoutParams);
            mRv5 = view.findViewById(R.id.rv_five_model);
            initData5();

            initAdapter5();
            index++;
        }

        for (int i = 0; i < tenNum; i++) {
            View view = View.inflate(getActivity(), R.layout.item_ten_model, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mLlLayoutContainer.addView(view, index, layoutParams);
            mRv101 = view.findViewById(R.id.rv_ten_model_1);
            mRv102 = view.findViewById(R.id.rv_ten_model_2);
            initData101();
            initData102();
            initAdapter101();
            initAdapter102();

            index++;
        }
        View view = View.inflate(getActivity(), R.layout.item_footer, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLlLayoutContainer.addView(view, index, layoutParams);
        mRv = view.findViewById(R.id.rv);

        initData();


        index++;


    }

    private void initData() {
//        mRvList.clear();
        for (int i = 0; i < 15; i++) {
            mRvList.add("" + i);
        }
        initAdapter();
    }

    private void initData4() {


        mRvList4.clear();

        //4模型
        for (int i = 0; i < 4; i++) {
            mRvList4.add("" + i);
        }

    }

    private void initData5() {


        mRvList5.clear();
        //5模型
        for (int i = 0; i < 4; i++) {
            mRvList5.add("" + i);
        }

    }

    private void initData101() {

        mRvList101.clear();

        //101模型
        for (int i = 0; i < 4; i++) {
            mRvList101.add("" + i);
        }

    }

    private void initData102() {


        mRvList102.clear();
        //102模型
        for (int i = 0; i < 4; i++) {
            mRvList102.add("" + i);
        }

    }


    private void initAdapter() {
        if (mRvAdapter == null) {
            GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2);

            mRv.setLayoutManager(linearLayoutManager);
            mRvAdapter = new MusicRvAdapter(mRvList);
            mRv.setNestedScrollingEnabled(false);
            mRv.setFocusable(false);
            mRv.setAdapter(mRvAdapter);
            mRvAdapter.notifyDataSetChanged();
        } else {
            mRvAdapter.setNewData(mRvList);
            mRvAdapter.notifyDataSetChanged();
        }
    }

    private void initAdapter4() {

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2);

        mRv4.setLayoutManager(linearLayoutManager);
        MusicRvAdapter mRvAdapter4 = new MusicRvAdapter(mRvList4);
        mRv4.setFocusable(false);
        mRv4.setAdapter(mRvAdapter);
        mRv4.setAdapter(mRvAdapter4);
        mRvAdapter4.notifyDataSetChanged();


    }

    private void initAdapter5() {

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2);

        mRv5.setLayoutManager(linearLayoutManager);
        MusicRvAdapter mRvAdapter5 = new MusicRvAdapter(mRvList5);
        mRv5.setFocusable(false);
        mRv5.setAdapter(mRvAdapter);
        mRv5.setAdapter(mRvAdapter5);
        mRvAdapter5.notifyDataSetChanged();


    }

    private void initAdapter101() {

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2);

        mRv101.setLayoutManager(linearLayoutManager);
        MusicRvAdapter mRvAdapter101 = new MusicRvAdapter(mRvList101);
        mRv101.setFocusable(false);
        mRv101.setAdapter(mRvAdapter);
        mRv101.setAdapter(mRvAdapter101);
        mRvAdapter101.notifyDataSetChanged();

    }

    private void initAdapter102() {

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2);

        mRv102.setLayoutManager(linearLayoutManager);
        MusicRvAdapter mRvAdapter102 = new MusicRvAdapter(mRvList102);
        mRv102.setFocusable(false);
        mRv102.setAdapter(mRvAdapter);
        mRv102.setAdapter(mRvAdapter102);
        mRvAdapter102.notifyDataSetChanged();

    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_music;
    }

    @Override
    public MusicFragmentPresenter createPresenter() {
        return new MusicFragmentPresenter();
    }

    @Override
    public void showShortToast(String message) {

    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        initData();
        ToastUtils.showToast("loadmore");
        refreshlayout.finishLoadmore();

    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        initData();
        ToastUtils.showToast("refresh");
        refreshlayout.finishRefresh();

    }
}
