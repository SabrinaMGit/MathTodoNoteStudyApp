package com.example.user.work.models;

public class BlitzrechnenModels {
    private int mathe_id;
    private int id;
    private int zahl1;
    private int zahl2;
    private int ergebnis;
    private String grundrechenart;
    private String isRichtig;
    private int punkte;

/*
    public blitzrechnenModels(int mathe_id, int id, int zahl1, int zahl2, int ergebnis, String grundrechenart, String isRichtig, int punkte){
        this.mathe_id = mathe_id;
        this.id = id;
        this.zahl1 = zahl1;
        this.zahl2 = zahl2;
        this.ergebnis = ergebnis;
        this.grundrechenart = grundrechenart;
        this.isRichtig = isRichtig;
        this.punkte = punkte;
    }
    public blitzrechnenModels(blitzrechnenModels blitzrechnenModels){
        this.mathe_id = blitzrechnenModels.mathe_id;
        this.id = blitzrechnenModels.id;
        this.zahl1 = blitzrechnenModels.zahl1;
        this.zahl2 = blitzrechnenModels.zahl2;
        this.ergebnis = blitzrechnenModels.ergebnis;
        this.grundrechenart = blitzrechnenModels.grundrechenart;
        this.isRichtig = blitzrechnenModels.isRichtig;
        this.punkte = blitzrechnenModels.punkte;
    }
*/

    public int getMathe_id() { return mathe_id; }

    public void setMathe_id(int mathe_id) { this.mathe_id = mathe_id; }

    public void setIsRichtig(String isRichtig) { this.isRichtig = isRichtig; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZahl1() {
        return zahl1;
    }

    public void setZahl1(int zahl1) {
        this.zahl1 = zahl1;
    }

    public int getZahl2() {
        return zahl2;
    }

    public void setZahl2(int zahl2) {
        this.zahl2 = zahl2;
    }

    public int getErgebnis() {
        return ergebnis;
    }

    public void setErgebnis(int ergebnis) {
        this.ergebnis = ergebnis;
    }

    public String getGrundrechenart() {
        return grundrechenart;
    }

    public void setGrundrechenart(String grundrechenart) {
        this.grundrechenart = grundrechenart;
    }

    public String getIsRichtig() {
        return isRichtig;
    }

    public void setIsRichtig(boolean solve) {
        isRichtig = isRichtig;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }
}
