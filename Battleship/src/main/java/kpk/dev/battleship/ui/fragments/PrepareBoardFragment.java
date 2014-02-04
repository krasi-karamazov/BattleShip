package kpk.dev.battleship.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import kpk.dev.battleship.R;
import kpk.dev.battleship.gamedata.GameData;
import kpk.dev.battleship.players.AndroidPlayer;
import kpk.dev.battleship.ui.views.GameArea;

/**
 * Created by krasimir.karamazov on 2/4/14.
 */
public class PrepareBoardFragment extends BaseFragment {

    public static BaseFragment getInstance(Bundle args) {
        BaseFragment fragment = new PrepareBoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initUI(View rootView) {

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
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_prepare_board;
    }
}
