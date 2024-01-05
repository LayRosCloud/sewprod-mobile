package com.betrayal.atcutter.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.betrayal.atcutter.callbacks.PackageGetAllCallback;
import com.betrayal.atcutter.databinding.FragmentPackageBinding;
import com.betrayal.atcutter.models.PackageEntity;
import com.betrayal.atcutter.server.HttpBuilder;
import com.betrayal.atcutter.server.repositories.PackageRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class PackageFragment extends Fragment {
    private FragmentPackageBinding binding;
    public PackageFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPackageBinding.inflate(inflater);
        final ListView packageList = binding.packageList;
        final HttpBuilder httpBuilder = new HttpBuilder();
        final PackageRepository repository = httpBuilder.createService(PackageRepository.class);
        final Call<List<PackageEntity>> packageCall = repository.getAll(httpBuilder.getAuthorizationHeader());
        final Callback<List<PackageEntity>> callback = new PackageGetAllCallback(getContext(), packageList);

        packageCall.enqueue(callback);

        return binding.getRoot();
    }
}