package xyz.dxstoolnavigation.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime; // 导入标准时间类

@Data
public class Users {
    private String id;
    private String username;
    private String email;
    private String password;
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 前端传时间时的格式（如需要）
    private LocalDateTime createDatetime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDatetime;

    private String Administrator;


}