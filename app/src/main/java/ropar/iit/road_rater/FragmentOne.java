package ropar.iit.road_rater;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by sachin on 05/07/17.
 */

public class FragmentOne extends Fragment {
    private ListView listView;
    private adaptercursor_fragmentone adapter;
    String text1;
    DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        text1=getArguments().getString("name");
        View rootView = inflater.inflate(R.layout.fragment_one, null);
        db=new DatabaseHelper(getActivity());
        getAllWidgets(rootView);
        setAdapter();

        return rootView;
    }

    private void getAllWidgets(View view) {
        listView = (ListView) view.findViewById(R.id.listFragmentOne);


    }
    private void setAdapter(){
      //  DatabaseHelper handler=new DatabaseHelper(getActivity());
        db.AddGpsValue(text1);

        // SQLiteDatabase db=handler.getWritableDatabase();
        Log.d("fragmentone","text1 is "+text1);
        Cursor cursor=db.getAllData(text1);
        adapter=new adaptercursor_fragmentone(getActivity(),cursor);
        listView.setAdapter(adapter);
    }

}