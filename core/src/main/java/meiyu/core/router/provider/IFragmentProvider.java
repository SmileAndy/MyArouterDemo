package meiyu.core.router.provider;

import android.support.v4.app.Fragment;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: IFragmentProvider
 * Date: 2018/11/27 15:53.
 */
public interface IFragmentProvider extends IBaseProvider {
    Fragment newInstance(String type, Object obj);
}
