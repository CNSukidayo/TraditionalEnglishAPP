package com.gitee.cnsukidayo.traditionalenglish.activity.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gitee.cnsukidayo.traditionalenglish.R;
import com.gitee.cnsukidayo.traditionalenglish.activity.MainActivity;
import com.gitee.cnsukidayo.traditionalenglish.utils.UserUtils;

public class WelcomeFragment extends Fragment implements View.OnClickListener {

    private View rootView;
    private MainActivity mainActivity;
    private Button disAgree, accept;
    private TextView agreement, userPolicy;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mainActivity = (MainActivity) view.getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            this.rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
        }
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        init();


    }

    private void init() {
        disAgree = rootView.findViewById(R.id.fragment_welcome_disagree);
        accept = rootView.findViewById(R.id.fragment_welcome_accept);
        agreement = rootView.findViewById(R.id.fragment_welcome_userAgreement);
        userPolicy = rootView.findViewById(R.id.fragment_welcome_userPolicy);
        disAgree.setOnClickListener(this);
        accept.setOnClickListener(this);
        agreement.setOnClickListener(this);
        userPolicy.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_welcome_disagree:
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
            case R.id.fragment_welcome_accept:
                UserUtils.getUserSettings().setAcceptUserAgreement(true);
                UserUtils.upDateUserSettings();
                FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.mainFrameLayout, new DetailFragment());
                transaction.commit();
                break;
            // todo 跳转到显示用户协议页面这里最好做成跳转到activity
            case R.id.fragment_welcome_userAgreement:
                break;
            // todo 跳转到显示隐私政策页面
            case R.id.fragment_welcome_userPolicy:
                break;
        }
    }
}