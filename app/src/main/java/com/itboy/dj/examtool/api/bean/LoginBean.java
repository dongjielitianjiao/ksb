package com.itboy.dj.examtool.api.bean;

/**
 * Created by Administrator on 2017/4/20.
 */

public class LoginBean {


    /**
     * result : ok
     * error : {"errorCode":0,"errorText":"success"}
     * data : {"userId":7374,"movePhone":"13093748551","userName":"9ab778c0bed94615805718e744776f0d","nikename":null,"password":"123456","sex":"0","inviteCode":"8128999084019871","headPortrait":"http://img.kspx.ccla.com.cn/server/upload/image/2017/06/21/1498035600455050737.jpg","realname":"123","idCard":"510723199207294418","birthday":null,"diploma":null,"diplomaLabel":null,"intro":null,"email":null,"qq":null,"phone":null,"homeaddressProvince":null,"homeaddressCity":null,"homeaddressCounty":null,"homeaddressProvinceLabel":null,"homeaddressCityLabel":null,"homeaddressCountyLabel":null,"address":null,"createDate":null,"company":null,"hobby":null,"marriage":null,"marriageLabel":null,"lastLoginTime":"2017-06-23","lastLoginIp":"125.71.132.205","job":{"isWorking":false,"work":null,"rank":null,"address":null},"token":{"accessToken":"8ef649ba59e541b28b88e9213b18240crtOP46u9uVT1c32_stsw16lc9g4gab-WSXukdEyjb_nFIkbSqlEd8p_ipCOpNue7ztgv9cb67a0112dd44d0bb130874b42ca804","expiresIn":21600}}
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
         * userId : 7374
         * movePhone : 13093748551
         * userName : 9ab778c0bed94615805718e744776f0d
         * nikename : null
         * password : 123456
         * sex : 0
         * inviteCode : 8128999084019871
         * headPortrait : http://img.kspx.ccla.com.cn/server/upload/image/2017/06/21/1498035600455050737.jpg
         * realname : 123
         * idCard : 510723199207294418
         * birthday : null
         * diploma : null
         * diplomaLabel : null
         * intro : null
         * email : null
         * qq : null
         * phone : null
         * homeaddressProvince : null
         * homeaddressCity : null
         * homeaddressCounty : null
         * homeaddressProvinceLabel : null
         * homeaddressCityLabel : null
         * homeaddressCountyLabel : null
         * address : null
         * createDate : null
         * company : null
         * hobby : null
         * marriage : null
         * marriageLabel : null
         * lastLoginTime : 2017-06-23
         * lastLoginIp : 125.71.132.205
         * job : {"isWorking":false,"work":null,"rank":null,"address":null}
         * token : {"accessToken":"8ef649ba59e541b28b88e9213b18240crtOP46u9uVT1c32_stsw16lc9g4gab-WSXukdEyjb_nFIkbSqlEd8p_ipCOpNue7ztgv9cb67a0112dd44d0bb130874b42ca804","expiresIn":21600}
         */

        private int userId;
        private String movePhone;
        private String userName;
        private Object nikename;
        private String password;
        private String sex;
        private String inviteCode;
        private String headPortrait;
        private String realname;
        private String idCard;
        private Object birthday;
        private Object diploma;
        private Object diplomaLabel;
        private Object intro;
        private Object email;
        private Object qq;
        private Object phone;
        private Object homeaddressProvince;
        private Object homeaddressCity;
        private Object homeaddressCounty;
        private Object homeaddressProvinceLabel;
        private Object homeaddressCityLabel;
        private Object homeaddressCountyLabel;
        private Object address;
        private Object createDate;
        private Object company;
        private Object hobby;
        private Object marriage;
        private Object marriageLabel;
        private String lastLoginTime;
        private String lastLoginIp;
        private JobBean job;
        private TokenBean token;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getMovePhone() {
            return movePhone;
        }

