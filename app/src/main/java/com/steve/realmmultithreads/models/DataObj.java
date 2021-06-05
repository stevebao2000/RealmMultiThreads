package com.steve.realmmultithreads.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class DataObj extends RealmObject {

    @PrimaryKey
    @Required
    private Long id;

    // suppose a and b >= 0;
    private int a = AppConstants.invalidvalue;
    private int b = AppConstants.invalidvalue;
    private int amount = 0;

    public void setId(Long id) {
        if (this.id != null) {
            throw new RuntimeException("id can not be changed");
        }
        this.id = id;
    }

    public void increaseAmount(int increment) {
        amount += increment;
    }

    public Long getId() {
        return id;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getAmount() {
        return amount;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String to_string() {
        return "a = " + a + ", b = " + b + ", amount = " + amount;
    }
}

