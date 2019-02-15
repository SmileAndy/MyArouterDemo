package meiyu.core.router.router;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;

import meiyu.core.router.provider.IAppProvider;
import meiyu.core.router.provider.IMusicProvider;
import meiyu.core.router.provider.IUserProvider;


/**
 * Created by wxmylife on 2017/4/21.
 */

public class ServiceManager {
    //服务注入看自己的具体实现
    //自动注入

    //可以不使用@Autowired，手动发现服务

    @Autowired
    IMusicProvider musicProvider;
    @Autowired
    IAppProvider appProvider;
    @Autowired
    IUserProvider userProvider;


    public ServiceManager() {
        ARouter.getInstance().inject(this);
    }

    private static final class ServiceManagerHolder {
        private static final ServiceManager instance = new ServiceManager();
    }

    public static ServiceManager getInstance() {
        return ServiceManagerHolder.instance;
    }


    public IMusicProvider getMusicProvider() {
        return musicProvider != null ? musicProvider : (musicProvider = ((IMusicProvider) ModuleRouter.newInstance(IMusicProvider.MUSIC_MAIN_SERVICE).navigation()));
    }

    public IAppProvider getAppProvider() {
        return appProvider != null ? appProvider : (appProvider = (IAppProvider) ModuleRouter.newInstance(IAppProvider.APP_MAIN_SERVICE).navigation());
    }

    public IUserProvider getUserProvider() {
        return userProvider != null ? userProvider : (userProvider = (IUserProvider) ModuleRouter.newInstance(IUserProvider.USER_MAIN_SERVICE).navigation());
    }


}
