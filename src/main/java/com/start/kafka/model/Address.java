package com.start.kafka.model;

import java.io.Serializable;


public class Address {
    String add;

    public Address() {
    }

    public Address(String add) {
        this.add = add;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    public String toString() {
        return "Address{" +
                "add='" + add + '\'' +
                '}';
    }
}
