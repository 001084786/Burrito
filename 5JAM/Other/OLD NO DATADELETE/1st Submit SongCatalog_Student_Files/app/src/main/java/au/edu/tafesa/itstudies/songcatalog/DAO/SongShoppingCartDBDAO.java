package au.edu.tafesa.itstudies.songcatalog.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.edu.tafesa.itstudies.songcatalog.models.Song;
import au.edu.tafesa.itstudies.songcatalog.models.SongShoppingCartModel;

/**
 * Created by sruiz on 26/03/2018.
 * Update 26/05/2018 - tidy up for release
 * Implements an set of Data Access Objects based on using a SQLite database
 * This is a Singleton class - only one object is evr created and the user doesnot create that
 * access to the single object is via the connectToSongDB static method.
 */

public class SongShoppingCartDBDAO {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Songs.db";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_LENGTH = "length";
    public static final String COLUMN_SONG_SELECTED = "selected";

    public static final String TABLE_SONGS = "tblSongs";

    public static final String SQL_CREATE_TBL_SONGS =
            " CREATE TABLE IF NOT EXISTS " + TABLE_SONGS +
                    " (" + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
                    COLUMN_SONG_TITLE + " TEXT, " +
                    COLUMN_SONG_SELECTED + " INT, " +
                    COLUMN_SONG_LENGTH + " NUMBER)";

    private SQLiteDatabase database;
    private SongSQLiteOpenHelper dbHelper;
    private static SongShoppingCartDBDAO singleSongDBObject = null;

    private SongShoppingCartDBDAO(Context context) {
        long start, end;
        start = System.currentTimeMillis();
        dbHelper = new SongSQLiteOpenHelper(context);
        end = System.currentTimeMillis();
        Log.i("connecting", "-> Time taken (ms) =" + (end - start));
        open();
//        database.beginTransaction();
    }

    public static SongShoppingCartDBDAO ConnectToSongDB(Context context) {
        if (singleSongDBObject == null) {
            singleSongDBObject = new SongShoppingCartDBDAO(context);
        }
        singleSongDBObject.open();
        return singleSongDBObject;
    }

    public void open() throws SQLException {
        long start, end;
        start = System.currentTimeMillis();
        if (database == null) {
            database = dbHelper.getWritableDatabase();
        }
        if (!database.isOpen()) {
            database = dbHelper.getWritableDatabase();
        }

        end = System.currentTimeMillis();
        Log.i("open", "-> Time taken (ms) =" + (end - start));

    }

    public void close() {
        long start, end;
        start = System.currentTimeMillis();
        dbHelper.close();
        end = System.currentTimeMillis();
        Log.i("close", "-> Time taken (ms) =" + (end - start));
    }

    /**
     * @param modelRead The model that will be filled with the Songs read from the database
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadFromDB(SongShoppingCartModel modelRead) throws IOException, ClassNotFoundException {
        String table;
        String columns[] = new String[4];
        List<Song> songsFromDB;
        Cursor cursor;
        Song b;

        this.open();

        //Use the class constants to set up the table name and the columns for the query
        table = TABLE_SONGS;
        columns[0] = COLUMN_ID;
        columns[1] = COLUMN_SONG_TITLE;
        columns[2] = COLUMN_SONG_LENGTH;
        columns[3] = COLUMN_SONG_SELECTED;

        songsFromDB = new ArrayList<Song>();
        //query the database retrieving all songs and then traversing the cursor and adding the
        // songs to the songsFromDB List
        cursor = database.query(table, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            b = new Song(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_SONG_TITLE)),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_SONG_LENGTH)),
                    (cursor.getInt(cursor.getColumnIndex(COLUMN_SONG_SELECTED)) == 0 ? false : true)
            );
            Log.i("LoadFromDB", "Loading Song ->" + b);
            songsFromDB.add(b);
            cursor.moveToNext();
        }
        //Set the models catalog List to be the songsFromDB list
        modelRead.setCatalog(songsFromDB);
        cursor.close();
    }

    /**
     * Performs a database update using the provided model. Updates existing records and
     * inserts any new ones.
     *
     * @param songShoppingCartModel the model to be saved to the database
     * @return The number of new records added to the database
     * @throws IOException
     */
    public int saveToDB(SongShoppingCartModel songShoppingCartModel) throws IOException {
        ContentValues values = new ContentValues();
        int numInserted = 0;
        String whereValues[] = new String[1];
        String where;

        Log.i("SaveToDB", "is db open" + database.isOpen());
        this.open();
        // Insert all of the new songs in the list with id as -1,
        // indicating that they did not come from the
        // DB ie are new
        // and update the data in the DB when the song object has an ID (!=-1)
        for (Song b : songShoppingCartModel.getCatalog()) {
            values.clear();
            values.put(COLUMN_SONG_TITLE, b.getTitle());
            values.put(COLUMN_SONG_LENGTH, b.getLength());
            values.put(COLUMN_SONG_SELECTED, b.isSelected());
            if (b.getId() == Song.DEFAULT_ID) {
                //Perform the insert into the DB
                database.insert(TABLE_SONGS, null, values);
                numInserted++;
                Log.i("SaveToDB - insert", "Saving Song ->" + b);
            } else {
                //Perform the update of the record in the DB
                where = COLUMN_ID + "=?";
                whereValues[0] = "" + b.getId();
                database.update(TABLE_SONGS, values, where, whereValues);
                Log.i("SaveToDB - update", "Updating Song ->" + b);
            }
        }

        return numInserted;

    }

    /**
     * deletes all songs in the DB with a length > 300 secs
     *
     * @return The number of songs deleted
     */
    public long deleteLongSongs() {
        String table = TABLE_SONGS;
        String where = COLUMN_SONG_LENGTH;
        String theValue = "300";
        long deleteCount = 0;

        Cursor cursor;

        this.open();
        cursor = database.rawQuery("DELETE * FROM " + table + " WHERE " + where + " < " + theValue + ";");
        this.close();


        deleteCount = cursor.getCount();
        return deleteCount;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    /**
     * Helps to setup the database
     */
    public class SongSQLiteOpenHelper extends SQLiteOpenHelper {

        public static final String CLASS_TAG = "SongSQLiteOpenHelper";


        public SongSQLiteOpenHelper(Context context) {
            //Call super class constructor to get the database created
            //TODO
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("onCreate", "db = " + db);
            //Create the necessary tables
            db.execSQL(SQL_CREATE_TBL_SONGS);
            //Load some dummy data
            saveDummyDataToDB(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("onUpgrade", "Upgrade old and new = " + oldVersion + "," + newVersion);
            // If there is a version change - we are going to keep it very simple and start afresh -
            // users would hate this as all data would be lost!
            if (newVersion != oldVersion) {
                Log.w(CLASS_TAG, "Upgrading database from version " + oldVersion + " to " + newVersion
                        + ", which will destroy all old data");
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
                onCreate(db);
            }
        }
    }

    private void saveDummyDataToDB(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        List<Song> dummyData;

        dummyData = SongShoppingCartModel.getDummyCatalogData();
        // Insert the dummy songs in the list
        for (Song s : dummyData) {
            values.clear();
            values.put(COLUMN_SONG_TITLE, s.getTitle());
            values.put(COLUMN_SONG_LENGTH, s.getLength());
            values.put(COLUMN_SONG_SELECTED, s.isSelected());
            //Perform the insert into the DB
            db.insert(TABLE_SONGS, null, values);
        }

        return;

    }

}
