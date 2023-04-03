package com.example.costbook.pojo;

import com.google.gson.annotations.SerializedName;

public class MounthCosts{

    private int ay;
    private int yıl;
    @SerializedName("machine")
    private int machine;
    @SerializedName("tohum")
    private int seed;
    @SerializedName("ilaç")
    private int pesticide;
    @SerializedName("gübre")
    private int chemicalfertilizers;
    @SerializedName("organik_gübre")
    private int organicfertilizers;
    @SerializedName("sulama")
    private int irrigation;
    @SerializedName("isgücü")
    private int laborforce;
    @SerializedName("yakıt")
    private int fuel;

    public MounthCosts() {
    }

    public int getAy() {
        return ay;
    }

    public void setAy(int ay) {
        this.ay = ay;
    }

    public int getYıl() {
        return yıl;
    }

    public void setYıl(int yıl) {
        this.yıl = yıl;
    }

    public int getMachine() {
        return machine;
    }

    public void setMachine(int machine) {
        this.machine = machine;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public int getPesticide() {
        return pesticide;
    }

    public void setPesticide(int pesticide) {
        this.pesticide = pesticide;
    }

    public int getChemicalfertilizers() {
        return chemicalfertilizers;
    }

    public void setChemicalfertilizers(int chemicalfertilizers) {
        this.chemicalfertilizers = chemicalfertilizers;
    }

    public int getOrganicfertilizers() {
        return organicfertilizers;
    }

    public void setOrganicfertilizers(int organicfertilizers) {
        this.organicfertilizers = organicfertilizers;
    }

    public int getIrrigation() {
        return irrigation;
    }

    public void setIrrigation(int irrigation) {
        this.irrigation = irrigation;
    }

    public int getLaborforce() {
        return laborforce;
    }

    public void setLaborforce(int laborforce) {
        this.laborforce = laborforce;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }
}
