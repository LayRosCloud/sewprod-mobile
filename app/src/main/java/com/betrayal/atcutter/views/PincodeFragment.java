package com.betrayal.atcutter.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.IdRes;
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
import com.betrayal.atcutter.callbacks.LoginCallback;
import com.betrayal.atcutter.databinding.FragmentPincodeBinding;
import com.betrayal.atcutter.models.PersonEntity;
import com.betrayal.atcutter.models.SecuritySuccessfulEntity;
import com.betrayal.atcutter.models.UserDataEntity;
import com.betrayal.atcutter.scripts.services.UserFinder;
import com.betrayal.atcutter.server.HttpBuilder;
import com.betrayal.atcutter.server.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;

public class PincodeFragment extends Fragment {
    private FragmentPincodeBinding binding;
    private final StringBuilder pinCode;
    private final List<RadioButton> radioButtons;

    private final static int COUNT_CELLS = 5;

    public PincodeFragment() {
        pinCode = new StringBuilder();
        radioButtons = new ArrayList<>(COUNT_CELLS);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPincodeBinding.inflate(inflater);
        View view = binding.getRoot();
        radioButtons.addAll(Arrays.asList(
                binding.firstLetter,
                binding.secondLetter,
                binding.thirdLetter,
                binding.fourthLetter,
                binding.fiveLetter
                )
        );

        Button[] buttons = {
                binding.button1, binding.button2, binding.button3, binding.button4, binding.button5,
                binding.button6, binding.button7, binding.button8, binding.button9, binding.button0,
        };

        for (Button button: buttons) {
            button.setOnClickListener(this::clickOnButton);
        }

        return view;
    }

    private void clickOnButton(View v){
        Button button = (Button)v;
        if(button == null || pinCode.length() >= COUNT_CELLS){
            return;
        }

        String contentButton = button.getText().toString();

        pinCode.append(contentButton);
        refresh();

        if (pinCode.length() == COUNT_CELLS){
            UserFinder finder = new UserFinder(getContext());
            UserDataEntity data = finder.findByPinCode(pinCode.toString());

            PersonEntity person = new PersonEntity(data.getEmail(), data.getPassword());


            HttpBuilder httpBuilder = new HttpBuilder();
            PersonRepository repository = httpBuilder.createService(PersonRepository.class);

            Call<SecuritySuccessfulEntity> authCall = repository.login(person);

            LoginCallback callback
                    = new LoginCallback(getContext());

            callback.setCallback(item -> {
                Intent intent = new Intent(getContext(), HubActivity.class);
                startActivity(intent);
            });

            authCall.enqueue(callback);
        }
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