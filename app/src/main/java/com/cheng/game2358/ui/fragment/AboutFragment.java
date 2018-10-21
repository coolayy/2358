package com.cheng.game2358.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidas.lib.ui.fragment.BaseFragment;
import com.cheng.game2358.R;
import com.cheng.game2358.common.Common;
import com.cheng.game2358.ui.view.IconButton;


public class AboutFragment extends BaseFragment {

        private IconButton Aisi;
    private IconButton Aiwu;
    private IconButton Ailiu;
    private IconButton Aiqi;
    private IconButton Quedd;
    public static int BIAN=4;
    public static int CHENG=6;

    private ImageButton backBtn;
    private TextView githubText;
    private TextView part1Text;
    private TextView part2Text;
    private TextView titleText;

    private boolean isHa;

    public static AboutFragment newInstance(boolean isHa) {
        Bundle args = new Bundle();
        args.putBoolean(Common.HA_MODE, isHa);
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.frag_about;
    }

    @Override
    protected void initView(View rootView) {
               Aisi = (IconButton) rootView.findViewById(R.id.btn_chengsi);
        Aiwu = (IconButton) rootView.findViewById(R.id.btn_chengwu);
      Ailiu = (IconButton) rootView.findViewById(R.id.btn_chengliu);
        Aiqi = (IconButton) rootView.findViewById(R.id.btn_chengqi);
        Quedd = (IconButton) rootView.findViewById(R.id.btn_quedd);
        backBtn = (ImageButton) rootView.findViewById(R.id.btn_back);
        titleText = (TextView) rootView.findViewById(R.id.text_about_title);
//        part1Text = (TextView) rootView.findViewById(R.id.text_about_plant1);
//        part2Text = (TextView) rootView.findViewById(R.id.text_about_plant2);
//        githubText = (TextView) rootView.findViewById(R.id.text_github);

        isHa = getArguments().getBoolean(Common.HA_MODE, false);

        titleText.setText(getString(isHa ? R.string.About_2048_ha : R.string.About_2048));
//        part1Text.setText(getString(isHa ? R.string.About_2048_part1_ha : R.string.About_2048_part1));
//        part2Text.setText(getString(isHa ? R.string.About_2048_part2_ha : R.string.About_2048_part2));
    }

    @Override
    protected void setListener(View rootView) {
//        githubText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(IntentUtils.openWebDefault(githubText.getText().toString()));
//            }
//        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutFragment.this.removeFragment();
            }
        });

                Aisi.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BIAN=4;
//                addFragment(GameFragment.newInstance(false, isHa));
        }
    });
       Aiwu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BIAN=5;
//                addFragment(GameFragment.newInstance(true, isHa));
        }
    });
       Ailiu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BIAN=6;
//                addFragment(HowPlayFragment.newInstance(isHa));
        }
    });
       Aiqi.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BIAN=7;
//                addFragment(AboutFragment.newInstance(isHa));
        }
       });

        Quedd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CHENG=BIAN;
            AboutFragment.this.removeFragment();
//                addFragment(AboutFragment.newInstance(isHa));
        }
       });
    }
}