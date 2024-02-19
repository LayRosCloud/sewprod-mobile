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
import android.widget.RadioButton;

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

    RadioButton[] radioButtons;

    public PinCodeSaveFragment() {
        firstPinCode = new StringBuilder();
        secondPinCode = new StringBuilder();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPinCodeSaveBinding.inflate(inflater);
        View view = binding.getRoot();

        radioButtons = new RadioButton[]{
                binding.firstLetter,
                binding.secondLetter,
                binding.thirdLetter,
                binding.fourthLetter,
                binding.fiveLetter,
        };

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

        binding.clearButton.setOnClickListener(v -> {
            int lengthPinCode = firstPinCode.toString().length();
            int lengthSecPinCode = secondPinCode.toString().length();

            if(lengthPinCode < PincodeFragment.COUNT_CELLS){
                if(firstPinCode.toString().isEmpty()){
                    return;
                }
                firstPinCode.deleteCharAt(lengthPinCode - 1);
                refreshPoints(firstPinCode.toString());
            }else{
                if(secondPinCode.toString().isEmpty()){
                    return;
                }
                secondPinCode.deleteCharAt(lengthSecPinCode - 1);
                refreshPoints(secondPinCode.toString());
            }

        });

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

        addCharacter(button.getText().toString());

        saveAndNavigateToHubActivity();
    }

    private void saveAndNavigateToHubActivity(){
        boolean equalsPinCodes = firstPinCode.toString().equals(secondPinCode.toString());
        boolean maximumPinCodes = firstPinCode.length() == PincodeFragment.COUNT_CELLS
                && secondPinCode.length() == PincodeFragment.COUNT_CELLS;

        if(maximumPinCodes && equalsPinCodes) {
            save(firstPinCode.toString());

            navigateToHubActivity();
        }else if(maximumPinCodes) {
            showError();
        }
    }

    private void showError(){
        final String message = "Пинкоды не совпадают!";
        final String title = "Ошибка!";

        Dialog messageBox = new MessageDialog(getContext(), title, message);
        messageBox.show();
    }

    private void save(String pinCode){
        UserRegister register = new UserRegister(getContext());

        UserCleared cleared = new UserCleared(getContext());
        cleared.truncate();

        register.register(
                UserDataEntity.builder()
                        .setPinCode(pinCode)
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
        boolean isNotFullFirstPinCode = firstPinCode.length() < PincodeFragment.COUNT_CELLS;
        boolean isNotFullSecondPinCode = secondPinCode.length() < PincodeFragment.COUNT_CELLS;

        if(isNotFullFirstPinCode) {
            firstPinCode.append(character);
            refreshPoints(firstPinCode.toString());
        } else if (isNotFullSecondPinCode) {
            secondPinCode.append(character);
            refreshPoints(secondPinCode.toString());
        }

        if(firstPinCode.length() >= PincodeFragment.COUNT_CELLS){
            binding.title.setText("Повторите пин-код");
        }

        return isNotFullFirstPinCode && isNotFullSecondPinCode;
    }

    private void refreshPoints(String text){
        for(RadioButton button: radioButtons){
            button.setChecked(false);
        }
        if(text.length() != PincodeFragment.COUNT_CELLS){
            for (int i = 0; i < text.length(); i++) {
                radioButtons[i].setChecked(true);
            }
        }
    }
}