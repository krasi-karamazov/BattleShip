package kpk.dev.battleship.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import java.util.*;
import kpk.dev.battleship.commands.Receiver;
import kpk.dev.battleship.states.BaseState;
import kpk.dev.battleship.ui.fragments.BaseFragment;
import kpk.dev.battleship.ui.navigation.ActionBarNavigationModel;

public abstract class BaseActionBarActivity extends ActionBarActivity implements Receiver{

    private static final String SELECTED_POSITION_KEY = "SELECTED_POSITION_KEY";
    protected List<ActionBarNavigationModel> mMainNavigationList;
    protected HashMap<Integer, Stack<String>> mBackStacks;
    protected Stack<String> mSelectedBackStack;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        int savedPosition = 0;
        mMainNavigationList = getMainNavigationItems();
        if (savedInstanceState != null) {
            mBackStacks = (HashMap<Integer, Stack<String>>) savedInstanceState.getSerializable("stacks");
            savedPosition = savedInstanceState.getInt(SELECTED_POSITION_KEY, 0);
        }
        else{
            mBackStacks = new HashMap<Integer, Stack<String>>();
            for(ActionBarNavigationModel model : mMainNavigationList) {
                mBackStacks.put(model.getTag(), new Stack<String>());
            }
        }
        configureActionBar();
        if(savedPosition > 0) {
            if(getSupportActionBar().getNavigationMode() != ActionBar.NAVIGATION_MODE_STANDARD) {
                getSupportActionBar().setSelectedNavigationItem(savedPosition);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int selectedNavIndex = getSupportActionBar().getSelectedNavigationIndex();
        outState.putSerializable("stacks", mBackStacks);
        outState.putSerializable(SELECTED_POSITION_KEY, selectedNavIndex);
    }

    @Override
    protected final void onResume() {
        super.onResume();
        try{
            Stack<String> backStack = mBackStacks.get(getLastFragmentTag());
            if (! backStack.isEmpty()){
                String tag = backStack.peek();
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment.isDetached()) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.attach(fragment);
                    ft.commit();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected final void onPause() {
        super.onPause();
        Stack<String> backStack = mBackStacks.get(getLastFragmentTag());
        if (! backStack.isEmpty()) {
            String tag = backStack.peek();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
            ft.detach(fragment);
            ft.commit();
        }
    }

    protected final void onNavigationItemSelected(int navItemTag, FragmentTransaction ft){
        try{
            Stack<String> backStack = mBackStacks.get(navItemTag);
            if(mSelectedBackStack != null){
                if(!mSelectedBackStack.equals(backStack)){

                    while (mSelectedBackStack.size() > 1){
                        String tag = mSelectedBackStack.pop();
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
                        ft.remove(fragment);
                    }
                }
            }

            if (backStack.isEmpty()) {
                BaseFragment fragment = getSelectedNavItemByTag(navItemTag);
                addFragment(fragment, backStack, ft);
            }
            else{
                showFragment(backStack, ft);
            }
            mSelectedBackStack = backStack;
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    protected final void onNavigationItemReselected(int navItemTag, FragmentTransaction ft){
        Stack<String> backStack = mBackStacks.get(navItemTag);

        while (backStack.size() > 1){
            String tag = backStack.pop();
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
            ft.remove(fragment);
        }
        showFragment(backStack, ft);
    }

    protected final void onNavigationItemUnselected(int navItemTag, FragmentTransaction ft){
        Stack<String> backStack = mBackStacks.get(navItemTag);
        String tag = backStack.peek();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        ft.detach(fragment);
    }

    public void showFragment(Stack<String> backStack, FragmentTransaction ft) {
        String tag = backStack.peek();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        ft.attach(fragment);
    }

    private final void configureActionBar(){
        setupNavigation();
        getSupportActionBar().setNavigationMode(getNavigationMode());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    protected final BaseFragment getSelectedNavItemByTag(int tag) {
        BaseFragment fragment = null;
        for(ActionBarNavigationModel model : mMainNavigationList) {
            if(model.getTag() == tag){
                fragment = model.getState().getFragment();
            }
        }
        return fragment;
    }

    private void addFragment(Fragment fragment) {
        ActionBar.Tab tab = getSupportActionBar().getSelectedTab();
        Stack<String> backStack = mBackStacks.get(tab.getTag());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        String tag = backStack.peek();
        Fragment top = getSupportFragmentManager().findFragmentByTag(tag);
        if(top != null){
            ft.detach(top);
        }
        addFragment(fragment, backStack, ft);
        ft.commit();
    }

    protected final void addFragment(Fragment fragment, Stack<String> backStack, FragmentTransaction ft) {
        String tag = UUID.randomUUID().toString();
        ft.add(getMainFragmentContainer(), fragment, tag);
        backStack.push(tag);
    }

    @Override
    public void changeState(BaseState state) {
        addFragment(state.getFragment());
    }

    protected abstract List<ActionBarNavigationModel> getMainNavigationItems();
    protected abstract int getContentView();
    protected abstract int getNavigationMode();
    protected abstract int getMainFragmentContainer();
    protected abstract void setupNavigation();
    protected abstract int getLastFragmentTag();
}