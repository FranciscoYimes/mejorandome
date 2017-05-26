package mejorandome.mejorandome.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import mejorandome.mejorandome.fragments.ActivitiesFragment;
import mejorandome.mejorandome.fragments.DashboardFragment;
import mejorandome.mejorandome.fragments.MainFragment;
import mejorandome.mejorandome.fragments.SettingsFragment;
import mejorandome.mejorandome.fragments.SosFragment;

/**
 * Created by franciscoyimesinostroza on 22-05-17.
 */

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                DashboardFragment tab1 = new DashboardFragment();
                return tab1;
            case 1:
                ActivitiesFragment tab2 = new ActivitiesFragment();
                return tab2;
            case 2:
                SosFragment tab3 = new SosFragment();
                return tab3;
            case 3:
                MainFragment tab4 = new MainFragment();
                return tab4;
            case 4:
                SettingsFragment tab5 = new SettingsFragment();
                return tab5;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}