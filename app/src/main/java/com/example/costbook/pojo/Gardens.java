package com.example.costbook.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Gardens {


    private int gardenID;
    private int userID;
    private String gardenName;
    private int ada;
    private int parsel;
    private String city;
    private String town;
    private String district;
    private int area;


    public Gardens(String gardenName, int gardenID, int userID, int ada, int parsel, String city, String town, String district, int area) {


        this.gardenID = gardenID;
        this.userID = userID;
        this.gardenName = gardenName;
        this.ada = ada;
        this.parsel = parsel;
        this.city = city;
        this.town = town;
        this.district = district;
        this.area = area;


    }


    public int getGardenID() {
        return gardenID;
    }

    public void setGardenID(int gardenID) {
        this.gardenID = gardenID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    public String getGardenName() {
        return gardenName;
    }

    public void setGardenName(String gardenName) {
        this.gardenName = gardenName;
    }

    public int getAda() {
        return ada;
    }

    public void setAda(int ada) {
        this.ada = ada;
    }

    public int getParsel() {
        return parsel;
    }

    public void setParsel(int parsel) {
        this.parsel = parsel;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

}
