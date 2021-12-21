package org.perscholas.controller.dto;

public class UserRegistrationDto {

    private String userId;
    private String userPw;
    private String userName;
    private String userAddress;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String userId, String userPw, String userName, String userAddress) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userAddress = userAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
