package ru.bkmz.util;

public class Address {
    char abc;
    int id;

    public Address(char abc, int id) {
        this.abc = abc;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getAbc() {
        return abc;
    }

    public void setAbc(char abc) {
        this.abc = abc;
    }
}
