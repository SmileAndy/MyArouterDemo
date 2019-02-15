package meiyu.core.router.module.app;

import meiyu.core.router.provider.IAppProvider;
import meiyu.core.router.router.ModuleManager;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: AppService
 * Date: 2018/11/22 10:59.
 */
public class AppService {

    private static boolean hasModule(){
        return ModuleManager.getInstance().hasModule(IAppProvider.APP_MAIN_SERVICE);
    }
}
