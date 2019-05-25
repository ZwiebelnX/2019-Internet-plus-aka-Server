package com.aka.server.akaminiprogramserver.DTO.activity;

public class ActivityDTO {
    String openid;
    String name;
    String time;
    String location;
    String maxPeopleCounting;
    String comment;
    String phone;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaxPeopleCounting() {
        return maxPeopleCounting;
    }

    public void setMaxPeopleCounting(String maxPeopleCounting) {
        this.maxPeopleCounting = maxPeopleCounting;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
