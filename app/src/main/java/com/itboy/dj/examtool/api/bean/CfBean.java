package com.itboy.dj.examtool.api.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */

public class CfBean {

    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : [{"orderId":109,"userId":5134,"orderCode":"2017072500000016","orderName":"土木建筑类钢筋工高级工","orderCost":500,"orderFreight":0,"orderTotalCost":500,"orderStatus":"待付款","orderTime":"2017-07-25","paymentTime":null,"deliveryTime":null,"receiveTime":null,"certificateStatus":"待审核"},{"orderId":108,"userId":5134,"orderCode":"2017072500000015","orderName":"土木建筑类钢筋工高级工","orderCost":500,"orderFreight":0,"orderTotalCost":500,"orderStatus":"待付款","orderTime":"2017-07-25","paymentTime":null,"deliveryTime":null,"receiveTime":null,"certificateStatus":"待审核"},{"orderId":107,"userId":5134,"orderCode":"2017072500000014","orderName":"土木建筑类钢筋工高级工","orderCost":500,"orderFreight":0,"orderTotalCost":500,"orderStatus":"待付款","orderTime":"2017-07-25","paymentTime":null,"deliveryTime":null,"receiveTime":null,"certificateStatus":"待审核"},{"orderId":106,"userId":5134,"orderCode":"2017072500000013","orderName":"土木建筑类钢筋工高级工","orderCost":500,"orderFreight":0,"orderTotalCost":500,"orderStatus":"待付款","orderTime":"2017-07-25","paymentTime":null,"deliveryTime":null,"receiveTime":null,"certificateStatus":"待审核"},{"orderId":105,"userId":5134,"orderCode":"2017072500000012","orderName":"土木建筑类钢筋工高级工","orderCost":500,"orderFreight":0,"orderTotalCost":500,"orderStatus":"待付款","orderTime":"2017-07-25","paymentTime":null,"deliveryTime":null,"receiveTime":null,"certificateStatus":"待审核"},{"orderId":104,"userId":5134,"orderCode":"2017072500000011","orderName":"土木建筑类钢筋工高级工","orderCost":500,"orderFreight":0,"orderTotalCost":500,"orderStatus":"待付款","orderTime":"2017-07-25","paymentTime":null,"deliveryTime":null,"receiveTime":null,"certificateStatus":"待审核"},{"orderId":103,"userId":5134,"orderCode":"2017072500000010","orderName":"土木建筑类钢筋工高级工","orderCost":500,"orderFreight":0,"orderTotalCost":500,"orderStatus":"待付款","orderTime":"2017-07-25","paymentTime":null,"deliveryTime":null,"receiveTime":null,"certificateStatus":"待审核"},{"orderId":102,"userId":5134,"orderCode":"2017072500000009","orderName":"土木建筑类钢筋工高级工","orderCost":500,"orderFreight":0,"orderTotalCost":500,"orderStatus":"待付款","orderTime":"2017-07-25","paymentTime":null,"deliveryTime":null,"receiveTime":null,"certificateStatus":"待审核"},{"orderId":101,"userId":5134,"orderCode":"2017072500000008","orderName":"土木建筑类钢筋工高级工","orderCost":500,"orderFreight":0,"orderTotalCost":500,"orderStatus":"待付款","orderTime":"2017-07-25","paymentTime":null,"deliveryTime":null,"receiveTime":null,"certificateStatus":"待审核"},{"orderId":100,"userId":5134,"orderCode":"2017072500000007","orderName":"土木建筑类钢筋工高级工","orderCost":500,"orderFreight":0,"orderTotalCost":500,"orderStatus":"待付款","orderTime":"2017-07-25","paymentTime":null,"deliveryTime":null,"receiveTime":null,"certificateStatus":"待审核"},{"orderId":99,"userId":5134,"orderCode":"2017072500000006","orderName":"土木建筑类钢筋工高级工","orderCost":500,"orderFreight":0,"orderTotalCost":500,"orderStatus":"待付款","orderTime":"2017-07-25","paymentTime":null,"deliveryTime":null,"receiveTime":null,"certificateStatus":"待审核"},{"orderId":98,"userId":5134,"orderCode":"2017072500000005","orderName":"土木建筑类钢筋工高级工","orderCost":0,"orderFreight":0,"orderTotalCost":0,"orderStatus":"待付款","orderTime":"2017-07-25","paymentTime":null,"deliveryTime":null,"receiveTime":null,"certificateStatus":"待审核"}]
     */

    private String result;
    private ErrorBean error;
    private List<DataBean> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ErrorBean {
        /**
         * errorCode : 0
         * errorText : success
         */

        private int errorCode;
        private String errorText;

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorText() {
            return errorText;
        }

        public void setErrorText(String errorText) {
            this.errorText = errorText;
        }
    }

    public static class DataBean {
        /**
         * orderId : 109
         * userId : 5134
         * orderCode : 2017072500000016
         * orderName : 土木建筑类钢筋工高级工
         * orderCost : 500.0
         * orderFreight : 0.0
         * orderTotalCost : 500.0
         * orderStatus : 待付款
         * orderTime : 2017-07-25
         * paymentTime : null
         * deliveryTime : null
         * receiveTime : null
         * certificateStatus : 待审核
         */

        private int orderId;
        private int userId;
        private String orderCode;
        private String orderName;
        private double orderCost;
        private double orderFreight;
        private double orderTotalCost;
        private String orderStatus;
        private String orderTime;
        private Object paymentTime;
        private Object deliveryTime;
        private Object receiveTime;
        private String certificateStatus;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getOrderName() {
            return orderName;
        }

        public void setOrderName(String orderName) {
            this.orderName = orderName;
        }

        public double getOrderCost() {
            return orderCost;
        }

        public void setOrderCost(double orderCost) {
            this.orderCost = orderCost;
        }

        public double getOrderFreight() {
            return orderFreight;
        }

        public void setOrderFreight(double orderFreight) {
            this.orderFreight = orderFreight;
        }

        public double getOrderTotalCost() {
            return orderTotalCost;
        }

        public void setOrderTotalCost(double orderTotalCost) {
            this.orderTotalCost = orderTotalCost;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public Object getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(Object paymentTime) {
            this.paymentTime = paymentTime;
        }

        public Object getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(Object deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public Object getReceiveTime() {
            return receiveTime;
        }

        public void setReceiveTime(Object receiveTime) {
            this.receiveTime = receiveTime;
        }

        public String getCertificateStatus() {
            return certificateStatus;
        }

        public void setCertificateStatus(String certificateStatus) {
            this.certificateStatus = certificateStatus;
        }
    }
}
