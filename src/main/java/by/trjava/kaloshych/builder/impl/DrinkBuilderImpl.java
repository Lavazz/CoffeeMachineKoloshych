package by.trjava.kaloshych.builder.impl;

import by.trjava.kaloshych.builder.DrinkBuilder;
import by.trjava.kaloshych.entity.Drink;

public class DrinkBuilderImpl implements DrinkBuilder {
    private int idComponent;
    private String nameComponent;
    private  int portion;
    private String picturePath;
    private double price;
    private String description;

    public DrinkBuilderImpl (){}

    public DrinkBuilderImpl (int idComponent){
        this.idComponent=idComponent;
    }

    @Override
    public Drink build(){
        Drink drink=new Drink();
        drink.setIdComponent(idComponent);
        drink.setNameComponent(nameComponent);
        drink.setPortion(portion);
        drink.setPicturePath(picturePath);
        drink.setPrice(price);
        drink.setDescription(description);
        return drink;
    }

    @Override
    public DrinkBuilder withNameComponent(String nameComponent){
        this.nameComponent=nameComponent;
        return this;
    }

    @Override
    public DrinkBuilder withPortion(int portion){
        this.portion=portion;
        return this;
    }

    @Override
    public DrinkBuilder withPicturePath(String picturePath){
        this.picturePath=picturePath;
        return this;
    }

    @Override
    public DrinkBuilder withPrice(double price){
        this.price=price;
        return this;
    }

    @Override
    public DrinkBuilder withDescription(String description){
        this.description=description;
        return this;
    }

    public int getIdComponent() {
        return idComponent;
    }

    public String getNameComponent() {
        return nameComponent;
    }

    public int getPortion() {
        return portion;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
