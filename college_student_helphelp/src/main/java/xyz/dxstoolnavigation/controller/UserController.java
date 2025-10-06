package xyz.dxstoolnavigation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import xyz.dxstoolnavigation.config.PasswordEncoderConfig;
import xyz.dxstoolnavigation.pojo.Result;
import xyz.dxstoolnavigation.pojo.Users;
import xyz.dxstoolnavigation.service.UserService;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService addUserService;
    @Autowired
    private PasswordEncoderConfig passwordEncoder;




    @PostMapping("/addUser")
    public Result addUser(@RequestBody Users newUser) {
        log.info("添加用户:{}", newUser);
        boolean result = addUserService.addUser(newUser); // 服务方法已改为boolean，返回添加成功或失败
        if (result) {
            return Result.success("用户添加成功");
        } else {
            return Result.error("用户已存在");
        }
    }

    @PostMapping("/changeUserMessage")
    public Result changeUserMessage(@RequestBody Users newUserMessage){
        log.info("修改用户信息:{}", newUserMessage);
        addUserService.changeUserMessage(newUserMessage);
        return Result.success("用户信息修改成功");
    }

}