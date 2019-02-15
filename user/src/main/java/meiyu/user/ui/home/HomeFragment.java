package meiyu.user.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.os.Environment;
import android.support.annotation.Nullable;

import android.support.v4.app.ActivityCompat;
import android.view.Gravity;

import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.io.File;

import cafe.adriel.androidaudioconverter.AndroidAudioConverter;
import cafe.adriel.androidaudioconverter.callback.IConvertCallback;
import cafe.adriel.androidaudioconverter.model.AudioFormat;
import meiyu.core.audio.AudioRecoderUtils;
import meiyu.core.audio.PopupWindowFactory;
import meiyu.core.audio.TimeUtils;
import meiyu.core.mvp.BaseFragment;
import meiyu.core.router.provider.IUserProvider;
import meiyu.user.R;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: HomeFragment
 * Date: 2018/11/27 15:21.
 */
@Route(path= IUserProvider.USER_HOME_FRAGMENT)
public class HomeFragment extends BaseFragment<HomeView,HomePresenter> {

    Button mButton;
    AudioRecoderUtils mAudioRecoderUtils;
    ImageView mImageView;
    TextView mTextView;
    Button mBtnConvert;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Android6.0以后的动态获取权限
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO ) == PackageManager.PERMISSION_GRANTED
                &&ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED
                &&ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED) {
               //这个超连接,java已经处理了，webview不要处理

        } else {
            //申请权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        }


        //当前布局文件的根layout
        final RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.rl);

        mButton = (Button) view.findViewById(R.id.button);
        mBtnConvert = view.findViewById(R.id.btn_convert);

        //PopupWindow的布局文件
        final View popView = View.inflate(getContext(), R.layout.layout_microphone, null);

        final PopupWindowFactory mPop = new PopupWindowFactory(getContext(),popView);

        //PopupWindow布局文件里面的控件
        mImageView = (ImageView) popView.findViewById(R.id.iv_recording_icon);
        mTextView = (TextView) popView.findViewById(R.id.tv_recording_time);
        mImageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_audio));

        mAudioRecoderUtils = new AudioRecoderUtils();

        //录音回调
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {

            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time) {
                //根据分贝值来设置录音时话筒图标的上下波动，下面有讲解
                mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
                mTextView.setText(TimeUtils.long2String(time));
             }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(String filePath) {
                Toast.makeText(getActivity(), "录音保存在：" + filePath, Toast.LENGTH_SHORT).show();
                mTextView.setText(TimeUtils.long2String(0));
             }
        });

        //Button的touch监听
        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:

                        mPop.showAtLocation(rl, Gravity.CENTER,0,0);

                        mButton.setText("松开保存");
                        mAudioRecoderUtils.startRecord();


                        break;

                    case MotionEvent.ACTION_UP:

                        mAudioRecoderUtils.stopRecord();        //结束录音（保存录音文件）
//                        mAudioRecoderUtils.cancelRecord();    //取消录音（不保存录音文件）
                        mPop.dismiss();
                        mButton.setText("按住说话");

                        break;
                }
                return true;
            }
        });


        mBtnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File flacFile = new File(Environment.getExternalStorageDirectory()+"/record/", "18-12-07 11:55:54.amr");
        IConvertCallback callback = new IConvertCallback() {
            @Override
            public void onSuccess(File convertedFile) {
                // So fast? Love it!
                Toast.makeText(getActivity(), "SUCCESS: " + convertedFile.getPath(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Exception error) {
                // Oops! Something went wrong
                Toast.makeText(getActivity(), "ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        Toast.makeText(getContext(), "Converting audio file...", Toast.LENGTH_SHORT).show();
        AndroidAudioConverter.with(getContext())
                // Your current audio file
                .setFile(flacFile)

                // Your desired audio format
                .setFormat(AudioFormat.MP3)

                // An callback to know when conversion is finished
                .setCallback(callback)

                // Start conversion
                .convert();


    }
});



    }

    @Override
    protected void init() {
        getPresenter().getData();

    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void showShortToast(String message) {

    }


}
