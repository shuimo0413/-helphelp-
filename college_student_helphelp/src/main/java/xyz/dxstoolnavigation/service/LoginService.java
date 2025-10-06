package xyz.dxstoolnavigation.service;

import xyz.dxstoolnavigation.pojo.Users;

public interface LoginService {
    Users login(Users user);
    Users getUserById(String userId);

    // 新增方法：验证token并获取用户信息
    Users getUserByToken(String token);

    // 新增方法：登出（移除token）
    void logout(String token);
}