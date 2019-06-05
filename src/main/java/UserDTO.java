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

public class UserDTO {

    // 为了使用jsonb,必须有一个空的默认构造方法
    public UserDTO() {

    }

    public UserDTO(String username,String name,String group,String error, String message, String openid, String wx_openid) {
        this.username=username;
        this.name=name;
        this.group=group;
        this.error = error;
        this.message = message;
        this.wx_openid = wx_openid;
        this.openid = openid;
    }

    // group
    @JsonbProperty("group")
    private String group;

    // name
    @JsonbProperty("name")
    private String name;

    // username
    @JsonbProperty("username")
    private String username;

    // wx_openid
    @JsonbProperty("wx_openid")
    private String wx_openid;

    // openid
    @JsonbProperty("openid")
    private String openid;

    // message
    @JsonbProperty("message")
    private String message;

    // error
    @JsonbProperty("error")
    private String error;

    @JsonbNumberFormat(locale = "en_US", value = "#0.0")
    public String getgroup() { return group; }

    public void setgroup(String group) { this.group = group; }

    public String getname() { return name; }

    public void setname(String name) { this.name = name; }

    public String getusername() { return username; }

    public void setusername(String username) { this.username = username; }

    public String getwx_openid() { return wx_openid; }

    public void setwx_openid(String wx_openid) { this.wx_openid = wx_openid; }

    public String getopenid() {
        return openid;
    }

    public void setopenid(String openid) {
        this.openid = openid;
    }

    public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }

    public String geterror() {
        return error;
    }

    public void seterror(String error) { this.error = error; }
}