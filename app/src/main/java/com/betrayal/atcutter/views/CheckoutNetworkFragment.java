package com.betrayal.atcutter.views;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.databinding.FragmentCheckoutNetworkBinding;
import com.betrayal.atcutter.scripts.data.DatabaseHelper;
import com.betrayal.atcutter.scripts.data.constants.DatabaseConstants;

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

        //TODO: other class
        DatabaseHelper helper = new DatabaseHelper(getContext());
        SQLiteDatabase writableData = helper.getWritableDatabase();
        writableData.execSQL(DatabaseConstants.ENSURE_CREATED);

        NavController navController = Navigation.findNavController(binding.getRoot());
        navController.navigate(R.id.action_checkoutNetworkFragment_to_pincodeFragment);
    }
}