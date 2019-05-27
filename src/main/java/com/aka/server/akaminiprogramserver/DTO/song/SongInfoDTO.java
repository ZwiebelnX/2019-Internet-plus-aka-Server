package com.aka.server.akaminiprogramserver.DTO.song;

import java.util.Arrays;
import java.util.Objects;

/**
 * <p>Title: SongInfoDTO</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019版权</p>
 * <p>Company: </p>
 *
 * @author Zwiebeln_Chan
 * @version V1.0
 * @date 2019/5/26 22:56
 */
public class SongInfoDTO {

    private long songId;
    private String songName;
    private String creatorOpenid;
    private String creatorNickname;
    private int peopleCounting;
    private String lyric;
    private String cover;
    private String[] songFiles;
    private String[] part;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCreatorNickname() {
        return creatorNickname;
    }

    public void setCreatorNickname(String creatorNickname) {
        this.creatorNickname = creatorNickname;
    }

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String[] getSongFiles() {
        return songFiles;
    }

    public void setSongFiles(String[] songFiles) {
        this.songFiles = songFiles;
    }

    public String[] getPart() {
        return part;
    }

    public void setPart(String[] part) {
        this.part = part;
    }

    public String getCreatorOpenid() {
        return creatorOpenid;
    }

    public void setCreatorOpenid(String creatorOpenid) {
        this.creatorOpenid = creatorOpenid;
    }

    public int getPeopleCounting() {
        return peopleCounting;
    }

    public void setPeopleCounting(int peopleCounting) {
        this.peopleCounting = peopleCounting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongInfoDTO that = (SongInfoDTO) o;
        return songId == that.songId &&
                peopleCounting == that.peopleCounting &&
                Objects.equals(songName, that.songName) &&
                Objects.equals(creatorOpenid, that.creatorOpenid) &&
                Objects.equals(creatorNickname, that.creatorNickname) &&
                Objects.equals(lyric, that.lyric) &&
                Arrays.equals(songFiles, that.songFiles) &&
                Arrays.equals(part, that.part);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(songId, songName, creatorOpenid, creatorNickname, peopleCounting, lyric);
        result = 31 * result + Arrays.hashCode(songFiles);
        result = 31 * result + Arrays.hashCode(part);
        return result;
    }
}
