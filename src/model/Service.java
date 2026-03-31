package model;

import java.io.Serializable;

/**
 * Represents an add-on service requested by a customer.
 */
public class Service implements Serializable {
    private String name;
    private double price;

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}
