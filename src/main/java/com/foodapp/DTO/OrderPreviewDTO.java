package com.foodapp.DTO;

import java.util.List;

public class OrderPreviewDTO {

    private String name;
    private String deliveryAddress;
    private String deliveryRecieverPhoneNumber;
    private String paymentMode;
    private double totalItemCost;
    private double handlingFee;
    private double platformFee;

    private  double GSTFee;
    private double totalAmount;
    private List<CartItemDTO> cartItems;



    // ✅ Recommended constructor


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

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

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public String getDeliveryRecieverPhoneNumber() {
        return deliveryRecieverPhoneNumber;
    }

    public void setDeliveryRecieverPhoneNumber(String deliveryRecieverPhoneNumber) {
        this.deliveryRecieverPhoneNumber = deliveryRecieverPhoneNumber;
    }

    public OrderPreviewDTO(String name, String deliveryAddress, String paymentMode, String deliveryRecieverPhoneNumber, double totalItemCost, double handlingFee, double platformFee, double GSTFee, double totalAmount, List<CartItemDTO> cartItems) {
        this.name = name;
        this.deliveryAddress = deliveryAddress;
        this.paymentMode = paymentMode;
        this.totalItemCost = totalItemCost;
        this.handlingFee = handlingFee;
        this.platformFee = platformFee;
        this.GSTFee = GSTFee;
        this.deliveryRecieverPhoneNumber=deliveryRecieverPhoneNumber;
        this.totalAmount = totalAmount;
        this.cartItems = cartItems;
    }
}
