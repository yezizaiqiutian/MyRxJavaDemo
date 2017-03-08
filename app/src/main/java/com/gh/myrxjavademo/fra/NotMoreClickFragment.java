package com.gh.myrxjavademo.fra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.gh.myrxjavademo.R;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * @author: gh
 * @description: 防止连续点击多次触发View的点击事件Demo,3秒内按钮只能点击1次
 * @date: 2017/2/22 17:51
 * @note:
 */

public class NotMoreClickFragment extends Fragment {

    @BindView(R.id.btn_click)
    Button btnClick;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_not_more_click, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        notMoreClick();
    }

    /**
     * 3秒内不允许按钮多次点击
     */
    private void notMoreClick() {
        RxView.clicks(btnClick)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(getActivity(), R.string.des_demo_not_more_click, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
