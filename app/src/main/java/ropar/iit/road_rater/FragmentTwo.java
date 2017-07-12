package ropar.iit.road_rater;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by sachin on 05/07/17.
 */

public class FragmentTwo extends Fragment {
    private ListView listView;
    private adaptercursor_fragmenttwo adapter;
    String text1;
    DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        text1=getArguments().getString("name");
        View rootView = inflater.inflate(R.layout.fragment_two, null);
        db=new DatabaseHelper(getActivity());
        getAllWidgets(rootView);
        setAdapter();


        return rootView;
    }

    private void getAllWidgets(View view) {
        listView = (ListView) view.findViewById(R.id.listFragmentTwo);


    }

    private void setAdapter(){
       // DatabaseHelper handler=new DatabaseHelper(getActivity());
        db.AddAccelerometerValue(text1 + "1");
        //  MainActivity mainActivity=new MainActivity();
       // Toast.makeText(getActivity(),"THIS IS THE TEXT"+mainActivity.text1+"1",Toast.LENGTH_LONG).show();
        Cursor cursor=db.getAllData(text1+"1");
        adapter=new adaptercursor_fragmenttwo(getActivity(),cursor);
        listView.setAdapter(adapter);
    }
}
