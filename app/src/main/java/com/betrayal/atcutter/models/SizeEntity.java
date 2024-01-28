package com.betrayal.atcutter.models;

public class SizeEntity extends Entity {
    private String number;
    private int ageId;
    private AgeEntity age;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAgeId() {
        return ageId;
    }

    public void setAgeId(int ageId) {
        this.ageId = ageId;
    }

    public AgeEntity getAge() {
        return age;
    }

    public void setAge(AgeEntity age) {
        this.age = age;
    }
}
