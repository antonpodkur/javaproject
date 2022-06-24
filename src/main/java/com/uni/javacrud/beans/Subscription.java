package com.uni.javacrud.beans;

import java.util.Date;

public class Subscription {
    private int id;
    private String name;
    private float price;
    private String date_start;
    private String date_end;
    private int edition_id;
    private int user_id;

    public Subscription(int id, String name, float price, String date_start, String date_end, int edition_id, int user_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date_start = date_start;
        this.date_end = date_end;
        this.edition_id = edition_id;
        this.user_id = user_id;
    }

    public Subscription(String name, float price, String date_start, String date_end, int edition_id, int user_id) {
        this.name = name;
        this.price = price;
        this.date_start = date_start;
        this.date_end = date_end;
        this.edition_id = edition_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public int getEdition_id() {
        return edition_id;
    }

    public void setEdition_id(int edition_id) {
        this.edition_id = edition_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
