package meiyu.music;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import meiyu.core.router.provider.IMusicProvider;
import meiyu.music.ui.dance.DanceFragment;
import meiyu.music.ui.draw.DrawFragment;
import meiyu.music.ui.music.MusicFragment;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: MusicProvider
 * Date: 2018/11/22 11:39.
 */
@Route(path = IMusicProvider.MUSIC_MAIN_SERVICE)
public class MusicProvider implements IMusicProvider {
    @Override
    public void init(Context context) {

    }

    @Override
    public Fragment newInstance(String type, Object obj) {

        Fragment fragment = null;
        if (type==IMusicProvider.MUSIC_DANCE_FRAGMENT){
            fragment = DanceFragment.newInstance();
        }else if (type==IMusicProvider.MUSIC_MUSIC_FRAGMENT){
            fragment = MusicFragment.newInstance();
        }else if (type==IMusicProvider.MUSIC_DRAW_FRAGMENT){
            fragment = DrawFragment.newInstance();
        }
        return fragment;
    }

}
