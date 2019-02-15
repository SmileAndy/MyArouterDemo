package meiyu.core.router.provider;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: IMusicProvider
 * Date: 2018/11/22 11:02.
 */
public interface IMusicProvider extends IFragmentProvider {
    String MUSIC_MAIN_SERVICE = "/music/main/service";
    String MUSIC_MAIN_ACTIVITY = "/music/main/activity";
    String MUSIC_MUSIC_FRAGMENT = "/music/music/fragment";
    String MUSIC_DANCE_FRAGMENT = "/music/dance/fragment";
    String MUSIC_DRAW_FRAGMENT = "/music/draw/fragment";
}
