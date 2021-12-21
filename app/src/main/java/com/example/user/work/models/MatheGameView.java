package com.example.user.work.models;

public class MatheGameView {

    private int id;
    private String name;
    private int record;
    private int picture;
    private boolean isCrownSet;

    public MatheGameView(int id, String name, int record, int picture, boolean isCrownSet){
        this.id = id;
        this.name = name;
        this.record = record;
        this.picture = picture;
        this.isCrownSet = isCrownSet;
    }

    public MatheGameView(MatheGameView view){
        this.id = view.id;
        this.name = view.name;
        this.record = view.record;
        this.picture = view.picture;
        this.isCrownSet = view.isCrownSet;
    }

    public String getName() {
        return name;
    }

    public int getRecord() {
        return record;
    }

    public int getPicture() {
        return picture;
    }

    public boolean getCrown() {
        return isCrownSet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public void setCrown(boolean crown) {
        this.isCrownSet = crown;
    }
}
