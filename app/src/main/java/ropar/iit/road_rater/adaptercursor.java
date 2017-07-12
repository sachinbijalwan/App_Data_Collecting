package ropar.iit.road_rater;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sachin on 05/07/17.
 */

public class adaptercursor extends CursorAdapter {
    private Context mcontext;
    private Cursor mcursor;
    public adaptercursor(Context context,Cursor cursor)
    {
        super(context,cursor,0);
        mcontext=context;
        mcursor=cursor;
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(mcontext).inflate(R.layout.inflator, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvBody = (TextView) view.findViewById(R.id.video_name);
        // Extract properties from cursor
        String body = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
        // Populate fields with extracted properties
        tvBody.setText(body);
        }
}
