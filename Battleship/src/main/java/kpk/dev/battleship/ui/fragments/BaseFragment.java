package kpk.dev.battleship.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kpk.dev.battleship.states.BaseState;

/**
 * Created by krasimir.karamazov on 2/3/14.
 */
public abstract class BaseFragment extends Fragment{
    private BaseState mState;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        initUI(rootView);
        return rootView;
    }

    public void setState(BaseState state) {
        mState = state;
    }

    public final BaseState getState() {
        return mState;
    }

    protected abstract void initUI(View rootView);

    protected abstract int getLayoutId();
}
