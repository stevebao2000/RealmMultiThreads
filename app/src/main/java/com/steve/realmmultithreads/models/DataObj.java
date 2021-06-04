package com.steve.realmmultithreads.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class DataObj extends RealmObject {

    @PrimaryKey
    @Required
    private Long id;

    // suppose a and b >= 0;
    private int a = 0;
    private int b = 0;

    public void setId(Long id) {
        if (this.id != null) {
            throw new RuntimeException("id can not be changed");
        }
        this.id = id;
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

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }
}

