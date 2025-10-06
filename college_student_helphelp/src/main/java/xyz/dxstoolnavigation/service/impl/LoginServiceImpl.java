package xyz.dxstoolnavigation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.dxstoolnavigation.mapper.LoginMapper;
import xyz.dxstoolnavigation.pojo.Users;
import xyz.dxstoolnavigation.service.LoginService;
import xyz.dxstoolnavigation.utils.TokenUtils;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users login(Users user) {
        log.info("用户登录Service：查询用户名【{}】", user.getUsername());
        log.info(user.toString());
        Users loginUser = loginMapper.selectByUsername(user.getUsername());
        log.info("数据库查询结果：{}", loginUser);

//         这里应该添加密码验证逻辑
         if (loginUser != null && passwordEncoder.matches(user.getPassword(), loginUser.getPassword())) {
             return loginUser;
         }
         return null;


    }

    @Override
    public Users getUserById(String userId) {
        log.info("根据用户ID查询：{}", userId);
        return loginMapper.selectById(userId);
    }

    @Override
    public Users getUserByToken(String token) {
        log.info("根据token查询用户：{}", token);

        // 验证token并获取用户ID
        String userId = tokenUtils.getUserIdByToken(token);
        if (userId == null) {
            return null;
        }

        // 根据用户ID查询用户信息
        return getUserById(userId);
    }

    @Override
    public void logout(String token) {
        log.info("用户登出，移除token：{}", token);
        tokenUtils.removeToken(token);
    }
}