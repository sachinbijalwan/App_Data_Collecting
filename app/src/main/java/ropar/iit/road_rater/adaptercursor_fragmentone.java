package ropar.iit.road_rater;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sachin on 05/07/17.
 */

public class adaptercursor_fragmentone extends CursorAdapter {
    private Context mcontext;
    private Cursor mcursor;
    public adaptercursor_fragmentone(Context context, Cursor cursor)
    {
        super(context,cursor,0);
        mcontext=context;
        mcursor=cursor;
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(mcontext).inflate(R.layout.fragment_one_helper, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView lattitude = (TextView) view.findViewById(R.id.lattitude);
        TextView longitude = (TextView) view.findViewById(R.id.longitude);
        TextView time = (TextView) view.findViewById(R.id.time_fragment_one);
        // Extract properties from cursor
        String latitude1 = cursor.getString(cursor.getColumnIndexOrThrow("LATTITUDE"));
        String longitude1 = cursor.getString(cursor.getColumnIndexOrThrow("LONGITUDE"));
        String time1 = cursor.getString(cursor.getColumnIndexOrThrow("TIME"));
        // Populate fields with extracted properties
        lattitude.setText(latitude1);
        longitude.setText(longitude1);
        time.setText(time1);
        }
}
