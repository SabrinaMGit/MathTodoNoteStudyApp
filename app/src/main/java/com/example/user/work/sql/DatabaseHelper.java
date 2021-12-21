package com.example.user.work.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.work.models.ToDoListModels;
import com.example.user.work.models.User;
import com.example.user.work.models.BlitzrechnenModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 9/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserDatabase.db";

    // User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_TODOTASK = "todotask";
    private static final String TABLE_MATHE = "mathe";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private static final String COLUMN_TODO_ID = "todo_id";
    private static final String COLUMN_AUFGABEN_NAME = "aufgaben_name";
    private static final String COLUMN_NOTIZEN = "notizen";
    private static final String COLUMN_LISTE = "liste";
    private static final String COLUMN_DATUM = "datum";
    private static final String COLUMN_ERRINERN = "errinern";
    private static final String COLUMN_PRIORITAET = "prioritaet";
    private static final String COLUMN_HIGHLIGHT = "highlight";
    private static final String COLUMN_ABGESCHLOSSEN = "abgeschlossen";

    private static final String COLUMN_MATHE_ID = "mathe_id";
    private static final String COLUMN_ZAHL1 = "zahl1";
    private static final String COLUMN_ZAHL2 = "zahl2";
    private static final String COLUMN_ERGEBNIS = "ergebnis";
    private static final String COLUMN_OPERATION = "operation";
    private static final String COLUMN_ISRICHTIG = "isRichtig";
    private static final String COLUMN_PUNKTE = "punkte";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    private String CREATE_TABLE_TODOTASK = "CREATE TABLE " + TABLE_TODOTASK + "("
           + COLUMN_TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_ID + " INTEGER," + COLUMN_AUFGABEN_NAME + " TEXT,"
            + COLUMN_NOTIZEN + " TEXT," + COLUMN_LISTE + " TEXT," + COLUMN_DATUM + " TEXT,"
            + COLUMN_ERRINERN + " TEXT," + COLUMN_PRIORITAET + " TEXT," + COLUMN_HIGHLIGHT + " TEXT,"
            + COLUMN_ABGESCHLOSSEN + " TEXT" + ")";

    private String CREATE_TABLE_MATHE = "CREATE TABLE " + TABLE_MATHE + "("
            + COLUMN_MATHE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLUMN_USER_ID + " INTEGER," + COLUMN_AUFGABEN_NAME + " TEXT,"
            + COLUMN_ZAHL1 + " INTEGER," + COLUMN_ZAHL2 + " INTEGER," + COLUMN_ERGEBNIS + " INTEGER,"
            + COLUMN_OPERATION + " TEXT," + COLUMN_ISRICHTIG + " TEXT," + COLUMN_PUNKTE + " INTEGER"
            + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_TABLE_TODOTASK = "DROP TABLE IF EXISTS " + TABLE_TODOTASK;
    private String DROP_TABLE_MATHE = "DROP TABLE IF EXISTS " + TABLE_MATHE;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TABLE_TODOTASK);
        db.execSQL(CREATE_TABLE_MATHE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_TABLE_TODOTASK);
        db.execSQL(DROP_TABLE_MATHE);
        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    /**
     * This method is to create user record
     *
     * @param toDoList
     */
    public void addToDoTask(ToDoListModels toDoList) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, toDoList.getId());
        values.put(COLUMN_AUFGABEN_NAME, toDoList.getName());
        values.put(COLUMN_NOTIZEN, toDoList.getNotizen());
        values.put(COLUMN_LISTE, toDoList.getListe());
        values.put(COLUMN_DATUM, toDoList.getDatum());
        values.put(COLUMN_ERRINERN, toDoList.getErrinern());
        values.put(COLUMN_PRIORITAET, toDoList.getPrioritaet());
        values.put(COLUMN_HIGHLIGHT, toDoList.getHighlight());
        values.put(COLUMN_ABGESCHLOSSEN, toDoList.getAbgeschlossen());


        // Inserting Row
        db.insert(TABLE_TODOTASK, null, values);
        db.close();
    }

    /**
     * This method is to create user record
     *
     * @param blitzrechnenModels
     */
    public void addMatheDaten(BlitzrechnenModels blitzrechnenModels) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MATHE_ID, blitzrechnenModels.getMathe_id());
        values.put(COLUMN_ZAHL1, blitzrechnenModels.getZahl1());
        values.put(COLUMN_ZAHL2, blitzrechnenModels.getZahl2());
        values.put(COLUMN_ERGEBNIS, blitzrechnenModels.getErgebnis());
        values.put(COLUMN_OPERATION, blitzrechnenModels.getGrundrechenart());
        values.put(COLUMN_ISRICHTIG, blitzrechnenModels.getIsRichtig());
        values.put(COLUMN_PUNKTE, blitzrechnenModels.getPunkte());

        // Inserting Row
        db.insert(TABLE_MATHE, null, values);
        db.close();
    }

    public void readUser() {
        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
/*
// Filter results WHERE "title" = 'My Title'
        String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );*/
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<ToDoListModels> getAllToDoTask(int userId) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_TODO_ID,
                COLUMN_USER_ID,
                COLUMN_AUFGABEN_NAME,
                COLUMN_NOTIZEN,
                COLUMN_LISTE,
                COLUMN_DATUM,
                COLUMN_ERRINERN,
                COLUMN_PRIORITAET,
                COLUMN_HIGHLIGHT,
                COLUMN_ABGESCHLOSSEN
        };
        // sorting orders
        String sortOrder =
                COLUMN_PRIORITAET + " ASC";
        List<ToDoListModels> toDoLists = new ArrayList<ToDoListModels>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_TODOTASK, //Table to query
                columns,    //columns to return
                "user_id = " + userId,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ToDoListModels toDoList = new ToDoListModels();
                        toDoList.setTodoid(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TODO_ID))));
                        toDoList.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                        toDoList.setName(cursor.getString(cursor.getColumnIndex(COLUMN_AUFGABEN_NAME)));
                        toDoList.setNotizen(cursor.getString(cursor.getColumnIndex(COLUMN_NOTIZEN)));
                        toDoList.setListe(cursor.getString(cursor.getColumnIndex(COLUMN_LISTE)));
                        toDoList.setDatum(cursor.getString(cursor.getColumnIndex(COLUMN_DATUM)));
                        toDoList.setErrinern(cursor.getString(cursor.getColumnIndex(COLUMN_ERRINERN)));
                        toDoList.setPrioritaet(cursor.getString(cursor.getColumnIndex(COLUMN_PRIORITAET)));
                        toDoList.setHighlight(cursor.getString(cursor.getColumnIndex(COLUMN_HIGHLIGHT)));
                        toDoList.setAbgeschlossen(cursor.getString(cursor.getColumnIndex(COLUMN_ABGESCHLOSSEN)));
                // Adding user record to list
                toDoLists.add(toDoList);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return toDoLists;
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<BlitzrechnenModels> getAllMatheBlitzrechnen(int matheId) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_TODO_ID,
                COLUMN_USER_ID,
                COLUMN_AUFGABEN_NAME,
                COLUMN_NOTIZEN,
                COLUMN_LISTE,
                COLUMN_DATUM,
                COLUMN_ERRINERN,
                COLUMN_PRIORITAET,
                COLUMN_HIGHLIGHT,
                COLUMN_ABGESCHLOSSEN
        };
        // sorting orders
        String sortOrder =
                COLUMN_PRIORITAET + " ASC";
        List<BlitzrechnenModels> blitzrechnenModels = new ArrayList<BlitzrechnenModels>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_TODOTASK, //Table to query
                columns,    //columns to return
                "user_id = " + matheId,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BlitzrechnenModels blitzrechnenModel = new BlitzrechnenModels();
                blitzrechnenModel.setMathe_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_TODO_ID))));
                blitzrechnenModel.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                blitzrechnenModel.setZahl1(cursor.getInt(cursor.getColumnIndex(COLUMN_ZAHL1)));
                blitzrechnenModel.setZahl2(cursor.getInt(cursor.getColumnIndex(COLUMN_ZAHL2)));
                blitzrechnenModel.setErgebnis(cursor.getInt(cursor.getColumnIndex(COLUMN_ERGEBNIS)));
                blitzrechnenModel.setGrundrechenart(cursor.getString(cursor.getColumnIndex(COLUMN_OPERATION)));
                blitzrechnenModel.setIsRichtig(cursor.getString(cursor.getColumnIndex(COLUMN_ISRICHTIG)));
                blitzrechnenModel.setPunkte(cursor.getInt(cursor.getColumnIndex(COLUMN_PUNKTE)));
                // Adding user record to list
                blitzrechnenModels.add(blitzrechnenModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return blitzrechnenModels;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete toDoTask record
     *
     * @param toDoListModels
     */
    public void deleteToDoTask(ToDoListModels toDoListModels) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_TODOTASK, COLUMN_TODO_ID + " = ?",
                new String[]{String.valueOf(toDoListModels.getTodoid())});
        db.close();
    }

    /**
     * This method is to delete toDoTask record
     *
     * @param blitzrechnenModels
     */
    public void deleteBlitzrechnenData(BlitzrechnenModels blitzrechnenModels) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_MATHE, COLUMN_MATHE_ID + " = ?",
                new String[]{String.valueOf(blitzrechnenModels.getId())});
        db.close();
    }
    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


}
