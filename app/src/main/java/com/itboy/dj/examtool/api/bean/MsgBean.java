package com.itboy.dj.examtool.api.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */

public class MsgBean {

    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : {"total":14,"rows":[{"messageId":27,"megType":"user_order_message","megContent":"你的订单[2017072500000016]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":26,"megType":"user_order_message","megContent":"你的订单[2017072500000015]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":25,"megType":"user_order_message","megContent":"你的订单[2017072500000014]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":24,"megType":"user_order_message","megContent":"你的订单[2017072500000013]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":23,"megType":"user_order_message","megContent":"你的订单[2017072500000012]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":22,"megType":"user_order_message","megContent":"你的订单[2017072500000011]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":21,"megType":"user_order_message","megContent":"你的订单[2017072500000010]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":20,"megType":"user_order_message","megContent":"你的订单[2017072500000009]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":19,"megType":"user_order_message","megContent":"你的订单[2017072500000008]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":18,"megType":"user_order_message","megContent":"你的订单[2017072500000007]已提交，请尽快付款。","megTime":"2017-07-25"}],"footer":[]}
     */

    private String result;
    private ErrorBean error;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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
         * total : 14
         * rows : [{"messageId":27,"megType":"user_order_message","megContent":"你的订单[2017072500000016]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":26,"megType":"user_order_message","megContent":"你的订单[2017072500000015]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":25,"megType":"user_order_message","megContent":"你的订单[2017072500000014]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":24,"megType":"user_order_message","megContent":"你的订单[2017072500000013]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":23,"megType":"user_order_message","megContent":"你的订单[2017072500000012]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":22,"megType":"user_order_message","megContent":"你的订单[2017072500000011]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":21,"megType":"user_order_message","megContent":"你的订单[2017072500000010]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":20,"megType":"user_order_message","megContent":"你的订单[2017072500000009]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":19,"megType":"user_order_message","megContent":"你的订单[2017072500000008]已提交，请尽快付款。","megTime":"2017-07-25"},{"messageId":18,"megType":"user_order_message","megContent":"你的订单[2017072500000007]已提交，请尽快付款。","megTime":"2017-07-25"}]
         * footer : []
         */

        private int total;
        private List<RowsBean> rows;
        private List<?> footer;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public List<?> getFooter() {
            return footer;
        }

        public void setFooter(List<?> footer) {
            this.footer = footer;
        }

        public static class RowsBean {
            /**
             * messageId : 27
             * megType : user_order_message
             * megContent : 你的订单[2017072500000016]已提交，请尽快付款。
             * megTime : 2017-07-25
             */

            private int messageId;
            private String megType;
            private String megContent;
            private String megTime;

            public int getMessageId() {
                return messageId;
            }

            public void setMessageId(int messageId) {
                this.messageId = messageId;
            }

            public String getMegType() {
                return megType;
            }

            public void setMegType(String megType) {
                this.megType = megType;
            }

            public String getMegContent() {
                return megContent;
            }

            public void setMegContent(String megContent) {
                this.megContent = megContent;
            }

            public String getMegTime() {
                return megTime;
            }

            public void setMegTime(String megTime) {
                this.megTime = megTime;
            }
        }
    }
}
