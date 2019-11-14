package com.dennn66.phonebook;

import java.util.HashMap;
import java.util.Map;

public class Phonebook {
    private HashMap<PhonebookRecord, String> phonebook;
    private int pbIndex;

    public Phonebook() {
        phonebook = new HashMap<>();
    }

    public Phonebook get(String name) {
        Phonebook filterdRecords = new Phonebook();
        for(Map.Entry<PhonebookRecord, String> o: phonebook.entrySet()){
            if(o.getKey().getName().equals(name)) filterdRecords.add(o.getKey().getName(), o.getValue());
        }
        return filterdRecords;
    }

    public void add(String name, String phone) {
        phonebook.put(new PhonebookRecord(pbIndex++, name), phone);
    }

    @Override
    public String toString() {
        return "Phonebook{" +
                "phonebook=" + phonebook +
                '}';
    }
}
