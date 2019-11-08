package com.dennn66.phonebook;

public class PhonebookRecord {
    int id;
    String name;

    public PhonebookRecord(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PhonebookRecord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
