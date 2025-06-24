package com.foodapp.entity;

public enum Status {
    PENDING,
    ORDERED,
    ACCEPTED,  // New status
    PREPARING,
    READY_FOR_DELIVERY,  // New status
    PICKED_UP,  // New status
    DELIVERED,
    CANCELLED_BY_CUSTOMER,
    CANCELLED_BY_RESTAURANT,
    CANCELLED_BY_DRIVER;
}
