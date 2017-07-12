package ropar.iit.road_rater;

import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class VIDEO_DETAILS extends AppCompatActivity {
    TabLayout allTabs;
    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;
    String name;
    //DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video__details);
         Bundle bundle=getIntent().getExtras();                                 //defined below
        name=bundle.getString("name");
     //   db=new DatabaseHelper(this);
        getAllWidgets();                                    //function defined below
        bindWidgetsWithAnEvent();                           //defined below
        setupTabLayout();
       /* TabLayout.Tab currentTab = allTabs.getTabAt(0);
        if (currentTab != null) {
            View customView = currentTab.getCustomView();
            if (customView != null) {
                customView.setSelected(true);
            }
            currentTab.select();
        }*/
    }

    private void getAllWidgets() {
        allTabs = (TabLayout) findViewById(R.id.frame_tab);
    }

    private void bindWidgetsWithAnEvent() {
        allTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });
    }

    private void setCurrentTabFragment(int tabPosition) {
        switch (tabPosition) {
            case 0:
                replaceFragment(fragmentOne);
                break;
            default:
                replaceFragment(fragmentTwo);

                break;
        }
    }
    public void replaceFragment(android.support.v4.app.Fragment fragment) {

        Bundle bundle = new Bundle();
        bundle.putString("name", name);
// set Fragmentclass Arguments
        fragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private void setupTabLayout() {
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();

    }
}

