package com.aka.server.akaminiprogramserver.DTO.user;

import com.aka.server.akaminiprogramserver.DTO.result.ResultDTO;

import java.util.Objects;

/**
 * <p>Title: OpenidDTO</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019版权</p>
 * <p>Company: </p>
 *
 * @author Zwiebeln_Chan
 * @version V1.0
 * @date 2019/5/25 16:57
 */
public class OpenidDTO implements ResultDTO {

    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenidDTO openidDTO = (OpenidDTO) o;
        return Objects.equals(openid, openidDTO.openid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(openid);
    }
}
