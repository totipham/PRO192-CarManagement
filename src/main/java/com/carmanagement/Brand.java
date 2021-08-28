/**
 * @author DucPTM
 */

 package com.carmanagement;

public class Brand {
    private String brandID, brandName, soundBrand;
    private double price;

    Brand () {

    }

    Brand (String brandID, String brandName, String soundBrand, double price) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }
    
    public String getBrandID () {
        return brandID;
    }

    public String getBrandName () {
        return brandName;
    }

    public void setUpdatedBrand (String brandName, String soundBrand, double price) {
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }

    @Override
    public String toString () {
        return brandID + "," + brandName + "," + soundBrand + ":" + price; 
    }
}
