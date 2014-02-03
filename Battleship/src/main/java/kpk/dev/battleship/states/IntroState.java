package kpk.dev.battleship.states;

import android.os.Bundle;

import kpk.dev.battleship.commands.Receiver;
import kpk.dev.battleship.ui.fragments.BaseFragment;
import kpk.dev.battleship.ui.fragments.IntroFragment;

/**
 * Created by krasimir.karamazov on 2/3/14.
 */
public class IntroState extends BaseState {

    public IntroState(Receiver receiver) {
        super(receiver);
    }

    public IntroState() {
        super();
    }

    @Override
    public void setPrevState(BaseState prevState) {
        return;
    }

    @Override
    public BaseFragment getConcreteFragment() {
        final BaseFragment fragment = IntroFragment.getInstance(new Bundle());
        return fragment;
    }

}
