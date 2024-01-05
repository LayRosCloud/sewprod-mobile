package com.betrayal.atcutter.models;

public abstract class Entity {
    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id){
        if(id < 0){
            throw new IllegalArgumentException();
        }
        this.id = id;
    }
}
