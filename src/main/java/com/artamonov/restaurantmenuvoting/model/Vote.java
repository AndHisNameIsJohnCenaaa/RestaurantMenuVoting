package com.artamonov.restaurantmenuvoting.model;

import javax.persistence.OneToOne;

public class Vote extends AbstractBaseEntity {

    public Vote(){
    }

    public Vote(int id, Restaurant restaurant) {
        super(id);
        this.restaurant = restaurant;
    }

    @OneToOne
    private Restaurant restaurant;

    @OneToOne
    private User user;

}
