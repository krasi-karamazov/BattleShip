package kpk.dev.battleship.states;

import kpk.dev.battleship.commands.Receiver;
import kpk.dev.battleship.ui.fragments.BaseFragment;
import kpk.dev.battleship.ui.fragments.MenuFragment;

/**
 * Created by krasimir.karamazov on 2/3/14.
 */
public class MenuState extends BaseState {

    public MenuState(Receiver receiver) {
        super(receiver);
    }

    public MenuState() {
    }

    @Override
    public BaseFragment getConcreteFragment() {
        final BaseFragment fragment = new MenuFragment();
        return fragment;
    }
}
