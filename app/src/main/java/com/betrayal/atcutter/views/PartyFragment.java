package com.betrayal.atcutter.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.betrayal.atcutter.adapters.PartyAdapter;
import com.betrayal.atcutter.databinding.FragmentPartyBinding;
import com.betrayal.atcutter.models.Model;
import com.betrayal.atcutter.models.Party;
import com.betrayal.atcutter.models.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartyFragment extends Fragment {
    private FragmentPartyBinding binding;
    public PartyFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPartyBinding.inflate(inflater);
        View view = binding.getRoot();

        GridView partyList = binding.gvParties;
        List<Party> parties = new ArrayList<>();
        Party party = new Party();
        party.setDefected(false);
        party.setCutNumber("21");
        party.setModel(new Model());
        party.setDateStart(new Date());
        party.setDateEnd(new Date());
        party.setPerson(new Person());
        parties.add(party);
        parties.add(party);
        parties.add(party);
        parties.add(party);
        parties.add(party);
        parties.add(party);
        parties.add(party);
        parties.add(party);
        parties.add(party);
        ArrayAdapter<Party> adapter = new PartyAdapter(getContext(), parties);
        partyList.setAdapter(adapter);
        return view;
    }
}