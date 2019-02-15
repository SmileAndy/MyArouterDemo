package meiyu.user.ui.home;

import android.util.Log;

import meiyu.core.http.ApiClient;
import meiyu.core.http.ResponseSubscriberTwo;
import meiyu.core.module.response.ArticleListModel;
import meiyu.core.mvp.BasePresenter;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: HomePresenter
 * Date: 2018/11/27 15:21.
 */
public class HomePresenter extends BasePresenter<HomeView> {

    private static final String TAG = "HomePresenter";
    public void getData(){
//        ApiClient.getInstance().getArticleList(1, new ResponseSubscriberTwo<ArticleListModel>() {
//            @Override
//            protected void onRealSuccess(ArticleListModel articleListModel) {
//                Log.e(TAG,"articleListModel"+articleListModel);
//                Log.e(TAG,"articleListModel"+articleListModel.getLink());
//            }
//        });
    }
}
