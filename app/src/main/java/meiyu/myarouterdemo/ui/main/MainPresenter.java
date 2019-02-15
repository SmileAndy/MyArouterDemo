package meiyu.myarouterdemo.ui.main;

import meiyu.core.mvp.BasePresenter;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: MusicPresenter
 * Date: 2018/11/22 15:39.
 */
public class MainPresenter extends BasePresenter<MainView> {

    public void setData(){

        getView().show("hello");
    }
}
