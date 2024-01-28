package com.betrayal.atcutter.models;

public class UserDataEntity {
    private Integer id;
    private String email;
    private String password;
    private String pinCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public static UserDataBuilder builder(){
        return new UserDataEntity.UserDataBuilder();
    }

    public static class UserDataBuilder{
        private Integer id;
        private String email;
        private String password;
        private String pinCode;

        public UserDataBuilder setId(Integer id) {
            this.id = id;
            return this;
        }


        public UserDataBuilder setEmail(String email) {
            this.email = email;
            return this;
        }


        public UserDataBuilder setPassword(String password) {
            this.password = password;
            return this;
        }


        public UserDataBuilder setPinCode(String pinCode) {
            this.pinCode = pinCode;
            return this;
        }

        public UserDataEntity build(){
            UserDataEntity user = new UserDataEntity();
            user.setId(id);
            user.setPassword(password);
            user.setEmail(email);
            user.setPinCode(pinCode);
            return user;
        }
    }
}
