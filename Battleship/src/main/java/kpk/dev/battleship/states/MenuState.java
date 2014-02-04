package kpk.dev.battleship.states;

import android.os.Bundle;
import kpk.dev.battleship.commands.Receiver;
import kpk.dev.battleship.ui.fragments.BaseFragment;
import kpk.dev.battleship.ui.fragments.MenuFragment;

/**
 * Created by krasimir.karamazov on 2/3/14.
 */
public class MenuState extends BaseState {

    public MenuState(Receiver receiver, Bundle args) {
        super(receiver, args);
    }

    public MenuState(Bundle args) {
        super(args);
    }

    @Override
    public BaseFragment getConcreteFragment() {
        final BaseFragment fragment = new MenuFragment();
        return fragment;
    }
}
