package com.example.fooddelivery.dto;

import java.util.List;

public class OrderRequest {
    private Long userId;
    private Long courierId;
    private String deliveryAddress;
    private String status;
    private List<OrderDetailRequest> orderDetails;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetailRequest> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailRequest> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public static class OrderDetailRequest {
        private Long dishId;
        private int quantity;

        public Long getDishId() {
            return dishId;
        }

        public void setDishId(Long dishId) {
            this.dishId = dishId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
