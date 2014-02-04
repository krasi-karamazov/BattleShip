package kpk.dev.battleship.ui.fragments;

import android.view.View;
import android.widget.Button;

import kpk.dev.battleship.R;
import kpk.dev.battleship.gamedata.GameData;
import kpk.dev.battleship.players.AndroidPlayer;
import kpk.dev.battleship.states.PrepareBoardsState;

/**
 * Created by krasimir.karamazov on 2/3/14.
 */
public class MenuFragment extends BaseFragment {

    @Override
    protected void initUI(View rootView) {
        Button btnMenu = (Button)rootView.findViewById(R.id.btn_play);
        btnMenu.setOnClickListener(getOnClickListener());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu;
    }

    private View.OnClickListener getOnClickListener(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                GameData.getInstance().addPlayer(new AndroidPlayer("Player_1"));
                GameData.getInstance().addPlayer(new AndroidPlayer("player_2"));
                gotoState(new PrepareBoardsState(null));
            }
        };
    }
}
