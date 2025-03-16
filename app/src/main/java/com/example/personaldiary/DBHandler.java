package com.example.personaldiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    //We use version to update database.

    private static final int VERSION = 1;
    private static final String DB_NAME = "MYDiary";
    private static final String TABLE_NAME = "my_diary";

    //Column Names
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";

//    private static final String IMAGE_DIRECTORY = "image_directory";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String IMAGE_PATH = "image_path";


    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TITLE + " TEXT," +
                DESCRIPTION + " TEXT," +
                DATE + " TEXT," +
                TIME + " TEXT,"+
                IMAGE_PATH+" TEXT);";



        db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;

        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);

    }

    public void addToDo(ModelClass toDo) {
        SQLiteDatabase sqlLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues(); //We use this to make organize data before send it to the database.

        contentValues.put(TITLE, toDo.getTitle());
        contentValues.put(DESCRIPTION, toDo.getDescription());
        contentValues.put(DATE, toDo.getDate());
        contentValues.put(TIME, toDo.getTime());
        contentValues.put(IMAGE_PATH, toDo.getImageDirectory());


        //saving data to database
        sqlLiteDatabase.insert(TABLE_NAME, null, contentValues);

        //optional : then we can close the database
        sqlLiteDatabase.close();
    }


    public List<ModelClass> getAllToDos() {
        List<ModelClass> todo = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) { // first we check if the database have data using following command.
            do {

                System.out.println("Do While Loop started");

                ModelClass ModelClass = new ModelClass();
                ModelClass.setId(cursor.getInt(0));
                ModelClass.setTitle(cursor.getString(1));
                ModelClass.setDescription(cursor.getString(2));
                ModelClass.setDate(cursor.getString(3));
                ModelClass.setTime(cursor.getString(4));
                ModelClass.setImageDirectory(cursor.getString(5));

                System.out.println("Do While Loop ended");

                // Generate logs to verify the data
                Log.i("Database", "ID: " + ModelClass.getId());
                Log.i("Database", "Title: " + ModelClass.getTitle());
                Log.i("Database", "Description: " + ModelClass.getDescription());
                Log.i("Database", "Date: " + ModelClass.getDate());
                Log.i("Database", "Time: " + ModelClass.getTime());
                Log.i("Database", "Image Directory: " + ModelClass.getImageDirectory());

                todo.add(ModelClass);
                //In this array list we have list of ModelClass objects.
            } while (cursor.moveToNext());
        }
        return todo;
    }

    // Delete item

    public void deleteToDo(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});

        db.close();
    }

    // Single to do view
    public ModelClass getSingleToDo(int id){
        SQLiteDatabase db = getWritableDatabase();
        System.out.println(id);
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        ID,
                        TITLE,
                        DESCRIPTION,
                        DATE,
                        TIME,
                        IMAGE_PATH
                },
                ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null
        );

        ModelClass todo;

        if(cursor != null && cursor.moveToNext()){

//            Log.d("CursorDebug", "ID: " + cursor.getInt(0) +
//                    ", Title: " + cursor.getString(1) +
//                    ", Description: " + cursor.getString(2) +
//                    ", Started: " + cursor.getLong(3) +
//                    ", Finished: " + cursor.getLong(4));

            todo = new ModelClass(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );

            return todo;
        }
        return null;
    }

//    public int updateSingleToDo(ModelClass todo){
//
//        SQLiteDatabase sqlLiteDatabase = getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues(); //We use this to make organize data before send it to the database.
//
//
//        contentValues.put(TITLE, todo.getTitle());
//        contentValues.put(DESCRIPTION, todo.getDescription());
//        contentValues.put(DATE, todo.getDate());
//        contentValues.put(TIME, todo.getTime());
//        contentValues.put(IMAGE_PATH, todo.getImageDirectory());
//
//        long rowID =sqlLiteDatabase.insert(TABLE_NAME,null,contentValues);
//        Log.i("ROW ID:",String.valueOf(rowID));
//
//        //update data on the database
//
//        int status = sqlLiteDatabase.update(
//                TABLE_NAME,
//                contentValues,
//                ID + "= ?",
//                new String[]{String.valueOf(todo.getId())}
//        );
//
//
//        //optional : then we can close the database
//        sqlLiteDatabase.close();
//
//        return status;
//    }

}

