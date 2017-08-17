package com.itboy.dj.examtool.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */

public class ProfessionalBean  implements  Serializable{


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : [{"id":"profession-110","pid":null,"originalId":110,"name":"供水专业","type":"profession","professionId":110},{"id":"work-304","pid":"profession-110","originalId":304,"name":"供水供应工","type":"work","professionId":110},{"id":"work-377","pid":"work-304","originalId":377,"name":"供排水泵站运行工","type":"work","professionId":110},{"id":"work-386","pid":"work-304","originalId":386,"name":"供排水客户服务员","type":"work","professionId":110},{"id":"work-305","pid":"work-304","originalId":305,"name":"供水调度员","type":"work","professionId":110},{"id":"work-305-1","pid":"work-305","originalId":1,"name":"普工","type":"rank","professionId":110},{"id":"work-305-2","pid":"work-305","originalId":2,"name":"初级工","type":"rank","professionId":110},{"id":"work-305-3","pid":"work-305","originalId":3,"name":"中级工","type":"rank","professionId":110},{"id":"work-305-4","pid":"work-305","originalId":4,"name":"高级工","type":"rank","professionId":110},{"id":"work-382","pid":"work-304","originalId":382,"name":"供水管道工","type":"work","professionId":110},{"id":"work-382-1","pid":"work-382","originalId":1,"name":"普工","type":"rank","professionId":110},{"id":"work-382-2","pid":"work-382","originalId":2,"name":"初级工","type":"rank","professionId":110},{"id":"work-382-3","pid":"work-382","originalId":3,"name":"中级工","type":"rank","professionId":110},{"id":"work-382-4","pid":"work-382","originalId":4,"name":"高级工","type":"rank","professionId":110},{"id":"work-378","pid":"work-304","originalId":378,"name":"供水检测工","type":"work","professionId":110},{"id":"work-378-1","pid":"work-378","originalId":1,"name":"普工","type":"rank","professionId":110},{"id":"work-378-2","pid":"work-378","originalId":2,"name":"初级工","type":"rank","professionId":110},{"id":"work-378-3","pid":"work-378","originalId":3,"name":"中级工","type":"rank","professionId":110},{"id":"work-378-4","pid":"work-378","originalId":4,"name":"高级工","type":"rank","professionId":110},{"id":"work-307","pid":"work-304","originalId":307,"name":"供水营销员","type":"work","professionId":110},{"id":"work-307-1","pid":"work-307","originalId":1,"name":"普工","type":"rank","professionId":110},{"id":"work-307-2","pid":"work-307","originalId":2,"name":"初级工","type":"rank","professionId":110},{"id":"work-307-3","pid":"work-307","originalId":3,"name":"中级工","type":"rank","professionId":110},{"id":"work-307-4","pid":"work-307","originalId":4,"name":"高级工","type":"rank","professionId":110},{"id":"work-381","pid":"work-304","originalId":381,"name":"排水管道工","type":"work","professionId":110},{"id":"work-381-1","pid":"work-381","originalId":1,"name":"普工","type":"rank","professionId":110},{"id":"work-381-2","pid":"work-381","originalId":2,"name":"初级工","type":"rank","professionId":110},{"id":"work-381-3","pid":"work-381","originalId":3,"name":"中级工","type":"rank","professionId":110},{"id":"work-381-4","pid":"work-381","originalId":4,"name":"高级工","type":"rank","professionId":110},{"id":"work-380","pid":"work-304","originalId":380,"name":"排水检测工","type":"work","professionId":110},{"id":"work-380-1","pid":"work-380","originalId":1,"name":"普工","type":"rank","professionId":110},{"id":"work-380-2","pid":"work-380","originalId":2,"name":"初级工","type":"rank","professionId":110},{"id":"work-380-3","pid":"work-380","originalId":3,"name":"中级工","type":"rank","professionId":110},{"id":"work-380-4","pid":"work-380","originalId":4,"name":"高级工","type":"rank","professionId":110},{"id":"work-384","pid":"work-304","originalId":384,"name":"排水巡查员","type":"work","professionId":110},{"id":"work-306","pid":"work-304","originalId":306,"name":"水表装修工","type":"work","professionId":110},{"id":"work-306-1","pid":"work-306","originalId":1,"name":"普工","type":"rank","professionId":110}]
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

    public static class DataBean  implements Serializable{
        /**
         * id : profession-110
         * pid : null
         * originalId : 110
         * name : 供水专业
         * type : profession
         * professionId : 110
         */

        private String id;  //当前选项的id
        private Object pid;  //当前选项的父id
        private int originalId;
        private String name; //名字
        private String type; //类型
        private int professionId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getPid() {
            return pid;
        }

        public void setPid(Object pid) {
            this.pid = pid;
        }

        public int getOriginalId() {
            return originalId;
        }

        public void setOriginalId(int originalId) {
            this.originalId = originalId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getProfessionId() {
            return professionId;
        }

        public void setProfessionId(int professionId) {
            this.professionId = professionId;
        }
    }
}
