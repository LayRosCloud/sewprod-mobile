package com.betrayal.atcutter.views.dialogues;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.R;

import java.util.Objects;

public class MessageDialog extends Dialog {
    public MessageDialog(@NonNull Context context, String title, String message) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initialize(title, message);
    }

    private void initialize(String title, String message){
        setContentView(R.layout.message_box);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(true);
        TextView titleView = findViewById(R.id.title);
        TextView description = findViewById(R.id.description);
        titleView.setText(title);
        description.setText(message);
    }
}
