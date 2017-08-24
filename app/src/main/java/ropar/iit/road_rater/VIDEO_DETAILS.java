package ropar.iit.road_rater;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class VIDEO_DETAILS extends AppCompatActivity {
    TabLayout allTabs;
    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;
    String name;
    public static final String GPS_COL_1="LATTITUDE";
    public static final String GPS_COL_2="LONGITUDE";
    public static final String GPS_COL_3="TIME";
    //ACCELEROMETER COLUMNE VARIABLES
    public static final String ACCELEROMETER_COL_1="XAXIS";
    public static final String ACCELEROMETER_COL_2="YAXIS";
    public static final String ACCELEROMETER_COL_3="ZAXIS";
    public static final String ACCELEROMETER_COL_4="TIME";
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
    public void convertToExcel(View view) throws IOException {

        DatabaseHelper db=new DatabaseHelper(this);
        Cursor c=db.getAllData(name);
        amazing(c,name+"gp");
        c=db.getAllData(name+"1");
        amazing(c,name+"ac");
    }

    private void amazing(Cursor c,String filename) throws IOException
    {
        //c = sqldb.rawQuery("select * from places", null);
        int rowcount = 0;
        int colcount = 0;
        File sdCardDir = this.getExternalFilesDir(null);;
        filename = filename+".csv";
        // the name of the file to export with
        File saveFile = new File(sdCardDir, filename);
        FileWriter fw = new FileWriter(saveFile);

        BufferedWriter bw = new BufferedWriter(fw);
        rowcount = c.getCount();
        colcount = c.getColumnCount();
        try{
        if (rowcount > 0) {
            c.moveToFirst();

            for (int i = 0; i < colcount; i++) {
                if (i != colcount - 1) {

                    bw.write(c.getColumnName(i) + ",");

                } else {

                    bw.write(c.getColumnName(i));

                }
            }
            bw.newLine();

            for (int i = 0; i < rowcount; i++) {
                c.moveToPosition(i);

                for (int j = 0; j < colcount; j++) {
                    if (j != colcount - 1)
                        bw.write(c.getString(j) + ",");
                    else
                        bw.write(c.getString(j));
                }
                bw.newLine();
            }
            bw.flush();
         //   infotext.setText("Exported Successfully.");
        }
    } catch (Exception ex) {
        /*if (sqldb.isOpen()) {
            sqldb.close();
            infotext.setText(ex.getMessage().toString());
        }*/
        ex.printStackTrace();

    } finally {

    }
    }
}



