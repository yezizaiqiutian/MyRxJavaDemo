package com.gh.myrxjavademo.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gh.myrxjavademo.R;

import java.util.List;

/**
 * @author: gh
 * @description: TODO(描述)
 * @date: 2017/2/22 16:24
 * @note:
 */

public class ListAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public ListAdapter(Context context,  List data) {
        super(R.layout.rv_item_text, data);
    }

//    public ListAdapter(Context context,  String[] data) {
//        List list = null;
//        for (String s : data) {
//            list.add(s);
//        }
//        this(context, list);
//    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.id_tv, item);
    }
}
