package xyz.dxstoolnavigation.pojo;
import lombok.Data;
import org.apache.catalina.User;

@Data
public class LoginResult {
    private Integer code;
    private String msg;
    private Object data;
    public static LoginResult success(Object data){
        LoginResult result = new LoginResult();
        result.code = 1;
        result.msg = "success";
        result.data = data;  // 将用户信息设置到data字段
        return result;
    }
    public static LoginResult error0(){
        LoginResult result = new LoginResult();
        result.code = 0;
        result.msg = "未知错误";
        return result;
    }
//    用户名或密码错误
    public static LoginResult error1(){
        LoginResult result = new LoginResult();
        result.code = 0;
        result.msg = "用户名或密码错误";
        return result;
    }
//    此账号被封禁
    public static LoginResult error2(){
        LoginResult result = new LoginResult();
        result.code = 0;
        result.msg = "此账号被封禁";
        return result;
    }

}
