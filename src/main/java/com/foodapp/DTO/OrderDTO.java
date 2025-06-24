package com.foodapp.DTO;

import com.foodapp.entity.Status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OrderDTO  {

    private Long orderId;
    private String email;
  // private  OrderPreviewDTO orderPreviewDTO;
    private String name;

    private List<OrderedItemDTO> orderItems;


    private String phoneNumber;


    private double totalItemCost;


    private double handlingFee;


    private double platformFee;


    private  double GSTFee;


    private double totalAmount;

    private Status orderStatus;
    private String deliveryAddress;
    private String paymentMethod;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private Boolean isAccepted;

    public OrderDTO(Long orderId, String email, String name, List<OrderedItemDTO> orderItems, String phoneNumber, double totalItemCost, double handlingFee, double platformFee, double GSTFee, double totalAmount, Status orderStatus, String deliveryAddress, String paymentMethod, LocalDate orderDate, LocalTime orderTime, Boolean isAccepted) {
        this.orderId = orderId;
        this.email = email;
        this.name = name;
        this.orderItems = orderItems;
        this.phoneNumber = phoneNumber;
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
    }

    public OrderDTO() {

    }

    // Getters & Setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

//    public OrderPreviewDTO getOrderPreviewDTO() {
//        return orderPreviewDTO;
//    }
//    public void setOrderPreviewDTO(OrderPreviewDTO orderPreviewDTO) {
//        this.orderPreviewDTO = orderPreviewDTO;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public List<OrderedItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderedItemDTO> orderItems) {
        this.orderItems = orderItems;
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
        return Math.round(totalAmount * 100.0) / 100.0;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
