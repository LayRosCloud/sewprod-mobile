package com.betrayal.atcutter.scripts;

import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileHandler {
    private final TextView email;
    private final TextView fullName;
    private final TextView uid;
    private final TextView dateRegistration;


    public ProfileHandler(TextView email, TextView fullName, TextView uid, TextView date) {
        this.email = email;
        this.fullName = fullName;
        this.uid = uid;
        this.dateRegistration = date;
    }

    public void setEmail(String text){
        email.setText(text);
    }

    public void setFullName(String lastName, String firstName){
        fullName.setText(String.format("%s %s", lastName, firstName));
    }

    public void setUid(String text){
        uid.setText(text);
    }
    public void setDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String formattingDate = dateFormat.format(date);
        dateRegistration.setText(formattingDate);
    }
}
