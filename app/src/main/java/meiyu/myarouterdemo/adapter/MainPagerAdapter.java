package meiyu.myarouterdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: MainPagerAdapter
 * Date: 2018/11/27 14:35.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList ;
    public MainPagerAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
