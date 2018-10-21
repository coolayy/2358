package com.cheng.game2358.ui.activity;

import com.androidas.lib.ui.activity.BaseActivity;
import com.androidas.lib.ui.fragment.BaseFragment;
import com.cheng.game2358.R;
import com.cheng.game2358.ui.fragment.MainFragment;

public class MainActivity extends BaseActivity {
    @Override
    protected BaseFragment getFirstFragment() {
        return MainFragment.newInstance();
    }

    @Override
    protected int getActLayoutId() {
        return R.layout.act_base;
    }

    @Override
    protected int getFragContentLayoutId() {
        return R.id.main_frame;
    }
}
