package xyz.dxstoolnavigation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.dxstoolnavigation.config.PasswordEncoderConfig;
import xyz.dxstoolnavigation.mapper.LoginMapper;
import xyz.dxstoolnavigation.pojo.Users;
import xyz.dxstoolnavigation.service.UserService;
import xyz.dxstoolnavigation.mapper.UserMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private UserMapper addUserMapper;

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private PasswordEncoderConfig passwordEncoder;

    // 实现接口方法（已改为void返回类型）
    @Override
    public boolean addUser(Users user) {
        log.info("开始处理用户添加：{}", user);
        // 检查用户名是否已存在
        if (loginMapper.selectByUsername(user.getUsername()) != null) {
            log.error("用户名已存在：{}", user.getUsername());
            return false;
        }

        user.setStatus("1"); // 设置用户状态

        // 密码加密（明文密码→BCrypt加密，避免明文存储）
        String encryptedPwd = passwordEncoder.passwordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPwd); // 替换明文密码为加密后的值
        user.setId(UUID.randomUUID().toString());
        user.setCreateDatetime(LocalDateTime.now());
        user.setUpdateDatetime(LocalDateTime.now());
        System.out.println(user);

        addUserMapper.insert(user);

        log.info("用户添加成功：{}", user.getUsername());
        return true;
    }

    @Override
    public void changeUserMessage(Users newUserMessage) {
//        设置更新时间
        newUserMessage.setUpdateDatetime(LocalDateTime.now());
        newUserMessage.setPassword(passwordEncoder.passwordEncoder().encode(newUserMessage.getPassword()));
        addUserMapper.updateById(newUserMessage);
    }
}