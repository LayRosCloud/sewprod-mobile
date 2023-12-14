package com.betrayal.atcutter.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.databinding.FragmentCheckoutNetworkBinding;

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
        NavController navController = Navigation.findNavController(binding.getRoot());
        navController.navigate(R.id.action_checkoutNetworkFragment_to_authFragment);
    }
}