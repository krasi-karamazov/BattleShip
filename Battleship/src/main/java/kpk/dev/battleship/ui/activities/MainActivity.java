package kpk.dev.battleship.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Stack;

import kpk.dev.battleship.R;
import kpk.dev.battleship.commands.Receiver;
import kpk.dev.battleship.states.BaseState;
import kpk.dev.battleship.states.IntroState;
import kpk.dev.battleship.ui.fragments.MainFragment;

public class MainActivity extends FragmentActivity implements Receiver {

    private BaseState mState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayState(new IntroState(this));
    }

    @Override
    public void onBackPressed() {
        if(mState == null || mState.getPrevState() == null) {
            super.onBackPressed();
        }else{
            displayState(mState.getPrevState());
        }
    }

    private void displayState(BaseState state) {

        final Fragment fr = state.getFragment();
        if(fr != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fr, state.getFragmentTag()).commit();
        }
        if(mState != null){
            state.setPrevState(mState);
        }
        mState = state;
    }

    @Override
    public void changeState(BaseState state) {
        displayState(state);
    }
}