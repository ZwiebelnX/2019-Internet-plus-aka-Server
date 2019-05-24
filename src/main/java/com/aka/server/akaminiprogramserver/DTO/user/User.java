package com.aka.server.akaminiprogramserver.DTO.user;

/**
 * <p>Title: User</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019版权</p>
 * <p>Company: </p>
 *
 * @author Zwiebeln_Chan
 * @version V1.0
 * @date 2019/5/24 20:52
 */
public class User {
    private String openid;
    private Data data;

    public Data getData() {
        return data;
    }

    public String getOpenid() {
        return openid;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
