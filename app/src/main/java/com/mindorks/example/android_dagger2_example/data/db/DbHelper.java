package com.mindorks.example.android_dagger2_example.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mindorks.example.android_dagger2_example.data.model.User;

/**
 * @author wzc
 * @date 2018/6/5
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "user_table";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PWD = "user_pwd";

    public DbHelper(Context context, String name, int version) {
        super(context, name, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    private void createTable(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS "
                        + USER_TABLE + "("
                        + USER_ID + " INTEGER PRIMARY KEY, "
                        + USER_NAME + " VARCHAR(50), "
                        + USER_PWD + " VARCHAR(50)" + ")");
    }

    public Long insertUser(User data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, data.getUsername());
        contentValues.put(USER_PWD, data.getPassword());
        return db.insert(USER_TABLE, null, contentValues);
    }

    public User getUser(Long userId) throws NullPointerException, Resources.NotFoundException {
        String[] columns = new String[]{
                USER_ID,
                USER_NAME,
                USER_PWD
        };
        String selection = USER_ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(userId)};
        Cursor cursor = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.query(USER_TABLE, columns, selection, selectionArgs, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                User user = new User();
                user.setUsername(cursor.getString(cursor.getColumnIndex(USER_NAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PWD)));
                return user;
            } else {
                throw new Resources.NotFoundException("User id " + userId + " does not exist");
            }
        } catch (NullPointerException e) {
            throw e;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void deleteUser(Long userId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(USER_TABLE, USER_ID + "=?", new String[]{userId + ""});
    }
}
