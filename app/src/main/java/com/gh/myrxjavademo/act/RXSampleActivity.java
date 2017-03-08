package com.gh.myrxjavademo.act;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.gh.myrxjavademo.R;
import com.gh.myrxjavademo.base.BaseActivity;
import com.gh.myrxjavademo.fra.RXSampleFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: gh
 * @description: RxJava_For_Android Demo练习
 * @date: 2017/2/22 14:22
 * @note:
 */

public class RXSampleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxsample);
        initFragment();
    }

    private void initFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, new RXSampleFragment(), RXSampleFragment.class.getName())
                .commit();
    }

}
