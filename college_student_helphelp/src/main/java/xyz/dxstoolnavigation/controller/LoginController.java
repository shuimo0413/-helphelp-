package xyz.dxstoolnavigation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.dxstoolnavigation.pojo.LoginResult;
import xyz.dxstoolnavigation.pojo.Users;
import xyz.dxstoolnavigation.service.LoginService;
import xyz.dxstoolnavigation.utils.TokenUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenUtils tokenUtils;

    @PostMapping("/login")
    public LoginResult login(@RequestBody Users user) {
        log.info("用户登录请求：{}", user);
        Users loginUser = loginService.login(user);
        log.info("用户登录查询结果：{}", loginUser);

        if (loginUser == null) {
            log.info("用户登录失败");
            return LoginResult.error1();
        }
        if (loginUser.getStatus().equals("0")){
            return LoginResult.error2();
        }

        loginUser.setPassword(null);

        // 生成token
        String token = tokenUtils.generateToken(loginUser.getId());
        log.info("生成token：{}", token);


        Map<String, Object> resultData = new HashMap<>();
        resultData.put("user", loginUser);
        resultData.put("token", token);

        log.info("用户登录成功，返回数据：{}", resultData);

        return LoginResult.success(resultData);
    }

    @PostMapping("/logout")
    public LoginResult logout(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // 移除 "Bearer " 前缀
        }

        loginService.logout(token);
        return LoginResult.success("登出成功");
    }

    @GetMapping("/userInfo")
    public LoginResult getUserInfo(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // 移除 "Bearer " 前缀
        }

        Users user = loginService.getUserByToken(token);
        if (user == null) {
            return LoginResult.error0();
        }

        return LoginResult.success(user);
    }

}