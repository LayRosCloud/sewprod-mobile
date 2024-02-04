package com.betrayal.atcutter.models;

import java.util.List;

public class ModelEntity extends Entity {
    private String title = "Треники";
    private String description;
    private String codeVendor;
    private List<OperationEntity> operations;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCodeVendor() {
        return codeVendor;
    }

    public void setCodeVendor(String codeVendor) {
        this.codeVendor = codeVendor;
    }


    public List<OperationEntity> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationEntity> operations) {
        this.operations = operations;
    }
}
