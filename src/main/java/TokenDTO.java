import javax.json.bind.annotation.JsonbNumberFormat;
import javax.json.bind.annotation.JsonbProperty;

/**
 * @author 梁梓豪
 * Created by 梁梓豪 on 2019-04-18.
 *
 *
 * @JsonbProperty - 用于指定自定义字段名称
 * @JsonbTransient - 当我们想要在反序列化/序列化期间忽略该字段时
 * @JsonbDateFormat - 当我们想要定义日期的显示格式时
 * @JsonbNumberFormat - 用于指定数值的显示格式
 * @JsonbNillable - 用于启用空值的序列化
 */

public class TokenDTO {

    // 为了使用jsonb,必须有一个空的默认构造方法
    public TokenDTO() {

    }

    public TokenDTO(String token, String appid, String appsecret, String userip) {
        this.token = token;
        this.appid = appid;
        this.appsecret = appsecret;
        this.userip = userip;
    }

    // token
    @JsonbProperty("token")
    private String token;

    // appid
    @JsonbProperty("appid")
    private String appid;

    // appsecret
    @JsonbProperty("appsecret")
    private String appsecret;

    // userip
    @JsonbProperty("userip")
    private String userip;

    @JsonbNumberFormat(locale = "en_US", value = "#0.0")
    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) { this.userip = userip; }
}