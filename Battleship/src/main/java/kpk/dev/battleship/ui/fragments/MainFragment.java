package kpk.dev.battleship.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import kpk.dev.battleship.R;
import kpk.dev.battleship.ui.views.GameArea;
import kpk.dev.battleship.ui.views.grid.BattleshipGrid;

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
        final BattleshipGrid grid = (BattleshipGrid)gameArea.findViewById(R.id.battleship_grid);
        grid.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                    grid.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }else{
                    grid.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                mCellWidth = grid.getCellWidth();
                gameArea.startAddingPieces(mCellWidth);
            }
        });
        return rootView;
    }
}
