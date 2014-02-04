package kpk.dev.battleship.states;

import android.os.Bundle;

import kpk.dev.battleship.commands.Receiver;
import kpk.dev.battleship.ui.fragments.BaseFragment;

/**
 * Created by krasimir.karamazov on 2/3/14.
 */
public abstract class BaseState {
    private BaseState mPrevState;
    private Receiver mReceiver;
    private Bundle mArgs;
    public BaseState(Receiver receiver, Bundle args) {
        this(args);
        mReceiver = receiver;
    }

    public BaseState(Bundle args) {
        mArgs = args;
    }

    public void setPrevState(BaseState prevState) {
        mPrevState = prevState;
    }

    public BaseState getPrevState(){
        return mPrevState;
    }

    protected final void setReceiver(Receiver receiver){
        mReceiver = receiver;
    }
    protected final Bundle getArgs() {
        return mArgs;
    }

    public final BaseFragment getFragment(){
        final BaseFragment fragment = getConcreteFragment();
        fragment.setState(this);
        fragment.setArguments(mArgs);
        return fragment;
    }

    protected abstract BaseFragment getConcreteFragment();

    public final void gotoState(BaseState state) {
        state.setReceiver(mReceiver);
        mReceiver.changeState(state);
    }
}
