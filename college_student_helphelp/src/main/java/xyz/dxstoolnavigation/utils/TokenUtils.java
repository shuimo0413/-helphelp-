//package xyz.dxstoolnavigation.utils;
//
//import org.springframework.stereotype.Component;
//
//import java.security.SecureRandom;
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.Base64;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//
////例子，token与用户id的映射
////{
////        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...xyz" : "user123",
////        "dGhpcyIsYXRoZXIgdG9rZW4gZm9yIHVzZXI0NTY..." : "user456"
////}
////token与时间的映射
////{
////        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...xyz" : 2025-09-24T10:30:00,
////        "dGhpcyIsYXRoZXIgdG9rZW4gZm9yIHVzZXI0NTY..." : 2025-09-24T14:15:00
////}
//
//
///**
// * Token工具类，用于生成、验证和管理登录Token
// */
//@Component
//public class TokenUtils {
//    // 存储token和用户ID的映射关系
//    private static final Map<String, String> TOKEN_USER_MAP = new ConcurrentHashMap<>();
//    // 存储token和过期时间的映射关系
//    private static final Map<String, LocalDateTime> TOKEN_EXPIRE_TIME_MAP = new ConcurrentHashMap<>();
//    // token过期时间（默认24小时）
//    private static final long EXPIRE_HOURS = 24;
//    // 用于生成安全随机数
//    private static final SecureRandom secureRandom = new SecureRandom();
//
//    /**
//     * 生成64位随机token
//     * @param userId 用户ID
//     * @return 生成的token
//     */
//    public String generateToken(String userId) {
//        // 清理过期的token
//        cleanExpiredTokens();
//
//        // 生成64字节的随机数据
//        byte[] randomBytes = new byte[64];
//        secureRandom.nextBytes(randomBytes);
//        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
//        TOKEN_USER_MAP.put(token,userId);
//
//        return token;
//    }
//
//    /**
//     * 根据token获取用户ID
//     * @param token 登录token
//     * @return 用户ID，如果token无效或过期则返回null
//     */
//    public String getUserIdByToken(String token) {
//        if (token == null || !TOKEN_USER_MAP.containsKey(token)) {
//            return null;
//        }
//
//        // 检查token是否过期
//        LocalDateTime expireTime = TOKEN_EXPIRE_TIME_MAP.get(token);
//        if (expireTime != null && LocalDateTime.now().isAfter(expireTime)) {
//            // token已过期，移除该token
//            removeToken(token);
//            return null;
//        }
//
//        // 刷新token过期时间
//        refreshTokenExpireTime(token);
//
//        return TOKEN_USER_MAP.get(token);
//    }
//
//    /**
//     * 验证token是否有效
//     * @param token 登录token
//     * @return 是否有效
//     */
//    public boolean validateToken(String token) {
//        return getUserIdByToken(token) != null;
//    }
//
//    /**
//     * 移除指定的token
//     * @param token 登录token
//     */
//    public void removeToken(String token) {
//        TOKEN_USER_MAP.remove(token);
//        TOKEN_EXPIRE_TIME_MAP.remove(token);
//    }
//
//    /**
//     * 刷新token过期时间
//     * @param token 登录token
//     */
//    private void refreshTokenExpireTime(String token) {
//        TOKEN_EXPIRE_TIME_MAP.put(token, LocalDateTime.now().plus(EXPIRE_HOURS, ChronoUnit.HOURS));
//    }
//
//    /**
//     * 清理过期的token
//     */
//    private void cleanExpiredTokens() {
//        LocalDateTime now = LocalDateTime.now();
//        TOKEN_EXPIRE_TIME_MAP.forEach((token, expireTime) -> {
//            if (now.isAfter(expireTime)) {
//                removeToken(token);
//            }
//        });
//    }
//
//    /**
//     * 获取当前存储的token数量
//     * @return token数量
//     */
//    public int getTokenCount() {
//        cleanExpiredTokens();
//        return TOKEN_USER_MAP.size();
//    }
//}


//第一代写法，接下来是第二代
package xyz.dxstoolnavigation.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TokenUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // Token过期时间（例如2小时）
    private static final long TOKEN_EXPIRE = 2 * 60 * 60;

    /**
     * 生成token并存储到Redis
     */
    public String generateToken(String userId) {
        // 生成UUID作为token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        // 存储token到Redis，key为token，value为userId，设置过期时间
        redisTemplate.opsForValue().set(token, userId, TOKEN_EXPIRE, TimeUnit.SECONDS);
        return token;
    }
    //    /**
//     * 验证token是否有效
//     * @param token 登录token
//     * @return 是否有效
//     */
    public boolean validateToken(String token) {
        return getUserIdByToken(token) != null;
    }



    /**
     * 根据token从Redis获取用户ID
     */
    public String getUserIdByToken(String token) {
        if (token == null) {
            return null;
        }
        // 从Redis获取userId
        return (String) redisTemplate.opsForValue().get(token);
    }

    /**
     * 从Redis删除token（登出）
     */
    public void removeToken(String token) {
        if (token != null) {
            redisTemplate.delete(token);
        }
    }
}
