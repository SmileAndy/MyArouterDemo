package meiyu.core.http;

import android.content.Intent;


import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import meiyu.core.Constant;
import meiyu.core.R;
import meiyu.core.application.MyApplication;
import meiyu.core.module.CommonResponse;
import meiyu.core.mvp.ActivityCollector;
import meiyu.core.mvp.FragmentCollector;
import meiyu.core.utils.ToastUtils;



/**
 * Created by sakura on 2016/12/16.
 */

public abstract class ResponseSubscriberTwo<T extends CommonResponse> extends Observable<T> {

//
//    @Override
//    public void onCompleted() {
//    }
//
//    @Override
//    public void onError(Throwable e) {
//        e.printStackTrace();
//        if (ActivityCollector.getTopActivity()!=null){
//            ActivityCollector.getTopActivity().hideProgress();
//        }
//
//        ToastUtils.showToast(R.string.network_error);
//        Intent intent=new Intent();
//        intent.setAction("netWorkError");
//        MyApplication.getContext().sendBroadcast(intent);
//    }
//
//    @Override
//    public void onNext(T t) {
//        if(ActivityCollector.getTopActivity()!=null) {
//            ActivityCollector.getTopActivity().hideProgress();
//        }
//        FragmentCollector.hideProgress();
//
//        if (t.getCode()== Constant.CODE_SUCCESS) {
//            onRealSuccess(t);
//        } else {
//
//            if(t.getCode()==Constant.CODE_UNLOGINED ){
////                未登录
//                ToastUtils.showToast("未登录");
//                return;
//
//            }
//
//        }
//    }
//
//    protected abstract void onRealSuccess(T t);
//
//    protected void onOtherError(T t){
//        if(t.getMessage().equals("参数错误")){
//            return;
//        }
//        ToastUtils.showToast(t.getMessage());
//    };
}
