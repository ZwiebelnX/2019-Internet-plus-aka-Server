package com.aka.server.repo.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.servlet.mvc.LastModified;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * <p>Title: ActivityEntity</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019版权</p>
 * <p>Company: </p>
 *
 * @author Zwiebeln_Chan
 * @version V1.0
 * @date 2019/5/24 20:32
 */
@Entity
@EntityListeners(AuditingEntityListener.class) //启动自动生成时间
@Table(name = "activity", schema = "test_aka", catalog = "")
public class ActivityEntity {
    private long id;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private String name;
    private String leaderOpenid;
    private Timestamp startTime;
    private String location;
    private String phone;
    private int nowPeopleCounting;
    private int maxPeopleCounting;
    private String comment;
    private String participant;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @CreatedDate
    @Column(name = "gmt_create", nullable = false)
    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Basic
    @LastModifiedDate
    @Column(name = "gmt_modified", nullable = false)
    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "leader_openid", nullable = false, length = 35)
    public String getLeaderOpenid() {
        return leaderOpenid;
    }

    public void setLeaderOpenid(String leaderOpenid) {
        this.leaderOpenid = leaderOpenid;
    }

    @Basic
    @Column(name = "start_time", nullable = false)
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "location", nullable = false, length = 100)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "now_people_counting", nullable = false)
    public int getNowPeopleCounting() {
        return nowPeopleCounting;
    }

    public void setNowPeopleCounting(int nowPeopleCounting) {
        this.nowPeopleCounting = nowPeopleCounting;
    }

    @Basic
    @Column(name = "max_people_counting", nullable = false)
    public int getMaxPeopleCounting() {
        return maxPeopleCounting;
    }

    public void setMaxPeopleCounting(int maxPeopleCounting) {
        this.maxPeopleCounting = maxPeopleCounting;
    }

    @Basic
    @Column(name = "comment", nullable = true, length = 100)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "participant", nullable = true, length = 1000)
    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityEntity that = (ActivityEntity) o;
        return id == that.id &&
                nowPeopleCounting == that.nowPeopleCounting &&
                maxPeopleCounting == that.maxPeopleCounting &&
                Objects.equals(gmtCreate, that.gmtCreate) &&
                Objects.equals(gmtModified, that.gmtModified) &&
                Objects.equals(name, that.name) &&
                Objects.equals(leaderOpenid, that.leaderOpenid) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(location, that.location) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(participant, that.participant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gmtCreate, gmtModified, name, leaderOpenid, startTime, location, phone, nowPeopleCounting, maxPeopleCounting, comment, participant);
    }
}
