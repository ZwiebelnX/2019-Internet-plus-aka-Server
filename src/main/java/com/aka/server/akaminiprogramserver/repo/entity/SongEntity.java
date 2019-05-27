package com.aka.server.akaminiprogramserver.repo.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * <p>Title: SongEntity</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019版权</p>
 * <p>Company: </p>
 *
 * @author Zwiebeln_Chan
 * @version V1.0
 * @date 2019/5/26 23:02
 */
@Entity
@EntityListeners(AuditingEntityListener.class) //启动自动生成时间
@Table(name = "song", schema = "test_aka", catalog = "")
public class SongEntity {
    private long id;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private String songName;
    private String creatorOpenid;
    private byte peopleCounting;
    private String filesUrl;
    private String part;
    private String lyric;
    private String creatorNickname;
    private String coverUrl;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "song_name", nullable = false, length = 30)
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    @Basic
    @Column(name = "creator_openid", nullable = false, length = 35)
    public String getCreatorOpenid() {
        return creatorOpenid;
    }

    public void setCreatorOpenid(String creatorOpenid) {
        this.creatorOpenid = creatorOpenid;
    }

    @Basic
    @Column(name = "people_counting", nullable = false)
    public byte getPeopleCounting() {
        return peopleCounting;
    }

    public void setPeopleCounting(byte peopleCounting) {
        this.peopleCounting = peopleCounting;
    }

    @Basic
    @Column(name = "files_url", nullable = true, length = 1000)
    public String getFilesUrl() {
        return filesUrl;
    }

    public void setFilesUrl(String filesUrl) {
        this.filesUrl = filesUrl;
    }

    @Basic
    @Column(name = "part", nullable = true, length = 300)
    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    @Basic
    @Column(name = "lyric", nullable = true, length = 1000)
    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    @Basic
    @Column(name = "creator_nickname", nullable = true, length = 35)
    public String getCreatorNickname() {
        return creatorNickname;
    }

    public void setCreatorNickname(String creatorNickname) {
        this.creatorNickname = creatorNickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongEntity that = (SongEntity) o;
        return id == that.id &&
                peopleCounting == that.peopleCounting &&
                Objects.equals(gmtCreate, that.gmtCreate) &&
                Objects.equals(gmtModified, that.gmtModified) &&
                Objects.equals(songName, that.songName) &&
                Objects.equals(creatorOpenid, that.creatorOpenid) &&
                Objects.equals(filesUrl, that.filesUrl) &&
                Objects.equals(part, that.part) &&
                Objects.equals(lyric, that.lyric) &&
                Objects.equals(creatorNickname, that.creatorNickname) &&
                Objects.equals(coverUrl, that.coverUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gmtCreate, gmtModified, songName, creatorOpenid, peopleCounting, filesUrl, part, lyric, creatorNickname, coverUrl);
    }

    @Basic
    @Column(name = "cover_url", nullable = false, length = 100)
    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
