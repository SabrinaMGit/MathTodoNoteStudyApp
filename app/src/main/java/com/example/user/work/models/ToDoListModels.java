package com.example.user.work.models;

public class ToDoListModels {
    private int todoid;
    private int id;
    private String name;
    private String notizen;
    private String liste;
    private String datum;
    private String errinern;
    private String prioritaet;
    private String highlight;
    private String abgeschlossen;

    /*public ToDoListModels(int id,
            String name,
            String notizen,
            String liste,
            String datum,
            String errinern,
            String prioritaet,
            String highlight,
            String abgeschlossen){
        this.id = id;
        this.name = name;
        this.notizen = notizen;
        this.liste = liste;
        this.datum = datum;
        this.errinern = errinern;
        this.prioritaet = prioritaet;
        this.highlight = highlight;
        this.abgeschlossen = abgeschlossen;
    }*/

    public int getTodoid() { return todoid; }

    public void setTodoid(int todoid) { this.todoid = todoid; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotizen() {
        return notizen;
    }

    public void setNotizen(String notizen) {
        this.notizen = notizen;
    }

    public String getListe() {
        return liste;
    }

    public void setListe(String liste) {
        this.liste = liste;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getErrinern() {
        return errinern;
    }

    public void setErrinern(String errinern) {
        this.errinern = errinern;
    }

    public String getPrioritaet() {
        return prioritaet;
    }

    public void setPrioritaet(String prioritaet) {
        this.prioritaet = prioritaet;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getAbgeschlossen() {
        return abgeschlossen;
    }

    public void setAbgeschlossen(String abgeschlossen) {
        this.abgeschlossen = abgeschlossen;
    }
}
