package de.lukaskoerfer.p2pchat.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class databaseClass extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String  COLUMN_USER_IMAGE = "USER_IMAGE";
    public static final String COLUMN_USER_MAC_ADDRESS = "USER_MAC_ADDRESS";
    public static final String  COLUMN_USER_BLUETOOTH_ADDRESS= "USER_BLUETOOTH";
    public static final String COLUMN_ID ="ID";

    public databaseClass(@Nullable Context context){
        super(context,"user.db",null,1);

    }

    // this is called when first time database is accessed
    @Override
    public void onCreate(SQLiteDatabase db){
        // define the database structure
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_IMAGE +" BLOB, " + COLUMN_USER_MAC_ADDRESS + " TEXT, " + COLUMN_USER_BLUETOOTH_ADDRESS + " TEXT);";
        // create the database
        db.execSQL(createTableStatement);
    }

    // this is called if the database version number changes . It prevents pervious users apps from breaking when we change the database design
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    // Query Operations for adding data in database
    public boolean addOne( UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // it works like a hash map
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, user.getName() );
        cv.put(COLUMN_USER_BLUETOOTH_ADDRESS, user.getBluetooth_ssid());
        cv.put(COLUMN_USER_MAC_ADDRESS, user.getWifi_mac_address());
        cv.put(COLUMN_USER_IMAGE, user.getImage());
        long insert = db.insert(USER_TABLE, null, cv);
        if(insert!=-1)
            return true;  // data has added
        return false;

    }

    // QueryOperation retrieve all the data

    public ArrayList<UserModel> getEveryOne(){

        ArrayList <UserModel> returnList = new ArrayList<>();

         //get all the data from the database

        String queryString ="SELECT * FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor  = db.rawQuery(queryString , null);

        if(cursor.moveToFirst())
        {
            do{

                String name  = cursor.getString(0);
                int id = cursor.getInt(4);
                String bluetoothaddress = cursor.getString(2);
                byte[]  image           = cursor.getBlob(1);
                String macaddress = cursor.getString(3);
                UserModel user = new UserModel(name,image,macaddress,bluetoothaddress,id);
                returnList.add(user);
            } while(cursor.moveToNext());
        }
        else {
            //failure
        }
        cursor.close();
        db.close();
        return returnList;

    }

    public int updateContact(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_IMAGE, user.getName());
        values.put(COLUMN_USER_IMAGE,user.getImage());

        // updating row
        return db.update(USER_TABLE, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }

    // Deleting single contact
    public void deleteContact(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USER_TABLE, COLUMN_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
        db.close();
    }

}
