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
public class UserDTO {
    private String openid;
    private UserDataDTO data;

    public UserDataDTO getData() {
        return data;
    }

    public String getOpenid() {
        return openid;
    }

    public void setData(UserDataDTO data) {
        this.data = data;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
