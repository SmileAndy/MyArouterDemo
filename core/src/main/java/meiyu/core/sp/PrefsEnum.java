package meiyu.core.sp;

/**
 * Created by Administrator on 2017/6/6.
 */

public enum PrefsEnum {
//    /**
//     * token,用户登录标识
//     */
//    TOKEN;
//    /**
//     * 用户信息
//     */
//    USER_INFO;
//    /**
//     * 上次登录账户
//     */
//    LAST_LOGIN_ACCOUNT;

    /**
     * 商品详情
     */

    GOOD_DETAIL("0", "代取详情");

    private String type;
    private String desc;

    private PrefsEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
