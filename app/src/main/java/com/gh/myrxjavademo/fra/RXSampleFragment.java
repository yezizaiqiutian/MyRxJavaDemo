package com.gh.myrxjavademo.fra;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gh.myrxjavademo.R;
import com.gh.myrxjavademo.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: gh
 * @description: TODO(描述)
 * @date: 2017/2/22 17:44
 * @note:
 */

public class RXSampleFragment extends Fragment {

    private Context mContext;

    @BindView(R.id.id_rv_list)
    RecyclerView idRvList;

    private String[] textList = {
            "防止连续点击多次触发View的点击事件Demo,3秒内按钮只能点击1次",
            "随着CheckBox状态发生改变UI而改变",
            "RxJava实现搜索关键字推荐Demo",
            "Buffer操作符"};

    private ListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_list, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();
        List list = new ArrayList();
        for (String s : textList) {
            list.add(s);
        }

        idRvList.setHasFixedSize(true);
        idRvList.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new ListAdapter(mContext, list);
        idRvList.setAdapter(mAdapter);
        idRvList.addOnItemTouchListener(onItemClickListener);
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            switch (position) {
                case 0:
                    open(new NotMoreClickFragment());
                    break;
                case 1:
                    open(new CheckBoxUpdateFragment());
                    break;
                case 2:
                    open(new DebounceFragment());
                    break;
                case 3:
                    open(new BufferFragment());
                    break;
            }
        }
    };

    /**
     * 开启新的Fragment
     */
    private void open(Fragment fragment) {
        final String tag = fragment.getClass().toString();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.main_content, fragment, tag)
                .commit();
    }
}
