package com.betrayal.atcutter.scripts.mappers;

import android.database.Cursor;
import android.util.Log;

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
                .setId(cursor.getInt(0))
                .setEmail(cursor.getString(1))
                .setPassword(cursor.getString(2))
                .setPinCode(cursor.getString(3))
                .build();
    }

    private <T> T get(Cursor cursor, String columnName){
        int columnId = cursor.getColumnIndex(columnName);
        T value = (T) cursor.getString(columnId);
        return value;
    }
}
