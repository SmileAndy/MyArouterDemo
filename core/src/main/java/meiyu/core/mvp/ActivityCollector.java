package meiyu.core.mvp;



import java.util.ArrayList;
import java.util.List;

/**
 * 所有activity的管理类
 * Created by teikasei on 2017/1/11.
 */

public class ActivityCollector {
    /**
     * 存储activity的列表
     */
    private static List<BaseActivity> activityList = new ArrayList<>();

    /**
     * 添加某个activity
     *
     * @param activity
     */
    public static void addActivity(BaseActivity activity) {
        activityList.add(activity);
    }

    /**
     * 移除某个activity
     *
     * @param activity
     */
    public static void removeActivity(BaseActivity activity) {
        activityList.remove(activity);
    }

    /**
     * 获取栈顶的activity
     *
     * @return
     */
    public static BaseActivity getTopActivity() {
        if (activityList.isEmpty()) {
            return null;
        } else {
            return activityList.get(activityList.size() - 1);
        }
    }
}
