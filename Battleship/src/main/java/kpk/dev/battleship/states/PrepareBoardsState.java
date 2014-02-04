package kpk.dev.battleship.states;

import android.os.Bundle;

import kpk.dev.battleship.commands.Receiver;
import kpk.dev.battleship.ui.fragments.BaseFragment;
import kpk.dev.battleship.ui.fragments.PrepareBoardFragment;

/**
 * Created by krasimir.karamazov on 2/3/14.
 */
public class PrepareBoardsState extends BaseState {

    public PrepareBoardsState(Receiver receiver, Bundle args) {
        super(receiver, args);
    }

    public PrepareBoardsState(Bundle args) {
        super(args);
    }

    @Override
    protected BaseFragment getConcreteFragment() {
        return PrepareBoardFragment.getInstance(getArgs());
    }
}
