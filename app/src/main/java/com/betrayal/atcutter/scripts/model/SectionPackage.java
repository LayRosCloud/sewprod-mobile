package com.betrayal.atcutter.scripts.model;

import com.betrayal.atcutter.models.PackageEntity;

import java.util.ArrayList;
import java.util.List;

public class SectionPackage {
    private String header;
    private final List<PackageEntity> packageEntities;

    public SectionPackage(String header) {
        this.header = header;
        this.packageEntities = new ArrayList<>();
    }

    public List<PackageEntity> getPackageEntities() {
        return packageEntities;
    }

    public void add(PackageEntity packageEntity){
        packageEntities.add(packageEntity);
    }

    public void addAll(List<PackageEntity> packages){
        packageEntities.addAll(packages);
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
