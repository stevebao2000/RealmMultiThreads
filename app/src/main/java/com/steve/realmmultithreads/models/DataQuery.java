package com.steve.realmmultithreads.models;

public class DataQuery {
    private int valueA = AppConstants.invalidvalue;
    private int valueB = AppConstants.invalidvalue;
    private int increment = AppConstants.invalidvalue;

    public void setValueA(int valueA) {
        this.valueA = valueA;
    }

    public void setValueB(int valueB) {
        this.valueB = valueB;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public void updateQuery(DataObj data) {
        if (valueA != AppConstants.invalidvalue)
            data.setA(valueA);

        if (valueB != AppConstants.invalidvalue)
            data.setB(valueB);

        if (increment != AppConstants.invalidvalue)
            data.increaseAmount(increment);
    }
}
