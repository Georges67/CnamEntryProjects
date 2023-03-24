package cnam.com.myfirstgpsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyGpsPathDbHelper extends SQLiteOpenHelper {


    private static final String LOG_TAG = "MyGpsPathDbHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "GpsPath";


    private static final String COLUMN_ID = "id";
    private static final int COLUMN_ID_POSITION = 0;

    // Table name: PATHLIST
    private static final String TABLE_PATH_LIST = "PATHLIST";

    private static final String COLUMN_PATHLIST_NAME = "firstname";

    private static final int COLUMN_PATHLIST_NAME_POSITION = 1;

    // Table name: PATHLIST
    public static final String TABLE_PATH_POINTS = "PATHPOINTS";

    private static final String COLUMN_PATHPOINTS_LATITUDE = "latitude";
    private static final String COLUMN_PATHPOINTS_LONGITUDE = "longitude";

    private static final int COLUMN_PATHPOINTS_LATITUDE_POSITION = 1;
    private static final int COLUMN_PATHPOINTS_LONGITUDE_POSITION = 2;




    public MyGpsPathDbHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.clearUnusedTablePoint();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // Script to create table.
        String script = "CREATE TABLE " + TABLE_PATH_LIST + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PATHLIST_NAME + " TEXT)";
        // Execute Script.
        db.execSQL(script);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATH_LIST);

        // Recreate
        onCreate(db);
    }


    public long addPathEntry(String name) {
        Log.i(LOG_TAG, ">addPathEntry" + name);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PATHLIST_NAME, name);

        // Inserting Row
        long rowId = db.insert(TABLE_PATH_LIST, null, values);

        // Closing database connection
        db.close();

        return rowId;
    }


    public GpsPath getPathEntry(long id) {
        Log.i(LOG_TAG, ">GpsPath" + id);

        SQLiteDatabase db = this.getReadableDatabase();

        // TODO 18) fill method of DB request
        return null;
    }


    public List<GpsPath> getAllPath() {
        Log.i(LOG_TAG, "getAllPath");

        // TODO 19) fill method of DB request
        return null;
    }

    public void refreshPath(GpsPath path) {
        List<GpsPoint> gpsPointList = this.getAllGpsPoint(path.getGpsPointTableName());
        if (gpsPointList != null) path.setData(gpsPointList);
    }

    public int getPathCount() {
        Log.i(LOG_TAG, ">getPathCount");

        // TODO 20) fill method of DB request
        return 0;
    }


    public void deletePath(GpsPath path) {
        Log.i(LOG_TAG, ">deletePath");

        // TODO 21) fill method of DB request
    }


    ////////////////////////////////////////////////////
    ////
    ////       Delete unused TablePoint TABLES
    ////
    ////////////////////////////////////////////////////

    private void clearUnusedTablePoint() {
        Log.v(LOG_TAG, "=============================");
        List<GpsPath> gpsPathList = this.getAllPath();
        List<String> tablePointListName = this.getAllTablePointListName();

        for(String tablePointName : tablePointListName) {
            if(this.isPathTableIfAvailable(tablePointName, gpsPathList)) {
                Log.v(LOG_TAG, "Table: "+ tablePointName + " exists");
            } else {
                Log.v(LOG_TAG, "Table: "+ tablePointName + " not FOUND => DELETE");
                this.deleteTableByName(tablePointName);
            }
        }
    }

    private boolean isPathTableIfAvailable(String tableName, List<GpsPath> gpsPathList) {
        for(GpsPath gpsPath : gpsPathList) {
            if (gpsPath.getGpsPointTableName().equals(tableName)) return true;
        }
        return false;
    }

    private List<String> getAllTablePointListName() {
        Log.v(LOG_TAG, "=============================");

        List<String> allTablePointListName = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (cursor.moveToFirst()) {
            do {
                String tableName = cursor.getString(0);
                if(tableName.startsWith(TABLE_PATH_POINTS)) {
                    allTablePointListName.add(tableName);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return allTablePointListName;
    }

    public void deleteTableByName(String tableName) {
        Log.i(LOG_TAG, ">deleteTableByName");

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + tableName);

        db.close();
    }

    ////////////////////////////////////////////////////
    ////
    ////       Manage Second level of TABLES
    ////
    ////////////////////////////////////////////////////

    public void createTablePoint(String tableName) {

        SQLiteDatabase db = this.getWritableDatabase();
        // Script to create table.
        String script = "CREATE TABLE " + tableName + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PATHPOINTS_LATITUDE + " TEXT,"
                + COLUMN_PATHPOINTS_LONGITUDE + " TEXT)";
        // Execute Script.
        db.execSQL(script);
    }

    public void addPathGpsPoint(String tableName, GpsPoint gpsPoint) {
        Log.i(LOG_TAG, ">addPathGpsPoint");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PATHPOINTS_LATITUDE, gpsPoint.latitude);
        values.put(COLUMN_PATHPOINTS_LONGITUDE, gpsPoint.longitude);

        // Inserting Row
        long rowId = db.insert(tableName, null, values);


        // Closing database connection
        db.close();
    }


    public GpsPoint getGpsPoint(String tableName, int id) {
        Log.i(LOG_TAG, ">getGpsPoint" + tableName);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tableName, new String[] { COLUMN_ID,
                        COLUMN_PATHPOINTS_LATITUDE, COLUMN_PATHPOINTS_LONGITUDE },
                COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        GpsPoint gpsPoint = new GpsPoint(Double.parseDouble(cursor.getString(COLUMN_PATHPOINTS_LATITUDE_POSITION)),
                Double.parseDouble(cursor.getString(COLUMN_PATHPOINTS_LONGITUDE_POSITION)));
        return gpsPoint;
    }


    public List<GpsPoint> getAllGpsPoint(String tableName) {
        Log.i(LOG_TAG, "getAllGpsPoint");

        List<GpsPoint> gpsPath = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + tableName;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GpsPoint gpsPoint = new GpsPoint(Double.parseDouble(cursor.getString(COLUMN_PATHPOINTS_LATITUDE_POSITION)),
                        Double.parseDouble(cursor.getString(COLUMN_PATHPOINTS_LONGITUDE_POSITION)));
                // Adding note to list
                gpsPath.add(gpsPoint);
            } while (cursor.moveToNext());
        }

        // return note list
        return gpsPath;
    }

    public int getGpsPointCount(String tableName) {
        Log.i(LOG_TAG, ">getGpsPointCount: " + tableName);

        String countQuery = "SELECT  * FROM " + tableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

}