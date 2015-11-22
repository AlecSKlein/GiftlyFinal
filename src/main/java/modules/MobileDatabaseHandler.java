package modules;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import models.FriendDAO;
import models.FriendListDAO;
import models.GiftDAO;
import models.GiftListDAO;
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
    public static final String COLUMN_G_TITLE = "title";
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
                COLUMN_U_USERID+" INTEGER UNIQUE,"+
                COLUMN_U_EMAIL+" TEXT PRIMARY KEY,"+
                COLUMN_U_FNAME+" TEXT,"+
                COLUMN_U_LNAME+" TEXT,"+
                COLUMN_U_PASSWORD+" TEXT,"+
                COLUMN_U_STATE+" INTEGER);";
        db.execSQL(query);
        query = "CREATE TABLE "+TABLE_FRIEND+"("+
                COLUMN_F_FRIENDID+" INTEGER PRIMARY KEY,"+
                COLUMN_F_USERID+" INTEGER REFERENCES User(USERID),"+
                COLUMN_F_NAME+" TEXT,"+
                COLUMN_F_DOB+" TEXT,"+
                COLUMN_F_STATE+" INTEGER);";
        db.execSQL(query);
        query = "CREATE TABLE "+TABLE_GIFT+"("+
                COLUMN_G_ASIN+" TEXT PRIMARY KEY,"+
                COLUMN_G_FRIENDID+" INTEGER REFERENCES Friend(FRIENDID),"+
                COLUMN_G_TITLE+" TEXT,"+
                COLUMN_G_STATE+" INTEGER);";
        db.execSQL(query);
        query = "CREATE TABLE "+TABLE_INTEREST+"("+
                COLUMN_I_INTERESTNAME+" TEXT PRIMARY KEY,"+
                COLUMN_I_FRIENDID+" INTEGER REFERENCES Friend(FRIENDID),"+
                COLUMN_I_STATE+" INTEGER);";
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

    /*
    * params
    * email: primary key
    */
    public UserDAO getUser(String email){
        String query = "SELECT "+COLUMN_U_USERID+","+COLUMN_U_FNAME+","+COLUMN_U_LNAME+" FROM "+TABLE_USER+" WHERE "+COLUMN_U_EMAIL+"='"+email+"';";
        UserDAO user = null;
        long userid = -1;
        String fname = null, lname = null;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        if(c != null && c.moveToFirst()){
            userid = c.getLong(c.getColumnIndex(COLUMN_U_USERID));
            fname = c.getString(c.getColumnIndex(COLUMN_U_FNAME));
            lname = c.getString(c.getColumnIndex(COLUMN_U_LNAME));
            c.close();
        }
        try{
            if(userid!=-1 || fname.equals(null) || lname.equals(null)){
                user = new UserDAO(userid, email, fname, lname);
                System.out.println("From DB " + user.getEmail());
            }
        } catch(Exception e){
            System.out.println("oopsy");
        }
        return user;
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

    /*
    * params
    * userid: foreign key
    */
    public FriendListDAO getFriends(long userid){
        String query = "SELECT "+COLUMN_F_FRIENDID+","+COLUMN_F_NAME+","+COLUMN_F_DOB+" FROM "+TABLE_FRIEND+" WHERE "+COLUMN_F_USERID+"="+userid+";";
        System.out.println(query);
        long friendid = -1;
        String name = null, dob = null;
        List<FriendDAO> friendList = new ArrayList<FriendDAO>();
        System.out.println("list got");
        SQLiteDatabase db = getWritableDatabase();
        System.out.println("DB got");
        Cursor c = db.rawQuery(query, null);
        System.out.println("Cursor query");
        if(c != null && c.moveToFirst()){
            do{
                friendid = -1;
                name = dob = null;
                try{
                    friendid = c.getInt(c.getColumnIndex(COLUMN_F_FRIENDID));
                    name = c.getString(c.getColumnIndex(COLUMN_F_NAME));
                    dob = c.getString(c.getColumnIndex(COLUMN_F_DOB));
                } catch(Exception e){

                }
                if(friendid == -1 || name.equals(null) || dob.equals(null))
                {
                    System.out.println("Bad friend data, pass");
                }
                else {
                    friendList.add(new FriendDAO(friendid, userid, name, dob));
                }
            } while(c.moveToNext());
        }
        return new FriendListDAO(friendList);
    }

    public void addGift(GiftDAO gift){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_G_ASIN, gift.getAsin());
        cv.put(COLUMN_G_FRIENDID, gift.getFriendid());
        cv.put(COLUMN_G_TITLE, gift.getTitle());
        cv.put(COLUMN_G_STATE, gift.getState());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_GIFT, null, cv);
        System.out.println("supposedly gift is added");
    }

    public GiftListDAO getGifts(long friendid){
        String query = "SELECT "+COLUMN_G_ASIN+","+COLUMN_G_TITLE+" FROM "+TABLE_GIFT+" WHERE "+COLUMN_G_FRIENDID+"="+friendid+";";
        List<GiftDAO> giftList = new ArrayList<GiftDAO>();
        String asin = null, title = null;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        if(c != null && c.moveToFirst()){
            do{
                asin = title = null;
                try{
                    asin = c.getString(c.getColumnIndex(COLUMN_G_ASIN));
                    title = c.getString(c.getColumnIndex(COLUMN_G_TITLE));
                } catch(Exception e){

                }
                if(asin.equals(null) || title.equals(null)){
                    System.out.println("Bad gift data, pass");
                }
                else {
                    giftList.add(new GiftDAO(asin, friendid, title));
                }
            } while(c.moveToNext());
        }
        return new GiftListDAO(giftList);
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

    public InterestListDAO getInterests(long friendid){
        String query = "SELECT "+COLUMN_I_INTERESTNAME+" FROM "+TABLE_INTEREST+" WHERE "+COLUMN_I_FRIENDID+"="+friendid+";";
        List<InterestDAO> interestList = new ArrayList<InterestDAO>();
        String interestName = null;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        if(c != null && c.moveToFirst()){
            do{
                interestName = null;
                try{
                    interestName = c.getString(c.getColumnIndex(COLUMN_I_INTERESTNAME));
                } catch(Exception e){

                }
                if(interestName == null){
                    System.out.println("Bad interest data, pass");
                }
                else {
                    interestList.add(new InterestDAO(interestName, friendid));
                }
            } while(c.moveToNext());
        }
        return new InterestListDAO(interestList);
    }

    public String databaseToString(){
        String dbString = " ";
        SQLiteDatabase db = getWritableDatabase();
        String query = "Select * From " + TABLE_FRIEND + " Where 1";
        Cursor c = db.rawQuery(query, null);

//        while(!c.isAfterLast()){
//            if(c.getString(c.getColumnIndex("email")) != null){
//                dbString = c.getString(c.getColumnIndex("email"));
//            }
//        }

        if(c != null && c.moveToFirst()){
            do{
                dbString = c.getString(c.getColumnIndex("name"));
            }while(c.moveToNext());
            c.close();
        }

        System.out.println("database to string");
        return dbString;
    }
}
