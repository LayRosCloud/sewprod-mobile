package com.betrayal.atcutter.views;

import android.content.Intent;
import android.os.Bundle;

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
import com.betrayal.atcutter.scripts.services.UserRegister;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPinCodeSaveBinding.inflate(inflater);
        View view = binding.getRoot();

        assert getArguments() != null;

        if(getArguments().isEmpty()){
            throw new RuntimeException();
        }

        email = getArguments().getString("EMAIL");
        password = getArguments().getString("PASSWORD");

        Button[] buttons = {
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

        for (Button button : buttons) {
            button.setOnClickListener(this::clickNumber);
        }

        binding.getRoot();

        return view;
    }

    private void clickNumber(View view){
        Button button = (Button) view;

        if(button == null){
            return;
        }

        boolean existsCharacters = addCharacter(button);

        if( existsCharacters
            && firstPinCode.toString().equals(secondPinCode.toString())
        ) {
            UserRegister register = new UserRegister(getContext());

            register.register(
                    UserDataEntity.builder()
                        .setPinCode(firstPinCode.toString())
                        .setEmail(email)
                        .setPassword(password)
                        .build()
            );


            Intent intent = new Intent(getContext(), HubActivity.class);
            startActivity(intent);
        }
    }

    private boolean addCharacter(Button pressedButton){
        boolean isFullFirstPinCode = firstPinCode.length() < 5;
        boolean isFullSecondPinCode = secondPinCode.length() < 5;

        if(isFullFirstPinCode) {
            firstPinCode.append(pressedButton.getText().toString());
        } else if (isFullSecondPinCode) {
            binding.title.setText("Повторите пин-код");
            secondPinCode.append(pressedButton.getText().toString());
        }

        return isFullFirstPinCode && isFullSecondPinCode;
    }
}