package meiyu.music.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import meiyu.music.R;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: MusicRvAdapter
 * Date: 2018/12/29 10:12.
 */
public class MusicRvAdapter  extends BaseQuickAdapter<String,BaseViewHolder> {
    public MusicRvAdapter( @Nullable List<String> data) {
        super(R.layout.item_rv_footer, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.title,"第"+item+"个");

    }
}
