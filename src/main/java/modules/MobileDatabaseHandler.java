package modules;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import models.FriendDAO;
import models.GiftDAO;
import models.InterestDAO;
import models.InterestListDAO;
import models.UserDAO;

/**
 * Created by Alec Klein on 11/8/2015.
 */
public class MobileDatabaseHandler extends SQLiteOpenHelper{

    private static final String DB_NAME = "giftlydb";
    private static final int DB_VERSION = 1;

    public static final String TABLE_USER = "User";
    public static final String COLUMN_U_USERID = "userid";
    public static final String COLUMN_U_EMAIL = "email";
    public static final String COLUMN_U_FNAME = "fname";
    public static final String COLUMN_U_LNAME = "lname";
    public static final String COLUMN_U_PASSWORD = "password";
    public static final String COLUMN_U_STATE = "state";

    public static final String TABLE_FRIEND = "Friend";
    public static final String COLUMN_F_FRIENDID = "friendid";
    public static final String COLUMN_F_USERID = "userid";
    public static final String COLUMN_F_NAME = "name";
    public static final String COLUMN_F_DOB = "dob";
    public static final String COLUMN_F_STATE = "state";

    public static final String TABLE_GIFT = "Gift";
    public static final String COLUMN_G_ASIN = "asin";
    public static final String COLUMN_G_FRIENDID = "friendid";
    public static final String COLUMN_G_DESCRIPTION = "description";
    public static final String COLUMN_G_STATE = "state";

    public static final String TABLE_INTEREST = "Interest";
    public static final String COLUMN_I_INTERESTNAME = "interestname";
    public static final String COLUMN_I_FRIENDID = "friendid";
    public static final String COLUMN_I_STATE = "state";


    public MobileDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_USER+"("+
                COLUMN_U_USERID+" INTEGER PRIMARY KEY,"+
                COLUMN_U_EMAIL+" TEXT,"+
                COLUMN_U_FNAME+" TEXT,"+
                COLUMN_U_LNAME+" TEXT,"+
                COLUMN_U_PASSWORD+" TEXT,"+
                COLUMN_U_STATE+" INTEGER);";
        db.execSQL(query);
        System.out.println("db created?");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIEND + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GIFT + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INTEREST + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER + ";");
        onCreate(db);
    }

    public void addUser(UserDAO user){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_U_EMAIL, user.getEmail());
        cv.put(COLUMN_U_FNAME, user.getFname());
        cv.put(COLUMN_U_LNAME, user.getLname());
        cv.put(COLUMN_U_PASSWORD, user.getPassword());
        cv.put(COLUMN_U_USERID, user.getUserid());
        cv.put(COLUMN_U_STATE, user.getState());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USER, null, cv);
        System.out.println("supposedly user is added");
    }

    public void addFriend(FriendDAO friend){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_F_FRIENDID, friend.getFriendid());
        cv.put(COLUMN_F_USERID, friend.getUserid());
        cv.put(COLUMN_F_NAME, friend.getName());
        cv.put(COLUMN_F_DOB, friend.getDob());
        cv.put(COLUMN_F_STATE, friend.getState());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_FRIEND, null, cv);
        System.out.println("supposedly friend is added");
    }

    public void addGift(GiftDAO gift){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_G_ASIN, gift.getAsin());
        cv.put(COLUMN_G_FRIENDID, gift.getFriendid());
        cv.put(COLUMN_G_DESCRIPTION, gift.getDescription());
        cv.put(COLUMN_G_STATE, gift.getState());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_GIFT, null, cv);
        System.out.println("supposedly gift is added");
    }

    public void addInterest(InterestDAO interest){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_I_INTERESTNAME, interest.getInterestName());
        cv.put(COLUMN_I_FRIENDID, interest.getFriendid());
        cv.put(COLUMN_I_STATE, interest.getState());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_INTEREST, null, cv);
        System.out.println("supposedly interest is added");
    }

    public String databaseToString(){
        String dbString = " ";
        SQLiteDatabase db = getWritableDatabase();
        String query = "Select * From " + TABLE_USER + " Where 1";
        Cursor c = db.rawQuery(query, null);

//        while(!c.isAfterLast()){
//            if(c.getString(c.getColumnIndex("email")) != null){
//                dbString = c.getString(c.getColumnIndex("email"));
//            }
//        }

        if(c != null && c.moveToFirst()){
            do{
                dbString = c.getString(c.getColumnIndex("email"));
            }while(c.moveToNext());
            c.close();
        }

        System.out.println("database to string");
        return dbString;
    }
}
