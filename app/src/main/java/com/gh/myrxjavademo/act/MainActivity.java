package com.gh.myrxjavademo.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gh.myrxjavademo.R;
import com.gh.myrxjavademo.adapter.ListAdapter;
import com.gh.myrxjavademo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gh.myrxjavademo.R.id.id_rv_list;

public class MainActivity extends BaseActivity {

    @BindView(id_rv_list)
    RecyclerView idRvList;

    private String[] textList = {"RXSample"};
    private Class[] activityList = {RXSampleActivity.class};

    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list);
        ButterKnife.bind(this);

    }

    @Override
    protected void initView() {
        List list = new ArrayList();
        for (String s : textList) {
            list.add(s);
        }

        idRvList.setHasFixedSize(true);
        idRvList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ListAdapter(this,  list);
        idRvList.setAdapter(mAdapter);
        idRvList.addOnItemTouchListener(onItemClickListener);
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(mContext, activityList[position]);
            mContext.startActivity(intent);
        }
    };
}
