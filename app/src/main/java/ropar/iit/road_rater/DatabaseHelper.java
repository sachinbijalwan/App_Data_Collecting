package ropar.iit.road_rater;

/**
 * Created by 2015csb1027 on 12/4/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.icu.text.NumberFormat;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.File;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "VIDEO.db";
    public static final String TABLE_NAME = "VIDEO_NAMES";
    public static final String COL_1 = "rowid";
    public static final String COL_2 = "NAME";
    public static final String GPS_COL_1="LATTITUDE";
    public static final String GPS_COL_2="LONGITUDE";
    public static final String GPS_COL_3="TIME";
    //ACCELEROMETER COLUMNE VARIABLES
    public static final String ACCELEROMETER_COL_1="XAXIS";
    public static final String ACCELEROMETER_COL_2="YAXIS";
    public static final String ACCELEROMETER_COL_3="ZAXIS";
    public static final String ACCELEROMETER_COL_4="TIME";
    private Context context_of_class;

    public static final int DATABASE_VERSION=1;
    //NAME OF VIDEO TO BE INSERTED IN TABLE
    String entry_name;
    //NAME OF VIDEO PREFIX
    String video_name="video_road";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        entry_name=video_name+getmaxid();   //NAME OF VIDEO DECIDED EVERYTIME DATABASEHELPER IS CALLED
        context_of_class=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // Log.d("Block:","ONCREATE CALLED");
        db.execSQL("create table " + TABLE_NAME +" ("/*+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"*/+COL_2+" TEXT UNIQUE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      //  db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT UNIQUE)");
        //db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        //onCreate(db);
        //for(int i=0;i<1000;i++)
        //Log.d("BACKGROUND","DATABASE IS UPGRADED");
    }
    public static boolean CheckIsDataAlreadyInDBorNot(SQLiteDatabase db,String dbfield, String fieldValue) {
        String Query = "Select * from " + TABLE_NAME + " where " + dbfield + " = " + fieldValue;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public void AddDesiredGpsTable(String TableNmae){
    /*At first you will need a Database object.Lets create it.*/
        SQLiteDatabase ourDatabase=this.getWritableDatabase();

    /*then call 'execSQL()' on it. Don't forget about using TableName Variable as tablename.*/
        ourDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TableNmae+
                        " (ID INTEGER PRIMARY KEY AUTOINCREMENT,"+GPS_COL_1+
                " TEXT NOT NULL,"+GPS_COL_2+" TEXT NOT NULL,"+GPS_COL_3+
                " TEXT NOT NULL)");
    }
    public void AddGpsValue(String TableName){
        AddDesiredGpsTable(TableName);
        if(isMasterEmpty(TableName))
        {
            insertgpsData(TableName,111,111,21424);
        }
    }
    public void AddAccelerometerValue(String TableName){
        AddDesiredAccelerometerTable(TableName);
        if(isMasterEmpty(TableName))
        {
            insertaccelerometerData(TableName,1,1,1,1);
        }
    }
    public void AddDesiredAccelerometerTable(String TableNmae){
    /*At first you will need a Database object.Lets create it.*/
        SQLiteDatabase ourDatabase=this.getWritableDatabase();

    /*then call 'execSQL()' on it. Don't forget about using TableName Variable as tablename.*/
        ourDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TableNmae+
                " ("+ACCELEROMETER_COL_1+
                " TEXT NOT NULL,"+ACCELEROMETER_COL_2+" TEXT NOT NULL,"
                        +ACCELEROMETER_COL_3+" TEXT NOT NULL,"+ACCELEROMETER_COL_4+
                            " TEXT NOT NULL)");
    }

    public boolean insertaccelerometerData(String TableName, double xaxis, double yaxis, double zaxis, long Time) {
        // String user_id=///;
        //user_id="sachin bijalwan";
        //number="232313";

        NumberFormat nf=NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);

        double time=Time/1000;
        Log.d("TIME","TIME IS"+time);
     //   time/=1000.0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.clear();
        contentValues.put(ACCELEROMETER_COL_1,xaxis);
        contentValues.put(ACCELEROMETER_COL_2,yaxis);
        contentValues.put(ACCELEROMETER_COL_3,zaxis);
        contentValues.put(ACCELEROMETER_COL_4,Time);
        long result=-1;
        {
            result = db.insert(TableName,null ,contentValues);
        }
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertgpsData(String TableName, double longitude, double lattitude, long Time) {
        // String user_id=///;
        //user_id="sachin bijalwan";
        //number="232313";
        NumberFormat nf=NumberFormat.getInstance();
       // nf.setMaximumFractionDigits(4);
        //double time=Time/1000;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GPS_COL_1,lattitude);
        contentValues.put(GPS_COL_2,longitude);
        contentValues.put(GPS_COL_3,Time);
        long result=-1;
        {
            result = db.insert(TableName,null ,contentValues);
        }
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertData() {
        // String user_id=///;
        //user_id="sachin bijalwan";
        //number="232313";
        String name_of_video=entry_name;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name_of_video);
        long result=-1;
        // contentValues.put(COL_3,number);
        //if(!CheckIsDataAlreadyInDBorNot(db,"NAME",name_of_video))
        {
            result = db.insert(TABLE_NAME,null ,contentValues);
        }
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select rowid _id,* from "+TABLE_NAME,null);
        return res;
    }
    public Cursor getAllData(String Tablename) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select rowid _id,* from "+Tablename,null);
        return res;
    }

    public boolean  isMasterEmpty(String TABLE_MASTERS) {

        boolean flag;
        String quString = "select exists(select 1 from " + TABLE_MASTERS  + ");";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(quString, null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        if (count ==1) {
            flag =  false;
        } else {
            flag = true;
        }
        cursor.close();
        db.close();

        return flag;
    }

    /*public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }*/
    String getmaxid()
    {
        SQLiteDatabase db=this.getWritableDatabase();
                Cursor s = db.rawQuery("SELECT rowid ID FROM " + TABLE_NAME, null);
                if(s.getCount()!=0)
                {
                   /* Log.d("application","starting of getstring");
                    Log.d("application",""+s.getColumnNames()[0]+s.getColumnIndex("MAX(ID)"));
                    String t = s.getString(0);
                    Log.d("application","ending of getstring");
                    */
                    final SQLiteStatement stmt = db
                            .compileStatement("SELECT MAX(rowid) FROM "+TABLE_NAME);
                    int t=(int) stmt.simpleQueryForLong();
                    Log.d("getmaxid",Integer.toString(t));
                    return Integer.toString(t);
            }
            else
                return "0";
    }


    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
    public String findview(int id)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor s=db.rawQuery("select name from "+TABLE_NAME+"where (ID=="+id+")",null);
        String p=s.getString(0);
        return p;
    }


}

