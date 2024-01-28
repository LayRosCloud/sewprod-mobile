package com.betrayal.atcutter.scripts.services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.betrayal.atcutter.models.UserDataEntity;
import com.betrayal.atcutter.scripts.data.DatabaseHelper;
import com.betrayal.atcutter.scripts.data.constants.DatabaseConstants;
import com.betrayal.atcutter.scripts.mappers.UserDataMapper;

import java.util.List;

public class UserFinder extends Service {
    private final UserDataMapper mapper;

    public UserFinder(Context context){
        super(context);
        mapper = new UserDataMapper();
    }

    public List<UserDataEntity> findAll(){
        SQLiteDatabase database = helper.getReadableDatabase();
        database.beginTransaction();

        Cursor cursor = database.rawQuery(DatabaseConstants.SELECT_ALL, null);

        List<UserDataEntity> users = mapper.toListUser(cursor);

        database.endTransaction();

        return users;
    }

    public UserDataEntity findByPinCode(String pinCode){
        SQLiteDatabase database = helper.getReadableDatabase();
        database.beginTransaction();

        String[] args = {
                pinCode
        };

        Cursor cursor = database.rawQuery(DatabaseConstants.SELECT_BY_PIN_CODE, args);

        List<UserDataEntity> users = mapper.toListUser(cursor);

        database.endTransaction();
        database.close();
        return users.get(0);
    }
}
