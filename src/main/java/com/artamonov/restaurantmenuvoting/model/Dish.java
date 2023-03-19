package com.artamonov.restaurantmenuvoting.model;

import javax.persistence.ManyToOne;

public class Dish extends AbstractBaseEntity {
    private String description;

    private long price;

    @ManyToOne
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(String description, int calories) {
        this(null, description, calories);
    }

    public Dish(Integer id, String description, long price) {
        super(id);
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
