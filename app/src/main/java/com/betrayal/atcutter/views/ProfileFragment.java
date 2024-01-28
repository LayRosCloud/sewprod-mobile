package com.betrayal.atcutter.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.betrayal.atcutter.callbacks.ProfileCallback;
import com.betrayal.atcutter.databinding.FragmentProfileBinding;
import com.betrayal.atcutter.models.PersonEntity;
import com.betrayal.atcutter.scripts.ProfileHandler;
import com.betrayal.atcutter.server.HttpBuilder;
import com.betrayal.atcutter.server.ServerConstants;
import com.betrayal.atcutter.server.repositories.PersonRepository;

import retrofit2.Call;
import retrofit2.Callback;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public ProfileFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater);

        TextView fullName = binding.fullName;
        TextView email = binding.email;
        TextView date = binding.registrationDate;
        TextView uid = binding.uid;

        ProfileHandler handler = new ProfileHandler(email, fullName, uid, date);

        HttpBuilder httpBuilder = new HttpBuilder();
        PersonRepository repository = httpBuilder.createService(PersonRepository.class);
        Callback<PersonEntity> personCallback = new ProfileCallback(getContext(), handler);
        Call<PersonEntity> personCall = repository.get(ServerConstants.CurrentUser.getId(), httpBuilder.getAuthorizationHeader());
        personCall.enqueue(personCallback);

        return binding.getRoot();
    }
}