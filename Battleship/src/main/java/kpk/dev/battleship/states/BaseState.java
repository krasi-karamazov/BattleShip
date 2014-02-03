package kpk.dev.battleship.states;

import kpk.dev.battleship.commands.Receiver;
import kpk.dev.battleship.ui.fragments.BaseFragment;

/**
 * Created by krasimir.karamazov on 2/3/14.
 */
public abstract class BaseState {
    private BaseState mPrevState;
    private Receiver mReceiver;
    public BaseState(Receiver receiver) {
        mReceiver = receiver;
    }

    public BaseState() {

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

    public final BaseFragment getFragment(){
        BaseFragment fragment = getConcreteFragment();
        fragment.setState(this);
        return fragment;
    }

    public final String getFragmentTag() {
        return getClass().getSimpleName();
    }

    protected abstract BaseFragment getConcreteFragment();

    public final void gotoState(BaseState state) {
        state.setReceiver(mReceiver);
        mReceiver.changeState(state);
    }
}
