package com.gh.myrxjavademo.fra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gh.myrxjavademo.R;
import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * @author: gh
 * @description: TODO(描述)
 * @date: 2017/2/23 11:12
 * @note:
 */

public class BufferFragment extends RxFragment {

    @BindView(R.id.btn_buffer_count)
    Button btn_buffer_count;
    @BindView(R.id.et_input)
    EditText et_input;
    @BindView(R.id.btn_buffer_count_skip)
    Button btn_buffer_count_skip;
    @BindView(R.id.tv_output)
    TextView tv_output;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buffer, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        demo_buffer_count();
    }

    private void demo_buffer_count() {
        RxView.clicks(btn_buffer_count)
                .buffer(3)//执行第三个
                .compose(this.<List<Void>>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Void>>() {
                    @Override
                    public void call(List<Void> voids) {
                        Toast.makeText(BufferFragment.this.getActivity(), R.string.des_demo_buffer_count, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick(R.id.btn_buffer_count_skip)
    public void btn_buffer_count_skip() {
        tv_output.setText("");
        char[] cs = et_input.getText().toString().trim().toCharArray();;
        Character[] chs = new Character[cs.length];
        for (int i=0;i<cs.length;i++) {
            chs[i] = cs[i];
        }

        Observable.from(chs)
                .buffer(2, 3)//执行两个跳一个
                .compose(this.<List<Character>>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Character>>() {
                    @Override
                    public void call(List<Character> characters) {
                        tv_output.setText(tv_output.getText() + characters.toString());
                    }
                });
    }
}
