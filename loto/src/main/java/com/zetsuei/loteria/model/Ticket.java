package com.zetsuei.loteria.model;

import android.util.Log;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Table(name = "Ticket")
public class Ticket extends Model {
    @Column(name = "dateTime")
    public DateTime dateTime;

    @Column(name = "numbers")
    public String numbers = "";

    @Column(name = "generated")
    public Boolean generated = false;

    public Ticket() {
        Random random = new Random();
        List<Integer> integers = new ArrayList<>(6);
        do {
            int integer = random.nextInt(34) + 1;
            if (!integers.contains(integer)) {
                integers.add(integer);
            }
        } while (integers.size() < 6);
        generated = true;
        dateTime = DateTime.now();
        for (Integer integer : integers){
            numbers += integer + " ";
        }
    }

    public Ticket(String numbers, DateTime dateTime) {
        this.dateTime = dateTime;
        Log.w("Loteria", this.dateTime.toString());
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return numbers;
    }
}
