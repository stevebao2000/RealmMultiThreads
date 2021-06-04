package com.steve.realmmultithreads.models;

public class DataQuery {
    private int valueA = -1;
    private int valueB = -1;

    public void setValueA(int valueA) {
        this.valueA = valueA;
    }

    public void setValueB(int valueB) {
        this.valueB = valueB;
    }

    public void updateQuery(DataObj data) {
        if (valueA >= 0)
            data.setA(valueA);
        if (valueB >= 0)
            data.setB(valueB);
    }
}
