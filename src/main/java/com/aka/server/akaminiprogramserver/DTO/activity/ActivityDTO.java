package com.aka.server.akaminiprogramserver.DTO.activity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

public class ActivityDTO {
    private String activityId;
    private String creatorOpenid;
    private String creatorNickname;
    private String name;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp time;
    private String location;
    private String maxPeopleCounting;
    private String nowPeopleCounting;
    private String comment;
    private String phone;
    private String[] participant;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getCreatorOpenid() {
        return creatorOpenid;
    }

    public void setCreatorOpenid(String creatorOpenid) {
        this.creatorOpenid = creatorOpenid;
    }

    public String getCreatorNickname() {
        return creatorNickname;
    }

    public void setCreatorNickname(String creatorNickname) {
        this.creatorNickname = creatorNickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
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

    public String getNowPeopleCounting() {
        return nowPeopleCounting;
    }

    public void setNowPeopleCounting(String nowPeopleCounting) {
        this.nowPeopleCounting = nowPeopleCounting;
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

    public String[] getParticipant() {
        return participant;
    }

    public void setParticipant(String[] participant) {
        this.participant = participant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityDTO that = (ActivityDTO) o;
        return Objects.equals(activityId, that.activityId) &&
                Objects.equals(creatorOpenid, that.creatorOpenid) &&
                Objects.equals(creatorNickname, that.creatorNickname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(time, that.time) &&
                Objects.equals(location, that.location) &&
                Objects.equals(maxPeopleCounting, that.maxPeopleCounting) &&
                Objects.equals(nowPeopleCounting, that.nowPeopleCounting) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(phone, that.phone) &&
                Arrays.equals(participant, that.participant);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(activityId, creatorOpenid, creatorNickname, name, time, location, maxPeopleCounting, nowPeopleCounting, comment, phone);
        result = 31 * result + Arrays.hashCode(participant);
        return result;
    }
}
