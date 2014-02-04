package kpk.dev.battleship.ui.activities;


import java.util.ArrayList;
import java.util.List;

import kpk.dev.battleship.R;
import kpk.dev.battleship.states.IntroState;
import kpk.dev.battleship.ui.navigation.ActionBarNavigationModel;

public class MainActivity extends ActionBarListActivity {


    @Override
    protected List<ActionBarNavigationModel> getMainNavigationItems() {
        final List<ActionBarNavigationModel> navModels = new ArrayList<ActionBarNavigationModel>();
        for(int i = 0; i < 4; i++) {
            ActionBarNavigationModel model  = new ActionBarNavigationModel("Nav model " + i, i, R.drawable.ic_launcher);
            model.setState(new IntroState(this, null));
            navModels.add(model);
        }
        return navModels;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected int getMainFragmentContainer() {
        return R.id.container;
    }
}