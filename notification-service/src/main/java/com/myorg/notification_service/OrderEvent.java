package com.myorg.notification_service;

public class OrderEvent {

    private int customerId;
    private String status;
    private String msg;

    public OrderEvent(int customerId, String status, String msg) {
        this.customerId = customerId;
        this.status = status;
        this.msg = msg;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
