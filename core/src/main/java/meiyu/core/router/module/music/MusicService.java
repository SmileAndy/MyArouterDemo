package meiyu.core.router.module.music;

import meiyu.core.router.provider.IAppProvider;
import meiyu.core.router.router.ModuleManager;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: MusicService
 * Date: 2018/11/22 11:27.
 */
public class MusicService {

    private static boolean hasModule(){
        return ModuleManager.getInstance().hasModule(IAppProvider.APP_MAIN_SERVICE);
    }
}
