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

public class Access_TokenDTO {

    // 为了使用jsonb,必须有一个空的默认构造方法
    public Access_TokenDTO() {

    }

    public Access_TokenDTO(String openid, String access_token) {
        this.access_token = access_token;
        this.openid = openid;
    }

    // access_token
    @JsonbProperty("access_token")
    private String access_token;

    // openid
    @JsonbProperty("openid")
    private String openid;

    @JsonbNumberFormat(locale = "en_US", value = "#0.0")
    public String getaccess_token() { return access_token; }

    public void setaccess_token(String access_token) { this.access_token = access_token; }

    public String getopenid() {
        return openid;
    }

    public void setopenid(String openid) {
        this.openid = openid;
    }
}