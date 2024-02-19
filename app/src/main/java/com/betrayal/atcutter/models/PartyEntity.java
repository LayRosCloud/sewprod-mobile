package com.betrayal.atcutter.models;

import androidx.annotation.NonNull;

import java.util.Date;

public class PartyEntity extends Entity {
    private int modelId;
    private int personId;
    private int count;
    private PriceEntity price;
    private Date dateStart;
    private Date dateEnd;
    private boolean isDefected;
    private int sizeId;
    private String cutNumber;

    private PersonEntity person;
    private ModelEntity model;
    public PartyEntity() {
    }


    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        if(modelId < 0){
            throw new IllegalArgumentException();
        }
        this.modelId = modelId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        if(personId < 0){
            throw new IllegalArgumentException();
        }
        this.personId = personId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if(count < 0){
            throw new IllegalArgumentException();
        }
        this.count = count;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isDefected() {
        return isDefected;
    }

    public void setDefected(boolean defected) {
        isDefected = defected;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        if(sizeId < 0){
            throw new IllegalArgumentException();
        }
        this.sizeId = sizeId;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(@NonNull PersonEntity person) {
        this.person = person;
    }

    public String getCutNumber() {
        return cutNumber;
    }

    public void setCutNumber(String cutNumber) {
        this.cutNumber = cutNumber;
    }

    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public PriceEntity getPrice() {
        return price;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }
}
