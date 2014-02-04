package kpk.dev.battleship.states;

import android.os.Bundle;

import kpk.dev.battleship.commands.Receiver;
import kpk.dev.battleship.ui.fragments.BaseFragment;
import kpk.dev.battleship.ui.fragments.IntroFragment;

/**
 * Created by krasimir.karamazov on 2/3/14.
 */
public class IntroState extends BaseState {

    public IntroState(Receiver receiver, Bundle args) {
        super(receiver, args);
    }

    public IntroState(Bundle args) {
        super(args);
    }

    @Override
    public void setPrevState(BaseState prevState) {
        return;
    }

    @Override
    public BaseFragment getConcreteFragment() {
        return new IntroFragment();
    }

}
