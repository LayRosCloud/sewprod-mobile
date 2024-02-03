package com.betrayal.atcutter.views;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.databinding.FragmentCheckoutNetworkBinding;
import com.betrayal.atcutter.models.UserDataEntity;
import com.betrayal.atcutter.scripts.data.DatabaseHelper;
import com.betrayal.atcutter.scripts.data.constants.DatabaseConstants;
import com.betrayal.atcutter.scripts.services.UserFinder;
import com.betrayal.atcutter.scripts.services.UserRegister;

import java.util.List;

public class CheckoutNetworkFragment extends Fragment {

    private FragmentCheckoutNetworkBinding binding;
    public CheckoutNetworkFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCheckoutNetworkBinding.inflate(inflater);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        UserRegister register = new UserRegister(getContext());
        register.ensureCreated();

        UserFinder finder = new UserFinder(getContext());
        Log.d("DB_TAG", "enter");
        List<UserDataEntity> users = finder.findAll();
        Log.d("DB_TAG", String.valueOf(users.size()));
        NavController navController = Navigation.findNavController(binding.getRoot());

        if(users.size() > 0){
            navController.navigate(R.id.action_checkoutNetworkFragment_to_pincodeFragment);
        } else{
            navController.navigate(R.id.action_checkoutNetworkFragment_to_authFragment);
        }
    }
}