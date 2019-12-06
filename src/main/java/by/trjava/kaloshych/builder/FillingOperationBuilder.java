package by.trjava.kaloshych.builder;

import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.entity.FillingOperation;

public interface FillingOperationBuilder {
    FillingOperation build();

    FillingOperationBuilder withComponent(Component component);

    FillingOperationBuilder withMaxPortion(int maxPortion);
}
