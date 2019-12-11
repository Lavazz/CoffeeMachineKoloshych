package by.trjava.kaloshych.builder.impl;

import by.trjava.kaloshych.builder.FillingOperationBuilder;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.entity.FillingOperation;

public class FillingOperationBuilderImpl implements FillingOperationBuilder {
    private int idFillingOperation;
    private Component component;
    private int maxPortion;

    public FillingOperationBuilderImpl() {
    }

    public FillingOperationBuilderImpl(int idFillingOperation) {
        this.idFillingOperation = idFillingOperation;
    }

    @Override
    public FillingOperation build() {
        FillingOperation fillingOperation = new FillingOperation();
        fillingOperation.setIdFillingOperation(idFillingOperation);
        fillingOperation.setComponent(component);
        fillingOperation.setMaxPortion(maxPortion);
        return fillingOperation;
    }

    @Override
    public FillingOperationBuilder withComponent(Component component) {
        this.component = component;
        return this;
    }

    @Override
    public FillingOperationBuilder withMaxPortion(int maxPortion) {
        this.maxPortion = maxPortion;
        return this;
    }

    public int getIdFillingOperation() {
        return idFillingOperation;
    }

    public Component getComponent() {
        return component;
    }

    public int getMaxPortion() {
        return maxPortion;
    }
}
