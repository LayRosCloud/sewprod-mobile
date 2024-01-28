package com.betrayal.atcutter.scripts.mappers;

import android.database.Cursor;

import com.betrayal.atcutter.models.UserDataEntity;
import com.betrayal.atcutter.scripts.data.constants.DatabaseConstants;

import java.util.ArrayList;
import java.util.List;

public class UserDataMapper {
    public List<UserDataEntity> toListUser(Cursor cursor){
        List<UserDataEntity> users = new ArrayList<>(cursor.getCount());
        while(cursor.moveToNext()){
            UserDataEntity user = toUser(cursor);
            users.add(user);
        }
        return users;
    }

    public UserDataEntity toUser(Cursor cursor){
        return UserDataEntity.builder()
                .setId(get(cursor, DatabaseConstants.COLUMN_ID))
                .setEmail(get(cursor, DatabaseConstants.COLUMN_EMAIL))
                .setPassword(get(cursor, DatabaseConstants.COLUMN_PASSWORD))
                .setPinCode(get(cursor, DatabaseConstants.COLUMN_PIN_CODE))
                .build();
    }

    private <T> T get(Cursor cursor, String columnName){
        int columnId = cursor.getColumnIndex(columnName);
        T value = (T) cursor.getString(columnId);
        return value;
    }
}
