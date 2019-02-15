package meiyu.core.module;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/5.
 */

public class SignPackage implements Serializable {
    private long timestamp;
    private String nonstr;
    private String sign;
    private int type=1;

    public SignPackage() {

    }

    public SignPackage(long timestamp, String nonstr, String sign, int type) {
        this.timestamp = timestamp;
        this.nonstr = nonstr;
        this.sign = sign;
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonstr() {
        return nonstr;
    }

    public void setNonstr(String nonstr) {
        this.nonstr = nonstr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
