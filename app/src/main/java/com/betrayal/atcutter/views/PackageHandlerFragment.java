package com.betrayal.atcutter.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betrayal.atcutter.databinding.FragmentPackageHandlerBinding;

public class PackageHandlerFragment extends Fragment {

    private FragmentPackageHandlerBinding binding;

    public PackageHandlerFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPackageHandlerBinding.inflate(inflater);

        return binding.getRoot();
    }
}