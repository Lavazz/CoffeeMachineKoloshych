package by.trjava.kaloshych.builder.impl;

import by.trjava.kaloshych.builder.AdditionalIngredientBuilder;
import by.trjava.kaloshych.entity.AdditionalIngredient;

public class AdditionalIngredientBuilderImpl implements AdditionalIngredientBuilder {

    private int idComponent;
    private String nameComponent;
    private int portion;
    private String picturePath;
    private int calories;

    public AdditionalIngredientBuilderImpl() {
    }

    @Override
    public AdditionalIngredient build() {
        AdditionalIngredient additionalIngredient = new AdditionalIngredient();
        additionalIngredient.setIdComponent(idComponent);
        additionalIngredient.setNameComponent(nameComponent);
        additionalIngredient.setPortion(portion);
        additionalIngredient.setPicturePath(picturePath);
        additionalIngredient.setCalories(calories);
        return additionalIngredient;
    }

    @Override
    public AdditionalIngredientBuilder withIdComponent(int idComponent) {
        this.idComponent = idComponent;
        return this;
    }

    @Override
    public AdditionalIngredientBuilder withNameComponent(String nameComponent) {
        this.nameComponent = nameComponent;
        return this;
    }

    @Override
    public AdditionalIngredientBuilder withPortion(int portion) {
        this.portion = portion;
        return this;
    }

    @Override
    public AdditionalIngredientBuilder withPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return this;
    }

    @Override
    public AdditionalIngredientBuilder withCalories(int calories) {
        this.calories = calories;
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

    public int getCalories() {
        return calories;
    }
}
