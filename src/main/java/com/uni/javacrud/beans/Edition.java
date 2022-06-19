package com.uni.javacrud.beans;

public class Edition {
    private int id;
    private String name;
    private float price;
    private int topic_id;

    public Edition(int id, String name, float price, int topic_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.topic_id = topic_id;
    }

    public Edition(String name, float price, int topic_id) {
        this.name = name;
        this.price = price;
        this.topic_id = topic_id;
    }

    public Edition() {

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

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }
}
