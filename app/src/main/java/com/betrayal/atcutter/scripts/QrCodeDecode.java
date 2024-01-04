package com.betrayal.atcutter.scripts;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;

import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class QrCodeDecode implements DecodeCallback {
    private final Activity activity;
    private final View view;
    public QrCodeDecode(Activity activity, View view){
        this.activity = activity;
        this.view = view;
    }
    @Override
    public void onDecoded(@NonNull Result result) {
        activity.runOnUiThread(new QrCodeRunnable(view, result));
    }
}
