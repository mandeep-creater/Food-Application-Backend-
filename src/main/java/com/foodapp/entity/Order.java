package com.foodapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderId;

    private String email;

    private double totalItemCost;
    private double handlingFee;
    private double platformFee;

    private  double GSTFee;
    private double totalAmount;

    @Enumerated(value = EnumType.STRING)
    private Status orderStatus;

    private String deliveryAddress;

    private String paymentMethod;

    private String orderDate;

    private String orderTime;

    private Boolean isAccepted = false;

    private boolean isPreview; // true = preview, false = final order

   private String DeliveryRecieverPhoneNumber;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User customer;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderedItem> orderItems = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_preview_id")
//    private  OrderPreview orderPreview;



    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CharSequence getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public boolean isPreview() {
        return isPreview;
    }

    public void setPreview(boolean preview) {
        isPreview = preview;
    }

    public String getDeliveryRecieverPhoneNumber() {
        return DeliveryRecieverPhoneNumber;
    }

    public void setDeliveryRecieverPhoneNumber(String DeliveryRecieverPhoneNumber) {
        this.DeliveryRecieverPhoneNumber = DeliveryRecieverPhoneNumber;
    }

    public List<OrderedItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderedItem> orderItems) {
        this.orderItems = orderItems;
    }


//    public OrderPreview getOrderPreview() {
//        return orderPreview;
//    }
//
//    public void setOrderPreview(OrderPreview orderPreview) {
//        this.orderPreview = orderPreview;
//    }


    public double getTotalItemCost() {
        return totalItemCost;
    }

    public void setTotalItemCost(double totalItemCost) {
        this.totalItemCost = totalItemCost;
    }

    public double getHandlingFee() {
        return handlingFee;
    }

    public void setHandlingFee(double handlingFee) {
        this.handlingFee = handlingFee;
    }

    public double getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(double platformFee) {
        this.platformFee = platformFee;
    }

    public double getGSTFee() {
        return GSTFee;
    }

    public void setGSTFee(double GSTFee) {
        this.GSTFee = GSTFee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Order(Long orderId, String email, double totalItemCost, double handlingFee, double platformFee, double GSTFee, double totalAmount, Status orderStatus, String deliveryAddress, String paymentMethod, String orderDate, String orderTime, Boolean isAccepted, boolean isPreview, String deliveryRecieverPhoneNumber, User customer, List<OrderedItem> orderItems, Restaurant restaurant) {
        OrderId = orderId;
        this.email = email;
        this.totalItemCost = totalItemCost;
        this.handlingFee = handlingFee;
        this.platformFee = platformFee;
        this.GSTFee = GSTFee;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.isAccepted = isAccepted;
        this.isPreview = isPreview;
        DeliveryRecieverPhoneNumber = deliveryRecieverPhoneNumber;
        this.customer = customer;
        this.orderItems = orderItems;
        this.restaurant = restaurant;
    }

    public Order() {
    }
}
