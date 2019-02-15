package meiyu.myarouterdemo.ui.splash;

import meiyu.core.mvp.BasePresenter;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: SplashPresenter
 * Date: 2018/11/23 10:24.
 */
public class SplashPresenter extends BasePresenter<SplashView> {

    public void setData(){
        getView().show("hello splash!");
    }
}
