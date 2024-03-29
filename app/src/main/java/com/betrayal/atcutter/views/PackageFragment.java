package com.betrayal.atcutter.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.betrayal.atcutter.R;
import com.betrayal.atcutter.adapters.PackageAdapter;
import com.betrayal.atcutter.adapters.PartyHeaderAdapter;
import com.betrayal.atcutter.callbacks.InsideCallback;
import com.betrayal.atcutter.callbacks.PackageGetAllCallback;
import com.betrayal.atcutter.databinding.FragmentPackageBinding;
import com.betrayal.atcutter.models.PackageEntity;
import com.betrayal.atcutter.scripts.model.SectionPackage;
import com.betrayal.atcutter.server.HttpBuilder;
import com.betrayal.atcutter.server.repositories.PackageRepository;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;

public class PackageFragment extends Fragment {
    private FragmentPackageBinding binding;
    public PackageFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPackageBinding.inflate(inflater);

        final RecyclerView packageList = binding.packageList;

        final HttpBuilder httpBuilder = new HttpBuilder();
        final PackageRepository repository = httpBuilder.createService(PackageRepository.class);
        final Call<List<PackageEntity>> packageCall = repository.getAll(httpBuilder.getAuthorizationHeader());
        final PackageGetAllCallback callback = new PackageGetAllCallback(getContext(), packageList);
        List<PackageEntity> packages = new ArrayList<>();

        callback.subscribe(items -> {
            packages.clear();
            packages.addAll(items);
        });

        packageCall.enqueue(callback);
        EditText search = binding.search;


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                
            }

            @Override
            public void afterTextChanged(Editable s) {
                List<PackageEntity> arrayFiltered = packages.stream().filter(x->x.getParty().getCutNumber().contains(s.toString())).collect(Collectors.toList());
                List<SectionPackage> sectionList = new ArrayList<>();
                Hashtable<String, List<PackageEntity>> hashtable = new Hashtable<>();
                for (PackageEntity packageEntity: arrayFiltered){
                    String cutNumber = packageEntity.getParty().getCutNumber();
                    List<PackageEntity> list = hashtable.get(cutNumber);
                    if(list == null){
                        list = new ArrayList<>();
                        hashtable.put(cutNumber, list);
                    }
                    list.add(packageEntity);
                }

                for (String key: hashtable.keySet()){
                    SectionPackage sectionPackage = new SectionPackage(key);
                    List<PackageEntity> list = hashtable.get(key);
                    sectionPackage.addAll(list);
                    sectionList.add(sectionPackage);
                }


                PartyHeaderAdapter adapter = new PartyHeaderAdapter(sectionList);
                packageList.setAdapter(adapter);
                packageList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            }
        });
        binding.navigationButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(binding.getRoot());
            navController.navigate(R.id.action_packageFragment_to_packageHandlerFragment);
        });
        return binding.getRoot();
    }
}