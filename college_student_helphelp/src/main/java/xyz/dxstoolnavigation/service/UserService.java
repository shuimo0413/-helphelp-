package xyz.dxstoolnavigation.service;

import xyz.dxstoolnavigation.pojo.Users;

/**
 * 用户添加服务接口
 */
public interface UserService {
    /**
     * 添加用户
     * @param user 要添加的用户对象
     */
    boolean addUser(Users user); // 接口返回类型改为void

    void changeUserMessage(Users newUserMessage);
}