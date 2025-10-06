package xyz.dxstoolnavigation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.dxstoolnavigation.config.TokenInterceptor;
import xyz.dxstoolnavigation.service.LoginService;

import java.util.Arrays;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenInterceptor tokenInterceptor;

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 禁用CSRF保护（在前后端分离项目中通常这样做）
                .csrf().disable()
                // 允许所有请求的CORS配置
                .cors().and()
                // 不创建会话
                .sessionManagement().sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS).and()
                // 对请求进行授权
                .authorizeRequests()
                // 允许所有API请求
                .antMatchers("/api/**").permitAll()
                // 允许静态资源
                .antMatchers("/", "/static/**", "/public/**", "*.html").permitAll()
                // 其他所有请求都需要验证
                .anyRequest().authenticated();

        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setExposedHeaders(Arrays.asList("Authorization"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册token拦截器，拦截所有/api/**的请求，但排除登录接口
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login", "/api/addUser");
    }
}