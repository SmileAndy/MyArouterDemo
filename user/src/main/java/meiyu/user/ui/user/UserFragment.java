package meiyu.user.ui.user;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cafe.adriel.androidaudioconverter.AndroidAudioConverter;
import cafe.adriel.androidaudioconverter.callback.IConvertCallback;
import cafe.adriel.androidaudioconverter.model.AudioFormat;
import meiyu.core.mvp.BaseFragment;
import meiyu.core.router.provider.IUserProvider;
import meiyu.user.R;
import meiyu.user.audio.AudioRecorderButton;
import meiyu.user.audio.MediaManager;
import meiyu.user.audio.Recorder;
import meiyu.user.audio.RecorderAdapter;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: UserFragment
 * Date: 2018/11/27 12:01.
 */
@Route(path= IUserProvider.USER_USER_FRAGMENT)
public class UserFragment  extends BaseFragment<UserView,UserPresenter>{


    private ListView mListView;
    private ArrayAdapter<Recorder> mAdapter;
    private List<Recorder> mDatas =new ArrayList<>();

    private AudioRecorderButton mAudioRecorderButton;
    private View mAnimView;
    RelativeLayout mRlAudioItem;

    //item 最小最大值
    private int mMinItemWidth;
    private int mMaxIItemWidth;

    TextView mTvSeconds;
    View length;

    public static UserFragment newInstance() {

        Bundle args = new Bundle();

        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        setListViewAdapter();
        initScreen();
    }

    private void initScreen() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        //item 设定最小最大值
        mMaxIItemWidth = (int) (outMetrics.widthPixels * 0.7f);
        mMinItemWidth = (int) (outMetrics.widthPixels * 0.15f);
    }

    File flacFile;
    private void initView(View view ){
        mListView = view.findViewById(R.id.id_listview);
        mAudioRecorderButton = view.findViewById(R.id.id_recorder_button);
        mRlAudioItem = view.findViewById(R.id.rl_audio_item);

        mTvSeconds =  view.findViewById(R.id.id_recorder_time);
        length = view.findViewById(R.id.id_recorder_length);


        mAudioRecorderButton.setAudioFinishRecorderListener(new AudioRecorderButton.AudioFinishRecorderListener() {
            @Override
            public void onFinish(float seconds, String filePath) {
                //每完成一次录音
//                Recorder recorder = new Recorder(seconds,filePath);
//                mDatas.add(recorder);
//                //更新adapter
//                mAdapter.notifyDataSetChanged();
//                //设置listview 位置
//                mListView.setSelection(mDatas.size()-1);



                //语音格式转换
                flacFile = new File( filePath);
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


                //设置时间  matt.round 四舍五入
                mTvSeconds.setText(Math.round(seconds)+"\"");
                //设置背景的宽度
                ViewGroup.LayoutParams lp = length.getLayoutParams();
                //getItem(position).time
                lp.width = (int) (mMinItemWidth + (mMaxIItemWidth / 60f*seconds));



            }
        });

        mRlAudioItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //播放音频  完成后改回原来的background
                MediaManager.playSound( flacFile.getPath(), new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
//                        mAnimView.setBackgroundResource(R.drawable.icon_audio);
                    }
                });
            }
        });
    }

    private void setListViewAdapter(){
        mAdapter = new RecorderAdapter(getContext(), mDatas);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //如果第一个动画正在运行， 停止第一个播放其他的
                if (mAnimView != null) {
                    mAnimView.setBackgroundResource(R.drawable.icon_audio);
                    mAnimView = null;
                }
                //播放动画
                mAnimView = view.findViewById(R.id.id_recorder_anim);
                mAnimView.setBackgroundResource(R.drawable.icon_audio);
//                AnimationDrawable animation = (AnimationDrawable) mAnimView.getBackground();
//                animation.start();

                //播放音频  完成后改回原来的background
                MediaManager.playSound(mDatas.get(position).filePath, new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mAnimView.setBackgroundResource(R.drawable.icon_audio);
                    }
                });
            }
        });
    }

    /**
     * 根据生命周期 管理播放录音
     */
    @Override
    public void onPause() {
        super.onPause();
        MediaManager.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        MediaManager.resume();
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MediaManager.release();
    }

    @Override
    protected void init() {

    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    public UserPresenter createPresenter() {
        return new UserPresenter();
    }

    @Override
    public void showShortToast(String message) {

    }
}
