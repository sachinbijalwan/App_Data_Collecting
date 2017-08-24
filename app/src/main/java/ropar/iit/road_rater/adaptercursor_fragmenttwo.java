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

public class adaptercursor_fragmenttwo extends CursorAdapter {
    private Context mcontext;
    private Cursor mcursor;
    public adaptercursor_fragmenttwo(Context context, Cursor cursor)
    {
        super(context,cursor,0);
        mcontext=context;
        mcursor=cursor;


    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(mcontext).inflate(R.layout.fragment_two_helper, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView xaxis = (TextView) view.findViewById(R.id.xaxis);
        TextView yaxis = (TextView) view.findViewById(R.id.yaxis);
        TextView zaxis = (TextView) view.findViewById(R.id.zaxis);
        TextView time = (TextView) view.findViewById(R.id.time_fragment_two);
        // Extract properties from cursor
        String xaxis1 = cursor.getString(cursor.getColumnIndexOrThrow("XAXIS"));
        String yaxis1 = cursor.getString(cursor.getColumnIndexOrThrow("YAXIS"));
        String zaxis1 = cursor.getString(cursor.getColumnIndexOrThrow("ZAXIS"));
        String time1 = cursor.getString(cursor.getColumnIndexOrThrow("TIME"));
        // Populate fields with extracted properties
        xaxis.setText(xaxis1);
        yaxis.setText(yaxis1);
        zaxis.setText(zaxis1);
        time.setText(time1);
        }
}
