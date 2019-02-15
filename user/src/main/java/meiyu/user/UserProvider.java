package meiyu.user;


import android.content.Context;
import android.support.v4.app.Fragment;


import com.alibaba.android.arouter.facade.annotation.Route;

import meiyu.core.router.provider.IUserProvider;
import meiyu.user.ui.home.HomeFragment;
import meiyu.user.ui.user.UserFragment;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: UserProvider
 * Date: 2018/11/27 11:59.
 */
@Route(path = IUserProvider.USER_MAIN_SERVICE)
public class UserProvider implements IUserProvider {
    @Override
    public void init(Context context) {

    }

    @Override
    public  Fragment newInstance(String type, Object obj) {

        Fragment fragment=null;
        if (type==IUserProvider.USER_HOME_FRAGMENT){
            fragment = HomeFragment.newInstance();
        }else if (type==IUserProvider.USER_USER_FRAGMENT){
            fragment = UserFragment.newInstance();
        }

        return fragment;
    }
}
