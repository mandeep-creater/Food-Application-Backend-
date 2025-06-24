package com.foodapp.DTO;

public class OrderRequestDTO {
    private String deliveryAddress;
    private String paymentMode;

    private String deliveryRecieverPhoneNumber;
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

    public String getDeliveryRecieverPhoneNumber() {
        return deliveryRecieverPhoneNumber;
    }

    public void setDeliveryRecieverPhoneNumber(String deliveryRecieverPhoneNumber) {
        this.deliveryRecieverPhoneNumber = deliveryRecieverPhoneNumber;
    }


}
