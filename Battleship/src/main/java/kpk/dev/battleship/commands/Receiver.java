package kpk.dev.battleship.commands;

import kpk.dev.battleship.states.BaseState;

/**
 * Created by krasimir.karamazov on 2/3/14.
 */
public interface Receiver {
    public void changeState(BaseState state);
}
