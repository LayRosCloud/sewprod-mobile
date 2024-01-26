package com.betrayal.atcutter.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.callbacks.LoginCallback;
import com.betrayal.atcutter.databinding.FragmentAuthBinding;
import com.betrayal.atcutter.models.SecuritySuccessfulEntity;
import com.betrayal.atcutter.models.PersonEntity;
import com.betrayal.atcutter.scripts.QrCodeRunnable;
import com.betrayal.atcutter.server.HttpBuilder;
import com.betrayal.atcutter.server.repositories.PersonRepository;

import retrofit2.Call;
import retrofit2.Callback;

public class AuthFragment extends Fragment {
    private FragmentAuthBinding binding;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAuthBinding.inflate(inflater);
        View view = binding.getRoot();

        Button btnEnter = binding.btnEnter;
        Button btnQrCode = binding.btnQrCode;

        etEmail = binding.etEmail;
        etPassword = binding.etPassword;

        Bundle arguments = getArguments();

        if(arguments != null){
            String email = arguments.getString(QrCodeRunnable.EMAIL_KEY);
            String password = arguments.getString(QrCodeRunnable.PASSWORD_KEY);

            email = email.substring(1, email.length() - 1 );
            password = password.substring(1, password.length() - 1 );

            Toast.makeText(getContext(), String.format("email: %s\npwd: %s", email, password), Toast.LENGTH_LONG).show();

            etEmail.setText(email);
            etPassword.setText(password);
        }

        btnEnter.setOnClickListener(this::enterToApplication);
        btnQrCode.setOnClickListener(this::navigateToQrCodeFragment);
        return view;
    }

    private void enterToApplication(View v){
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        PersonEntity person = new PersonEntity(email, password);

        sendRequestOnAuth(person);
    }

    private void sendRequestOnAuth(PersonEntity person){
        HttpBuilder httpBuilder = new HttpBuilder();
        PersonRepository repository = httpBuilder.createService(PersonRepository.class);

        Call<SecuritySuccessfulEntity> authCall = repository.login(person);

        Callback<SecuritySuccessfulEntity> callback = new LoginCallback(getContext());

        authCall.enqueue(callback);
    }

    private void navigateToQrCodeFragment(View v){
        NavController navController = Navigation.findNavController(binding.getRoot());
        navController.navigate(R.id.action_authFragment_to_qrCodeFragment);
    }
}