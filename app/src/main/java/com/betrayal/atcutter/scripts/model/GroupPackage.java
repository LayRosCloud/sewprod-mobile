package com.betrayal.atcutter.scripts.model;

import com.betrayal.atcutter.models.PackageEntity;

import java.util.ArrayList;
import java.util.List;

public class GroupPackage {
    private String date;
    private final List<PackageEntity> packageEntities;

    public GroupPackage(String date) {
        this.date = date;
        this.packageEntities = new ArrayList<>();
    }

    public List<PackageEntity> getPackageEntities() {
        return packageEntities;
    }

    public void add(PackageEntity packageEntity){
        packageEntities.add(packageEntity);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
