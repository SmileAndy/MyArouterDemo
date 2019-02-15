package meiyu.core.module;

import java.io.Serializable;

/**
 * Author: GuoDandan
 * Version: V1.0版本
 * Description: CommonResponse
 * Date: 2018/11/27 17:04.
 */
public class CommonResponse  implements Serializable{

    protected int code;
    protected String message;

    public CommonResponse() {
    }

    public CommonResponse(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
