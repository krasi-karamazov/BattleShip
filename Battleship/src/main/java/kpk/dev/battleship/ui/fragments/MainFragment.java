package kpk.dev.battleship.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kpk.dev.battleship.R;
import kpk.dev.battleship.ui.views.GameArea;

/**
 * Created by krasimir.karamazov on 1/16/14.
 */
public class MainFragment extends Fragment {
    public static final String LOG_TAG = "Battleship";
    private int mCellWidth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        final GameArea gameArea = (GameArea)rootView;

        return rootView;
    }
}
