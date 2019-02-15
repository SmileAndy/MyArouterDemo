package meiyu.core.mvp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by {guodandan} on 2017/5/11.
 */

public class FragmentCollector {
    private static List<BaseFragment> list=new ArrayList<>();

    public static void  addFragment(BaseFragment fragment){
        list.add(fragment);
    }

    public static void removeFragment(BaseFragment fragment){
        list.remove(fragment);
    }

    public static void hideProgress(){
        for(int i=0;i<list.size();i++){
            list.get(i).hideProgress();
        }
    }
}
