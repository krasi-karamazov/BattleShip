package kpk.dev.battleship.states;

import kpk.dev.battleship.commands.Receiver;
import kpk.dev.battleship.ui.fragments.BaseFragment;

/**
 * Created by krasimir.karamazov on 2/3/14.
 */
public class PrepareBoardsState extends BaseState {

    public PrepareBoardsState(Receiver receiver) {
        super(receiver);
    }

    public PrepareBoardsState() {
        super();
    }

    @Override
    protected BaseFragment getConcreteFragment() {
        return null;
    }
}
