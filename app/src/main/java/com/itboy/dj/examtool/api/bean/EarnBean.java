package com.itboy.dj.examtool.api.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */

public class EarnBean {


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : [{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150},{"userId":"6733","cashbackAmount":150}]
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
         * userId : 6733
         * cashbackAmount : 150.0
         */

        private String userId;
        private double cashbackAmount;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public double getCashbackAmount() {
            return cashbackAmount;
        }

        public void setCashbackAmount(double cashbackAmount) {
            this.cashbackAmount = cashbackAmount;
        }
    }
}
