package com.gitee.cnsukidayo.traditionalenglish.ui.fragment.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.gitee.cnsukidayo.traditionalenglish.R;
import com.gitee.cnsukidayo.traditionalenglish.factory.StaticFactory;

/**
 *
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {

    private View rootView;
    private RelativeLayout accountAndSecurity, triplePartInfoShare;
    private TextView title;
    private ImageButton backUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView != null) {
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        bindView();
        this.title.setText(R.string.settings);
        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_settings_content_account:
                break;
            case R.id.fragment_settings_content_triple:
                Navigation.findNavController(getView()).navigate(R.id.action_navigation_settings_to_navigation_user_agreement, null, StaticFactory.getSimpleNavOptions());
                break;
            case R.id.toolbar_back_to_trace:
                Navigation.findNavController(getView()).popBackStack();
                break;
        }
    }

    private void bindView() {
        this.accountAndSecurity = rootView.findViewById(R.id.fragment_settings_content_account);
        this.triplePartInfoShare = rootView.findViewById(R.id.fragment_settings_content_triple);
        this.title = rootView.findViewById(R.id.toolbar_title);
        this.backUp = rootView.findViewById(R.id.toolbar_back_to_trace);

        this.backUp.setOnClickListener(this);
        this.accountAndSecurity.setOnClickListener(this);
        this.triplePartInfoShare.setOnClickListener(this);
    }
}