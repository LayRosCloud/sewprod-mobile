package com.betrayal.atcutter.callbacks;

import android.content.Context;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.betrayal.atcutter.adapters.PackageAdapter;
import com.betrayal.atcutter.adapters.PartyHeaderAdapter;
import com.betrayal.atcutter.models.PackageEntity;
import com.betrayal.atcutter.models.PartyEntity;
import com.betrayal.atcutter.scripts.ExceptionConstants;
import com.betrayal.atcutter.scripts.model.SectionPackage;
import com.betrayal.atcutter.server.HttpBuilder;
import com.betrayal.atcutter.server.repositories.PartyRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Response;

public class PackageGetAllCallback extends CallbackWrapper<List<PackageEntity>> {
    private final RecyclerView listView;
    private InsideCallback<List<PackageEntity>> callback;
    public PackageGetAllCallback(@NonNull Context context, @NonNull RecyclerView listView){
        super(context);
        this.listView = listView;
        setCanDisableLoadingDialog(false);
        showLoadingDialog();
    }

    public void subscribe(InsideCallback<List<PackageEntity>> callback){
        this.callback = callback;
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
            if(this.callback != null){
                this.callback.success(packages);
            }
        });

        packageCall.enqueue(callback);
    }

    private void initialPartyItemOnEveryPackage(List<PartyEntity> partyList, List<PackageEntity> packageList){
        final Map<Integer, PartyEntity> partyMap = convertListToMap(partyList);
        connectPartyToPackage(packageList, partyMap);
        Date now = new Date();
        packageList = packageList.stream()
                .filter(x -> x.getParty().getDateStart().getYear() >= now.getYear() && x.getParty().getDateStart().getYear() <= (now.getYear() + 1))
                .collect(Collectors.toList());

        List<SectionPackage> sectionList = new ArrayList<>();
        Hashtable<String, List<PackageEntity>> hashtable = new Hashtable<>();
        for (PackageEntity packageEntity: packageList){
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


        PartyHeaderAdapter packageAdapter = new PartyHeaderAdapter(sectionList);
        listView.setAdapter(packageAdapter);
        listView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
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
