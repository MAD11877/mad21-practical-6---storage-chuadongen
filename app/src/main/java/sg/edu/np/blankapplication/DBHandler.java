package sg.edu.np.blankapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DBHandler  extends SQLiteOpenHelper {
    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "practical.db";

    public static String TABLE_USER = "User";
    public static String COLUMN_ID = "iD";
    public static String COLUMN_NAME = "name";
    public static String COLUMN_DESCRIPTION = "description";
    public static String COLUMN_FOLLOWED = "followed";

    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER+
                "(" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY," + COLUMN_NAME+ " TEXT NOT NULL,"+COLUMN_DESCRIPTION+" TEXT,"
                +COLUMN_FOLLOWED+" INTEGER NOT NULL"
                 +")";
        db.execSQL(CREATE_USER_TABLE);

//        for(int i =0;i<20;i++){
//            int randomNum = 10 + (int)(Math.random() * ((10000 - 10) + 1));
//            String name = "Name" +String.valueOf(randomNum);
//            String desc = "Description" +String.valueOf(randomNum);
//            Integer followed;
//            if (i%2!=0){
//                followed =0;
//            }else{
//                followed=1;
//            }
//            User u = new User(name,desc,parseInt(followed));
//
//            AddUser(u);
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void AddUser(User u ){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,u.name);
        values.put(COLUMN_DESCRIPTION,u.description);
        if (u.followed) {
            values.put(COLUMN_FOLLOWED,1);
        }else{
            values.put(COLUMN_FOLLOWED,0);
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USER,null,values);
        db.close();
    }

    public ArrayList<User> GetUsers(){
        ArrayList<User> userList = new ArrayList<User>();
        String query = "SELECT * FROM " +TABLE_USER;
        SQLiteDatabase db = this.getWritableDatabase(); //readable
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Integer UserID = Integer.parseInt(cursor.getString(0));
                String Name = cursor.getString(1);
                String Description = cursor.getString(2);
                Integer followedInt = Integer.parseInt(cursor.getString(3));
                User u = new User(Name,Description,UserID,parseInt(followedInt));
                userList.add(u);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return userList;
    }

    public boolean updateUser(User u){
        boolean result = false;
        String query = "SELECT * FROM " +TABLE_USER + " WHERE " +COLUMN_ID +" = "+u.id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            String updateQuery = "UPDATE "+TABLE_USER
                    + " SET " + COLUMN_NAME + " = \"" + u.name +"\""
                    + " , " + COLUMN_DESCRIPTION + " = \"" + u.description +"\""
                    + " , " + COLUMN_FOLLOWED + " = " + parseBool(u.followed) +""
                    + " WHERE " +COLUMN_ID +" = "+u.id;
            db.execSQL(updateQuery);
            cursor.close();
            result=true;
        }
        db.close();
        return result;
    }

    public boolean parseInt(int i){
        if(i==1){
            return true;
        }else{
            return false;
        }
    }

    public int parseBool(boolean b){
        if (b){
            return 1;
        }else{
            return 0;
        }
    }
}
