package kpk.dev.battleship.ui.activities;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kpk.dev.battleship.R;
import kpk.dev.battleship.ui.navigation.ActionBarNavigationModel;

/**
 * Created by krasimir.karamazov on 2/4/14.
 */
public abstract class ActionBarListActivity extends BaseActionBarActivity {

    @Override
    protected int getNavigationMode() {
        return ActionBar.NAVIGATION_MODE_LIST;
    }

    @Override
    protected void setupNavigation() {
        getSupportActionBar().setListNavigationCallbacks(new NavigationAdapter(this, android.R.layout.simple_spinner_dropdown_item, mMainNavigationList), getNavigationListener());
    }

    @Override
    protected int getLastFragmentTag() {
        int tag = mMainNavigationList.get(getSupportActionBar().getSelectedNavigationIndex()).getTag();
        return tag;
    }

    private ActionBar.OnNavigationListener getNavigationListener() {
        return new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ActionBarListActivity.this.onNavigationItemSelected(mMainNavigationList.get(itemPosition).getTag(), ft);
                ft.commit();
                return true;
            }
        };
    }

    private class NavigationAdapter extends ArrayAdapter<ActionBarNavigationModel>{
        public NavigationAdapter(Context context, int textViewResourceId, List<ActionBarNavigationModel> objects) {
            super(context, textViewResourceId, objects);
        }

        private View getCustomView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if(row == null){
                LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.layout_list_nav_item, parent, false);
            }
            TextView tv = (TextView) row.findViewById(R.id.tv_nav_item);
            ImageView iv = (ImageView) row.findViewById(R.id.iv_nav_item);
            tv.setText(getItem(position).getTitle());
            if(getItem(position).getIconId() > -1){
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(getItem(position).getIconId());
            }else{
                iv.setVisibility(View.GONE);
            }

            return row;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }
    }
}
