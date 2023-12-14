package com.betrayal.atcutter.models;

public class Model {
    private int id;
    private String title = "Треники";
    private String description;
    private String codeVendor;
    private int percent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
