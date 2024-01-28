package com.betrayal.atcutter.scripts.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.betrayal.atcutter.models.UserDataEntity;
import com.betrayal.atcutter.scripts.data.constants.DatabaseConstants;

import java.util.List;

public class UserRegister extends Service {
    public UserRegister(Context context) {
        super(context);
    }

    public void register(UserDataEntity user){
        final int countParams = 3;

        SQLiteDatabase database = helper.getWritableDatabase();
        database.beginTransaction();

        ContentValues contentValues = new ContentValues(countParams);

        contentValues.put(DatabaseConstants.COLUMN_EMAIL, user.getEmail());
        contentValues.put(DatabaseConstants.COLUMN_PASSWORD, user.getPassword());
        contentValues.put(DatabaseConstants.COLUMN_PIN_CODE, user.getPinCode());

        database.insert(DatabaseConstants.TABLE_NAME, null, contentValues);

        database.endTransaction();
        database.close();
    }

    public void ensureCreated(){
        SQLiteDatabase writableData = helper.getWritableDatabase();
        writableData.execSQL(DatabaseConstants.ENSURE_CREATED);
        writableData.close();
    }
}
