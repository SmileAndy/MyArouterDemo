package meiyu.core.application;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.internal.Utils;
import cafe.adriel.androidaudioconverter.AndroidAudioConverter;
import cafe.adriel.androidaudioconverter.callback.ILoadCallback;
import meiyu.core.Constant;
import meiyu.core.router.config.ModuleOptions;
import meiyu.core.router.provider.IAppProvider;
import meiyu.core.router.provider.IMusicProvider;
import meiyu.core.router.router.ModuleManager;
import meiyu.core.sp.Prefs;
import meiyu.core.sp.SpEnum;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: MyApplication
 * Date: 2018/11/22 10:22.
 */
public class MyApplication extends Application {

    private static MyApplication app = null;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        initRouter();
        initSp();

        initAudioConverter();//音频格式转换


    }

        private void initAudioConverter() {
            AndroidAudioConverter.load(this, new ILoadCallback() {
                @Override
                public void onSuccess() {
                    // Great!
                }
                @Override
                public void onFailure(Exception error) {
                    // FFmpeg is not supported by device
                    error.printStackTrace();
                }
            });
        }

    public static Context getContext() {
        return app.getApplicationContext();
    }

    private void initRouter() {
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

        ModuleOptions.ModuleBuilder builder = new ModuleOptions.ModuleBuilder(this)

                .addModule(IAppProvider.APP_MAIN_SERVICE, IAppProvider.APP_MAIN_SERVICE)
                .addModule(IMusicProvider.MUSIC_MAIN_SERVICE,IMusicProvider.MUSIC_MAIN_SERVICE);

        ModuleManager.getInstance().init(builder.build());
    }

    private void initSp() {
        // sp 初始化
        new Prefs.Builder()
                .setContext(this)
//                .setMode(MODE_PRIVATE)
                .setPrefsName("yhjs")
                .setUseDefaultSharedPreference(true)
                .build();
        if (Prefs.getString(SpEnum.SIGN_DYNAMIC_VALUE.name(), "").equals("")) {
            Prefs.putString(SpEnum.SIGN_DYNAMIC_VALUE.name(), "34897511");
        }
    }


}
