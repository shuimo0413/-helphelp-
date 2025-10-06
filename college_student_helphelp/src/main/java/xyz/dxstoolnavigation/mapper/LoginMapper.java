package xyz.dxstoolnavigation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.dxstoolnavigation.pojo.Users;

@Mapper
public interface LoginMapper {

    @Select("select * from users where username = #{username}")
    Users selectByUsername(String username);

    @Select("select * from users where id = #{userId}")
    Users selectById(String userId);

}