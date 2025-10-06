package xyz.dxstoolnavigation.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import xyz.dxstoolnavigation.pojo.Users;

@Mapper
public interface UserMapper {


    @Insert("insert into users(id,username, email, password, status,create_datetime,update_dateTime) values(#{id},#{username},#{email},#{password},#{status},#{createDatetime},#{updateDatetime})")
    void insert(Users user); // Mapper层保持不变

    @Update("update users set username=#{username},password=#{password},update_datetime=#{updateDatetime} where id=#{id}")
    void updateById(Users newUserMessage);
}