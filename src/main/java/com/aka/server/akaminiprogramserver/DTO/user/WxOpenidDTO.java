package com.aka.server.akaminiprogramserver.DTO.user;

import java.util.Objects;

/**
 * <p>Title: WxOpenidDTO</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019版权</p>
 * <p>Company: </p>
 *
 * @author Zwiebeln_Chan
 * @version V1.0
 * @date 2019/5/25 16:45
 */
public class WxOpenidDTO {
    private String openid;
    private String sessionKey;
    private String unionid;
    private int errcode;
    private String errmsg;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WxOpenidDTO that = (WxOpenidDTO) o;
        return errcode == that.errcode &&
                Objects.equals(openid, that.openid) &&
                Objects.equals(sessionKey, that.sessionKey) &&
                Objects.equals(unionid, that.unionid) &&
                Objects.equals(errmsg, that.errmsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(openid, sessionKey, unionid, errcode, errmsg);
    }
}
