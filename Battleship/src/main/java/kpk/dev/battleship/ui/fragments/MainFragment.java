package kpk.dev.battleship.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kpk.dev.battleship.R;
import kpk.dev.battleship.gamedata.GameData;
import kpk.dev.battleship.players.AndroidPlayer;
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
        gameArea.init();
        return rootView;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameData.getInstance().addPlayer(new AndroidPlayer());
        GameData.getInstance().addPlayer(new AndroidPlayer());
        setRetainInstance(true);
    }
}
