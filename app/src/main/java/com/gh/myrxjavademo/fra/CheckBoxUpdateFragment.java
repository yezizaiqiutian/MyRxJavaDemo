package com.gh.myrxjavademo.fra;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.gh.myrxjavademo.R;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * @author: gh
 * @description: 随着CheckBox状态发生改变UI而改变
 * @date: 2017/2/22 18:19
 * @note:
 */

public class CheckBoxUpdateFragment extends Fragment {

    private Context mContext;

    @BindView(R.id.cb_1)
    CheckBox cb_1;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.cb_2)
    CheckBox cb_2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkbox_update, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();
        check_update1();
        check_update2();
    }

    /**
     * 同步SharedPreferences
     */
    private void check_update1() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        RxSharedPreferences rxPreferences = RxSharedPreferences.create(preferences);
        Preference<Boolean> xxFunction = rxPreferences.getBoolean("xxFunction", false);

        cb_1.setChecked(xxFunction.get());

        RxCompoundButton.checkedChanges(cb_1)
                .subscribe(xxFunction.asAction());

    }

    /**
     * 同步UI
     */
    private void check_update2() {
        RxCompoundButton.checkedChanges(cb_2)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        btn_login.setClickable(aBoolean);
                        btn_login.setBackgroundResource(aBoolean ? R.color.can_login : R.color.not_login);
                    }
                });
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        Toast.makeText(getActivity(), R.string.login, Toast.LENGTH_SHORT).show();
    }
}
