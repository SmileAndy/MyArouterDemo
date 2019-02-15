package meiyu.core.router.module.user;
import meiyu.core.router.provider.IUserProvider;
import meiyu.core.router.router.ModuleManager;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: UserService
 * Date: 2018/11/27 11:55.
 */
public class UserService {

    private static boolean hasModule(){
        return ModuleManager.getInstance().hasModule(IUserProvider.USER_MAIN_SERVICE);
    }
}
