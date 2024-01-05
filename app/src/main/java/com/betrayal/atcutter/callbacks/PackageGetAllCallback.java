package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.betrayal.atcutter.adapters.PackageAdapter;
import com.betrayal.atcutter.models.PackageEntity;
import com.betrayal.atcutter.models.PartyEntity;
import com.betrayal.atcutter.scripts.ExceptionConstants;
import com.betrayal.atcutter.server.HttpBuilder;
import com.betrayal.atcutter.server.repositories.PartyRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class PackageGetAllCallback extends CallbackWrapper<List<PackageEntity>> {
    private final ListView listView;
    public PackageGetAllCallback(@NonNull Context context, @NonNull ListView listView){
        super(context);
        this.listView = listView;
        setCanDisableLoadingDialog(false);
        showLoadingDialog();
    }

    @Override
    protected void successResponse(Response<List<PackageEntity>> item) {
        final List<PackageEntity> packages = item.body();
        final HttpBuilder httpBuilder = new HttpBuilder();
        final PartyRepository repository = httpBuilder.createService(PartyRepository.class);
        final Call<List<PartyEntity>> packageCall = repository.getAll(httpBuilder.getAuthorizationHeader());
        final PartyGetAllCallback callback = new PartyGetAllCallback(context);
        callback.subscribe(partyList -> {
            initialPartyItemOnEveryPackage(partyList, packages);
        });
        packageCall.enqueue(callback);
    }

    private void initialPartyItemOnEveryPackage(List<PartyEntity> partyList, List<PackageEntity> packageList){
        final Map<Integer, PartyEntity> partyMap = convertListToMap(partyList);
        connectPartyToPackage(packageList, partyMap);

        ArrayAdapter<PackageEntity> packageAdapter = new PackageAdapter(context, packageList);
        listView.setAdapter(packageAdapter);
        dismissDialog();
    }
    private Map<Integer, PartyEntity> convertListToMap(List<PartyEntity> list){
        final Map<Integer, PartyEntity> map = new HashMap<>();
        for (PartyEntity item:
                list) {
            map.put(item.getId(), item);
        }
        return map;
    }

    private void connectPartyToPackage(List<PackageEntity> packages, Map<Integer,PartyEntity> partyMap){
        for (PackageEntity packageItem:
                packages) {
            packageItem.setParty(partyMap.get(packageItem.getPartyId()));
        }
    }

    @Override
    protected String getErrorMessage() {
        return ExceptionConstants.EXCEPTION_NETWORK;
    }
}
