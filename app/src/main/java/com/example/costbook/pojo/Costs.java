package com.example.costbook.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Costs {

    private int costID;
    private int userID;
    private int productID;
    private int gardenID;
    private int machine;
    private int seed;
    private int pesticide;
    private int chemicalfertilizers;
    private int organicfertilizers;
    private int irrigation;
    private int laborforce;
    private int fuel;
    private String date;


    public Costs(int costID,int userID,int productID,int gardenID, int machine, int seed, int pesticide, int chemicalfertilizers,int organicfertilizers,int irrigation,int laborforce,int fuel,String date) {
        this.costID = costID;
        this.userID=userID;
        this.productID=productID;
        this.gardenID=gardenID;
        this.machine= machine;
        this.seed=seed;
        this.pesticide=pesticide;
        this.chemicalfertilizers=chemicalfertilizers;
        this.organicfertilizers=organicfertilizers;
        this.irrigation=irrigation;
        this.laborforce=laborforce;
        this.fuel=fuel;
        this.date=date;




    }


}
