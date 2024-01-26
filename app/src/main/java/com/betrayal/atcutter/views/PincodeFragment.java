package com.betrayal.atcutter.views;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.databinding.FragmentPincodeBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PincodeFragment extends Fragment {
    private FragmentPincodeBinding binding;
    private final StringBuilder pinCode;
    private final List<RadioButton> radioButtons;
    public PincodeFragment() {
        pinCode = new StringBuilder();
        radioButtons = new ArrayList<>(5);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPincodeBinding.inflate(inflater);
        View view = binding.getRoot();
        radioButtons.addAll(Arrays.asList(new RadioButton[]{
                view.findViewById(R.id.firstLetter),
                view.findViewById(R.id.secondLetter),
                view.findViewById(R.id.thirdLetter),
                view.findViewById(R.id.fourthLetter),
                view.findViewById(R.id.fiveLetter)
        }));

        int[] ids = {
                R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6,
                R.id.button7, R.id.button8, R.id.button9, R.id.button0,
        };

        for (int id:
             ids) {
            setNumberClick(id, view);
        }

        return view;
    }

    private void setNumberClick(@IdRes int id, View view){
        view.findViewById(id).setOnClickListener(this::clickOnButton);
    }

    private void clickOnButton(View v){
        Button button = (Button)v;
        if(button == null || pinCode.length() >= 5){
            return;
        }

        String contentButton = button.getText().toString();

        pinCode.append(contentButton);
        refresh();
    }

    private void refresh(){
        for (int i = 0; i < radioButtons.size(); i++) {
            RadioButton radio = radioButtons.get(i);
            radio.setChecked(false);
        }

        for (int i = 0; i < pinCode.length(); i++) {
            RadioButton radio = radioButtons.get(i);
            radio.setChecked(true);
        }
    }
}