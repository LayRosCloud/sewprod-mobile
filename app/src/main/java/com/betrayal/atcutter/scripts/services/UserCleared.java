package com.betrayal.atcutter.scripts.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.betrayal.atcutter.scripts.data.constants.DatabaseConstants;

public class UserCleared extends Service{
    public UserCleared(Context context) {
        super(context);
    }

    public void truncate(){
        SQLiteDatabase sql = helper.getWritableDatabase();
        sql.execSQL(DatabaseConstants.TRUNCATE);
        sql.close();
    }
}
