package mejorandome.mejorandome;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mejorandome.mejorandome.adapters.Pager;
import mejorandome.mejorandome.fragments.DashboardFragment;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, DashboardFragment.OnFragmentInteractionListener {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view1;
    private View view2;
    private View view3;
    private View view4;
    private View view5;
    private int dni;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        dni = getIntent().getIntExtra("dni",0);

        view1 = getLayoutInflater().inflate(R.layout.fragment_icons_design, null);
        view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.dashboard_icon);

        view2 = getLayoutInflater().inflate(R.layout.fragment_icons_design, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.activities_icon);

        view3 = getLayoutInflater().inflate(R.layout.fragment_sos_icon, null);
        view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.sos_icon);

        view4 = getLayoutInflater().inflate(R.layout.fragment_icons_design, null);
        view4.findViewById(R.id.icon).setBackgroundResource(R.drawable.medicine_icon);

        view5 = getLayoutInflater().inflate(R.layout.fragment_icons_design, null);
        view5.findViewById(R.id.icon).setBackgroundResource(R.drawable.settings_icon);

        tabLayout.addTab(tabLayout.newTab().setCustomView(view1));
        tabLayout.addTab(tabLayout.newTab().setCustomView(view2));
        tabLayout.addTab(tabLayout.newTab().setCustomView(view3));
        tabLayout.addTab(tabLayout.newTab().setCustomView(view4));
        tabLayout.addTab(tabLayout.newTab().setCustomView(view5));

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        viewPager = (ViewPager) findViewById(R.id.pager);

        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        tabLayout.setOnTabSelectedListener(this);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
