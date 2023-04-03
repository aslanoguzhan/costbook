package com.example.costbook.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GardenProducts {

    private int id;
    private int userID;
    private int productID;
    private int gardenID;
    private int totalproduction;

    public GardenProducts(int id, int userID, int productID, int gardenID, int totalproduction){
        this.id=id;
        this.userID=userID;
        this.productID=productID;
        this.gardenID=gardenID;
        this.totalproduction=totalproduction;
    }

}
