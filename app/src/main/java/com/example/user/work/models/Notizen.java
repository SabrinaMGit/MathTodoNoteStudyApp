package com.example.user.work.models;

public class Notizen {
    private int id;
    private String note;
    private String title;
    private boolean favorite;

    public Notizen(int id, String note, String title, boolean favorite){
        this.id = id;
        this.note = note;
        this.title = title;
        this.favorite = favorite;
    }

    public Notizen(Notizen notizen){
        this.id = notizen.id;
        this.note = notizen.note;
        this.title = notizen.title;
        this.favorite = notizen.favorite;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public String getTitle() {
        return title;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
