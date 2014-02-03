package kpk.dev.battleship.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kpk.dev.battleship.R;
import kpk.dev.battleship.states.MenuState;

/**
 * Created by krasimir.karamazov on 2/3/14.
 */
public class IntroFragment extends BaseFragment {

    public static final BaseFragment getInstance(Bundle args) {
        BaseFragment fragment = new IntroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initUI(View rootView) {
        Button btn = (Button)rootView.findViewById(R.id.btn_menu);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getState().gotoState(new MenuState());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_intro;
    }
}
