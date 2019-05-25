package com.aka.server.akaminiprogramserver.DTO.result;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

/**
 * <p>Title: ResponseDataDTO</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019版权</p>
 * <p>Company: </p>
 *
 * @author Zwiebeln_Chan
 * @version V1.0
 * @date 2019/5/24 22:52
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDataDTO {

    private boolean success = false;
    private ResultDTO result = null;
    private String reason = null;

    public ResponseDataDTO(){}

    public ResponseDataDTO(String failureReason){
        success = false;
        reason = failureReason;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        ResponseDataDTO that = (ResponseDataDTO) o;
        return success == that.success &&
                Objects.equals(result, that.result) &&
                Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, result, reason);
    }
}
