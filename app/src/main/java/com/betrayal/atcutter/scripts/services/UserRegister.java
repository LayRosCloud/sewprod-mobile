package com.betrayal.atcutter.scripts.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.betrayal.atcutter.models.UserDataEntity;
import com.betrayal.atcutter.scripts.data.constants.DatabaseConstants;

import java.util.List;
import java.util.Objects;

public class UserRegister extends Service {
    public UserRegister(Context context) {
        super(context);
    }

    public void register(UserDataEntity user){

        SQLiteDatabase database = helper.getWritableDatabase();
        //database.beginTransaction();

        Object[] values = {
                user.getEmail(),
                user.getPassword(),
                user.getPinCode()
        };

        database.execSQL(DatabaseConstants.INSERT, values);
        //database.endTransaction();
        database.close();
    }

    public void ensureCreated(){
        SQLiteDatabase writableData = helper.getWritableDatabase();
        writableData.execSQL(DatabaseConstants.ENSURE_CREATED);
        writableData.close();
    }

    public void ensureDeleted(){
        SQLiteDatabase writableData = helper.getWritableDatabase();
        writableData.execSQL("DROP TABLE IF EXISTS \"users\"");
        writableData.close();
    }
}
