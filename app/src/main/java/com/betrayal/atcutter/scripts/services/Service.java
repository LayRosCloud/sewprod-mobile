package com.betrayal.atcutter.scripts.services;

import android.content.Context;

import com.betrayal.atcutter.scripts.data.DatabaseHelper;
import com.betrayal.atcutter.scripts.mappers.UserDataMapper;

public abstract class Service {
    protected final DatabaseHelper helper;
    public Service(Context context){
        helper = new DatabaseHelper(context);
    }
}
