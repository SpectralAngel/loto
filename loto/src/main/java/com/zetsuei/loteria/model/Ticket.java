package com.zetsuei.loteria.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Table(name = "Ticket")
public class Ticket extends Model {
    @Column(name = "dateTime")
    public LocalDateTime dateTime;

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
        dateTime = LocalDateTime.now();
        for (Integer integer : integers){
            numbers += integer + " ";
        }
    }

    @Override
    public String toString() {
        return numbers;
    }
}
