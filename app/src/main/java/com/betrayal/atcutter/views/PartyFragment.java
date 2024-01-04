package com.betrayal.atcutter.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.betrayal.atcutter.callbacks.PartyGetAllCallback;
import com.betrayal.atcutter.databinding.FragmentPartyBinding;
import com.betrayal.atcutter.models.Party;
import com.betrayal.atcutter.server.HttpBuilder;
import com.betrayal.atcutter.server.repositories.PartyRepository;
import com.betrayal.atcutter.server.ServerConstants;

import java.util.List;

import retrofit2.Call;
public class PartyFragment extends Fragment {
    private FragmentPartyBinding binding;
    public PartyFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPartyBinding.inflate(inflater);
        View view = binding.getRoot();

        GridView partyList = binding.gvParties;
        HttpBuilder httpBuilder = new HttpBuilder();

        PartyRepository repository = httpBuilder.createService(PartyRepository.class);
        String authToken = "Bearer "+ServerConstants.User.getToken();
        Call<List<Party>> partyCall = repository.getAll(authToken);

        partyCall.enqueue(new PartyGetAllCallback(getContext(), partyList));

        return view;
    }
}