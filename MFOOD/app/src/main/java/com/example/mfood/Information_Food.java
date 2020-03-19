package com.example.mfood;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Information_Food implements Serializable {
    private String nameFood;
    private String desFood;
    private double priceFood;
    private String imgFood;

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getDesFood() {
        return desFood;
    }

    public void setDesFood(String desFood) {
        this.desFood = desFood;
    }

    public double getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(double priceFood) {
        this.priceFood = priceFood;
    }

    public String getImgFood() {
        return imgFood;
    }

    public void setImgFood(String imgFood) {
        this.imgFood = imgFood;
    }

    public Information_Food(String nameFood, String desFood, Double priceFood, String imgFood) {
        this.nameFood = nameFood;
        this.desFood = desFood;
        this.priceFood = priceFood;
        this.imgFood = imgFood;
    }
}
