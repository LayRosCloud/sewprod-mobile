package com.betrayal.atcutter.views;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.betrayal.atcutter.callbacks.CreatedRangePackagesCallback;
import com.betrayal.atcutter.callbacks.MaterialSpinnerCallback;
import com.betrayal.atcutter.callbacks.PartySpinnerCallback;
import com.betrayal.atcutter.callbacks.SizeSpinnerCallback;
import com.betrayal.atcutter.databinding.FragmentPackageHandlerBinding;
import com.betrayal.atcutter.models.MaterialEntity;
import com.betrayal.atcutter.models.PackageEntity;
import com.betrayal.atcutter.models.PartyEntity;
import com.betrayal.atcutter.models.SizeEntity;
import com.betrayal.atcutter.scripts.CloningComponentHelper;
import com.betrayal.atcutter.scripts.TableControllerAdder;
import com.betrayal.atcutter.server.HttpBuilder;
import com.betrayal.atcutter.server.ServerConstants;
import com.betrayal.atcutter.server.repositories.MaterialRepository;
import com.betrayal.atcutter.server.repositories.PackageRepository;
import com.betrayal.atcutter.server.repositories.PartyRepository;
import com.betrayal.atcutter.server.repositories.SizeRepository;
import com.betrayal.atcutter.views.dialogues.MessageDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class PackageHandlerFragment extends Fragment {
    private FragmentPackageHandlerBinding binding;

    private TableLayout table;
    private EditText counter;
    private Spinner materials;
    private Spinner parties;
    private Spinner sizes;
    private TableRow row;

    private CloningComponentHelper cloningComponentHelper;
    private TableControllerAdder tableController;
    private final HttpBuilder httpBuilder;

    public PackageHandlerFragment() {
        httpBuilder = new HttpBuilder();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPackageHandlerBinding.inflate(inflater);

        table = binding.table;
        counter = binding.counter;
        materials = binding.materials;
        parties = binding.parties;
        sizes = binding.sizes;
        row = binding.tableRow;

        cloningComponentHelper = new CloningComponentHelper(getContext());
        tableController = new TableControllerAdder(table);

        Button addedButton = binding.addedButton;
        Button saveButton = binding.saveButton;
        addedButton.setOnClickListener(this::addNewRow);
        saveButton.setOnClickListener(this::save);
        initial();
        return binding.getRoot();
    }

    private void initial(){
        initialSpinnerParties();
        initialSpinnerSizes();
        initialSpinnerMaterials();
    }

    private void initialSpinnerParties(){
        PartyRepository repository = httpBuilder.createService(PartyRepository.class);
        Call<List<PartyEntity>> partyCall = repository.getAll(httpBuilder.getAuthorizationHeader());
        Callback<List<PartyEntity>> callback = new PartySpinnerCallback(getContext(), parties);
        partyCall.enqueue(callback);
    }

    private void initialSpinnerSizes(){
        SizeRepository repository = httpBuilder.createService(SizeRepository.class);
        Call<List<SizeEntity>> sizeCall = repository.getAll(httpBuilder.getAuthorizationHeader());
        Callback<List<SizeEntity>> callback = new SizeSpinnerCallback(getContext(), sizes);
        sizeCall.enqueue(callback);
    }

    private void initialSpinnerMaterials(){
        MaterialRepository repository = httpBuilder.createService(MaterialRepository.class);
        Call<List<MaterialEntity>> materialCall = repository.getAll(httpBuilder.getAuthorizationHeader());
        Callback<List<MaterialEntity>> callback = new MaterialSpinnerCallback(getContext(), materials);
        materialCall.enqueue(callback);
    }

    private void addNewRow(View view){
        EditText newCounter = cloningComponentHelper.clone(counter);
        Spinner newSpinner = cloningComponentHelper.clone(materials);
        TableRow newRow = cloningComponentHelper.clone(row);

        tableController.putInRowComponents(newRow, newCounter, newSpinner);
    }

    private void save(View v){
        try{
            PartyEntity party = (PartyEntity) parties.getSelectedItem();
            SizeEntity size = (SizeEntity) sizes.getSelectedItem();
            List<PackageEntity> packages = getAllPackagesFromComponents(party, size);

            PackageRepository repository = httpBuilder.createService(PackageRepository.class);
            Call<List<PackageEntity>> packageCall = repository.createRange(packages, httpBuilder.getAuthorizationHeader());
            Callback<List<PackageEntity>> packageCallback = new CreatedRangePackagesCallback(getContext(), binding.getRoot());
            packageCall.enqueue(packageCallback);
        }catch (Exception exception){
            Dialog dialog = new MessageDialog(getContext(), "Ошибка валидации", "Выберите все нужные элементы");
            dialog.show();
        }

    }

    private List<PackageEntity> getAllPackagesFromComponents(PartyEntity party, SizeEntity size){
        final int indexOfEditField = 0;
        final int indexOfSpinnerField = 1;

        List<PackageEntity> packages = new ArrayList<>();

        for (int index = 1; index < table.getChildCount() - 1; index++) {
            TableRow currentRow = (TableRow) table.getChildAt(index);
            EditText currentCounter = (EditText)currentRow.getChildAt(indexOfEditField);
            Spinner currentSpinner = (Spinner) currentRow.getChildAt(indexOfSpinnerField);
            MaterialEntity material = (MaterialEntity) currentSpinner.getSelectedItem();

            PackageEntity packageEntity = new PackageEntity();
            packageEntity.setPartyId(party.getId());
            packageEntity.setCount(Integer.parseInt(currentCounter.getText().toString()));
            packageEntity.setMaterialId(material.getId());
            packageEntity.setSizeId(size.getId());
            packageEntity.setPersonId(ServerConstants.CurrentUser.getId());

            packages.add(packageEntity);
        }

        return packages;
    }


}