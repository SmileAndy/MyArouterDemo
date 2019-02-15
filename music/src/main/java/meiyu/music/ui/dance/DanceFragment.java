package meiyu.music.ui.dance;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import meiyu.core.mvp.BaseFragment;
import meiyu.core.router.provider.IMusicProvider;
import meiyu.music.R;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: DanceFragment
 * Date: 2018/11/27 15:11.
 */
@Route(path= IMusicProvider.MUSIC_DANCE_FRAGMENT)
public class DanceFragment extends BaseFragment<DanceFragmentView,DanceFragmentPresenter> {


    private WebView webView;
    public static DanceFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DanceFragment fragment = new DanceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {

    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webView = view.findViewById(R.id.webview);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);


        webView.loadUrl("http://mds.kidsmusic.com.cn/h5/class_list");
//        webView.addJavascriptInterface(this,"android");//添加js监听 这样html就能调用客户端


//        WebSettings webSettings=webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);//允许使用js

        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
//        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.
//
//        //支持屏幕缩放
//        webSettings.setSupportZoom(true);
//        webSettings.setBuiltInZoomControls(true);


    }


    @Override
    public void onPause() {
        super.onPause();
        webView.loadUrl("about:blank");

    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_dance;
    }

    @Override
    public DanceFragmentPresenter createPresenter() {
        return new DanceFragmentPresenter();
    }

    @Override
    public void showShortToast(String message) {

    }
}
