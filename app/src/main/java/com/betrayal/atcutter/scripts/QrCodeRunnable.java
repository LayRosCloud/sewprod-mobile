package com.betrayal.atcutter.scripts;

import android.os.Bundle;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.betrayal.atcutter.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.zxing.Result;

public class QrCodeRunnable implements Runnable {
    public static final String EMAIL_KEY = "email";
    public static final String PASSWORD_KEY = "password";
    private final View view;
    private final Result result;
    public QrCodeRunnable(View view, Result result){
        this.result = result;
        this.view = view;
    }
    @Override
    public void run() {

        try{
            String resultText = result.getText();
            JsonParser parser = new JsonParser();

            Object parsedObject = parser.parse(resultText);

            JsonObject jsonObject = (JsonObject) parsedObject;

            String email = String.valueOf(jsonObject.get(EMAIL_KEY));
            String password = String.valueOf(jsonObject.get(PASSWORD_KEY));

            Bundle bundle = new Bundle();

            bundle.putString(EMAIL_KEY, email);
            bundle.putString(PASSWORD_KEY, password);
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_qrCodeFragment_to_authFragment, bundle);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