        public void setMovePhone(String movePhone) {
            this.movePhone = movePhone;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getNikename() {
            return nikename;
        }

        public void setNikename(Object nikename) {
            this.nikename = nikename;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public Object getDiploma() {
            return diploma;
        }

        public void setDiploma(Object diploma) {
            this.diploma = diploma;
        }

        public Object getDiplomaLabel() {
            return diplomaLabel;
        }

        public void setDiplomaLabel(Object diplomaLabel) {
            this.diplomaLabel = diplomaLabel;
        }

        public Object getIntro() {
            return intro;
        }

        public void setIntro(Object intro) {
            this.intro = intro;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getQq() {
            return qq;
        }

        public void setQq(Object qq) {
            this.qq = qq;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getHomeaddressProvince() {
            return homeaddressProvince;
        }

        public void setHomeaddressProvince(Object homeaddressProvince) {
            this.homeaddressProvince = homeaddressProvince;
        }

        public Object getHomeaddressCity() {
            return homeaddressCity;
        }

        public void setHomeaddressCity(Object homeaddressCity) {
            this.homeaddressCity = homeaddressCity;
        }

        public Object getHomeaddressCounty() {
            return homeaddressCounty;
        }

        public void setHomeaddressCounty(Object homeaddressCounty) {
            this.homeaddressCounty = homeaddressCounty;
        }

        public Object getHomeaddressProvinceLabel() {
            return homeaddressProvinceLabel;
        }

        public void setHomeaddressProvinceLabel(Object homeaddressProvinceLabel) {
            this.homeaddressProvinceLabel = homeaddressProvinceLabel;
        }

        public Object getHomeaddressCityLabel() {
            return homeaddressCityLabel;
        }

        public void setHomeaddressCityLabel(Object homeaddressCityLabel) {
            this.homeaddressCityLabel = homeaddressCityLabel;
        }

        public Object getHomeaddressCountyLabel() {
            return homeaddressCountyLabel;
        }

        public void setHomeaddressCountyLabel(Object homeaddressCountyLabel) {
            this.homeaddressCountyLabel = homeaddressCountyLabel;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public Object getCompany() {
            return company;
        }

        public void setCompany(Object company) {
            this.company = company;
        }

        public Object getHobby() {
            return hobby;
        }

        public void setHobby(Object hobby) {
            this.hobby = hobby;
        }

        public Object getMarriage() {
            return marriage;
        }

        public void setMarriage(Object marriage) {
            this.marriage = marriage;
        }

        public Object getMarriageLabel() {
            return marriageLabel;
        }

        public void setMarriageLabel(Object marriageLabel) {
            this.marriageLabel = marriageLabel;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getLastLoginIp() {
            return lastLoginIp;
        }

        public void setLastLoginIp(String lastLoginIp) {
            this.lastLoginIp = lastLoginIp;
        }

        public JobBean getJob() {
            return job;
        }

        public void setJob(JobBean job) {
            this.job = job;
        }

        public TokenBean getToken() {
            return token;
        }

        public void setToken(TokenBean token) {
            this.token = token;
        }

        public static class JobBean {
            /**
             * isWorking : false
             * work : null
             * rank : null
             * address : null
             */

            private boolean isWorking;
            private Object work;
            private Object rank;
            private Object address;

            public boolean isIsWorking() {
                return isWorking;
            }

            public void setIsWorking(boolean isWorking) {
                this.isWorking = isWorking;
            }

            public Object getWork() {
                return work;
            }

            public void setWork(Object work) {
                this.work = work;
            }

            public Object getRank() {
                return rank;
            }

            public void setRank(Object rank) {
                this.rank = rank;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }
        }

        public static class TokenBean {
            /**
             * accessToken : 8ef649ba59e541b28b88e9213b18240crtOP46u9uVT1c32_stsw16lc9g4gab-WSXukdEyjb_nFIkbSqlEd8p_ipCOpNue7ztgv9cb67a0112dd44d0bb130874b42ca804
             * expiresIn : 21600
             */

            private String accessToken;
            private int expiresIn;

            public String getAccessToken() {
                return accessToken;
            }

            public void setAccessToken(String accessToken) {
                this.accessToken = accessToken;
            }

            public int getExpiresIn() {
                return expiresIn;
            }

            public void setExpiresIn(int expiresIn) {
                this.expiresIn = expiresIn;
            }
        }
    }
}
