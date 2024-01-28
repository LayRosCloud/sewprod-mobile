package com.betrayal.atcutter.views;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.databinding.FragmentPinCodeSaveBinding;
import com.betrayal.atcutter.models.UserDataEntity;
import com.betrayal.atcutter.scripts.services.UserCleared;
import com.betrayal.atcutter.scripts.services.UserRegister;
import com.betrayal.atcutter.views.dialogues.MessageDialog;

public class PinCodeSaveFragment extends Fragment {

    private FragmentPinCodeSaveBinding binding;
    private final StringBuilder firstPinCode;
    private final StringBuilder secondPinCode;

    private String email;
    private String password;

    public PinCodeSaveFragment() {
        firstPinCode = new StringBuilder();
        secondPinCode = new StringBuilder();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPinCodeSaveBinding.inflate(inflater);
        View view = binding.getRoot();

        assert getArguments() != null;

        if(getArguments().isEmpty()){
            throw new RuntimeException();
        }

        email = getArguments().getString(AuthFragment.EMAIL_KEY);
        password = getArguments().getString(AuthFragment.PASSWORD_KEY);

        Button[] buttons = receiveButtonArray();

        for (Button button : buttons) {
            button.setOnClickListener(this::clickNumber);
        }

        binding.getRoot();

        return view;
    }

    private Button[] receiveButtonArray(){
        return new Button[]{
                binding.button1,
                binding.button2,
                binding.button3,
                binding.button4,
                binding.button5,
                binding.button6,
                binding.button7,
                binding.button8,
                binding.button9,
                binding.button0
        };
    }

    private void clickNumber(View view){
        Button button = (Button) view;

        if(button == null){
            return;
        }

        boolean isFullPinCodes = addCharacter(button.getText().toString());

        saveAndNavigateToHubActivity(isFullPinCodes);
    }

    private void saveAndNavigateToHubActivity(boolean isFullPinCodes){
        boolean equalsPinCodes = firstPinCode.toString().equals(secondPinCode.toString());

        if(isFullPinCodes && equalsPinCodes) {
            save();

            navigateToHubActivity();
        }else if(isFullPinCodes) {
            showError();
        }
    }

    private void showError(){
        final String message = "Пинкоды не совпадают!";
        final String title = "Ошибка!";

        Dialog messageBox = new MessageDialog(getContext(), title, message);
        messageBox.show();
    }

    private void save(){
        UserCleared cleared = new UserCleared(getContext());
        UserRegister register = new UserRegister(getContext());

        cleared.truncate();

        register.register(
                UserDataEntity.builder()
                        .setPinCode(firstPinCode.toString())
                        .setEmail(email)
                        .setPassword(password)
                        .build()
        );
    }

    private void navigateToHubActivity(){
        Intent intent = new Intent(getContext(), HubActivity.class);
        startActivity(intent);
    }

    private boolean addCharacter(String character){
        boolean isFullFirstPinCode = firstPinCode.length() < 5;
        boolean isFullSecondPinCode = secondPinCode.length() < 5;

        if(isFullFirstPinCode) {
            firstPinCode.append(character);
        } else if (isFullSecondPinCode) {
            binding.title.setText("Повторите пин-код");
            secondPinCode.append(character);
        }

        return isFullFirstPinCode && isFullSecondPinCode;
    }
}