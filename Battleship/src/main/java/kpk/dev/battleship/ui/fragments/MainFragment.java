package kpk.dev.battleship.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.List;

import kpk.dev.battleship.R;
import kpk.dev.battleship.gamedata.GameData;
import kpk.dev.battleship.players.AndroidPlayer;
import kpk.dev.battleship.players.PlayerBase;
import kpk.dev.battleship.ui.views.GameArea;

/**
 * Created by krasimir.karamazov on 1/16/14.
 */
public class MainFragment extends Fragment {
    public static final String LOG_TAG = "Battleship";
    private int mCellWidth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GameData.getInstance().addPlayer(new AndroidPlayer("Player_1"));
        GameData.getInstance().addPlayer(new AndroidPlayer("player_2"));
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final GameArea gameArea = (GameArea)rootView;
        gameArea.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //GameData.getInstance().startGame();
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                    gameArea.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }else{
                    gameArea.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
        return rootView;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }
}
