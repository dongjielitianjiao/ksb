package com.itboy.dj.examtool.api.bean;

/**
 * Created by Administrator on 2017/4/5.
 */
//测试
public class Basebean {


    /**
     * result : fail
     * response : {"errorCode":-999,"errorText":"通用错误"}
     */

    private ResponseBean response;



    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * errorCode : -999
         * errorText : 通用错误
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
}
