package org.aeroflot.model;

public class Airplane {
    private String model;
    private int capacity;
    private int range;

    public Airplane(String model, int capacity, int range) {
        this.model = model;
        this.capacity = capacity;
        this.range = range;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRange() {
        return range;
    }

    @Override
    public String toString() {
        return "Airplane [Model=" + model + ", Capacity=" + capacity + ", Range=" + range + " км]";
    }
}
