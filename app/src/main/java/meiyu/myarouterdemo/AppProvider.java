package meiyu.myarouterdemo;

import android.content.Context;


import com.alibaba.android.arouter.facade.annotation.Route;

import meiyu.core.router.provider.IAppProvider;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: AppProvider
 * Date: 2018/11/22 11:33.
 */
@Route(path = IAppProvider.APP_MAIN_SERVICE)
public class AppProvider implements IAppProvider {
    @Override
    public void init(Context context) {
    }
}
