package ropar.iit.road_rater;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private adaptercursor adapter;
    public CharSequence text1;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main);
        db=new DatabaseHelper(this);
        setToolbar();
        setListView();
    }
    private void setToolbar()
    {

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);    //toolbar created
        setSupportActionBar(myToolbar);                                 //setups toolbar

    }

    public void camerastarts(View view){
        Intent myIntent = new Intent(MainActivity.this, CameraActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
    private void setListView(){
     //   DatabaseHelper handler=new DatabaseHelper(this);
       // SQLiteDatabase db=handler.getWritableDatabase();
        Cursor cursor=db.getAllData();
        if(cursor!=null) {
            ListView lvitems = (ListView) findViewById(R.id.video_list);
            adapter = new adaptercursor(this, cursor);
            lvitems.setAdapter(adapter);
        }
    }
    public void text_click(View view){
        TextView text=(TextView) view;
        text1=text.getText();
        Intent myIntent = new Intent(MainActivity.this, VIDEO_DETAILS.class);
        Bundle bundle=new Bundle();
        bundle.putString("name", (String) text1);
        myIntent.putExtras(bundle);
        MainActivity.this.startActivity(myIntent);
       // String a=(String) text1;
       // Toast.makeText(this,a,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListView();
    }
}
