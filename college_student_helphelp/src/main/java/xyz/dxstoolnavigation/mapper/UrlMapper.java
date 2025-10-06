package xyz.dxstoolnavigation.mapper;

import org.apache.ibatis.annotations.*;
import xyz.dxstoolnavigation.pojo.Favourite;
import xyz.dxstoolnavigation.pojo.UrlData;

import java.util.List;

@Mapper
public interface UrlMapper {

    // 修改为接受表名参数的通用查询
    @Select("select name,icon_url,url,information from ${tableName}")
    List<UrlData> getCommonSoftware(@Param("tableName") String tableName);
    @Insert("insert into ${tableName}(name,icon_url,url,information) values(#{name},#{iconUrl},#{url},#{information})")
    void addUrl(UrlData urlData);

    @Delete("Delete from ${tableName} where name=#{name} or icon_url=#{iconUrl} or url=#{url}")
    void deleteUrl(UrlData urlData);


    @Update("UPDATE ${tableName} set name = #{name}, icon_url = #{iconUrl}, url = #{url}, information = #{information} where name = #{beforeName}")
    void updateUrl(UrlData urlData);

    // 检查收藏是否存在
    @Select("select count(*) from collect where url = #{url} and id = #{userId}")
    Integer checkFavourite(Favourite favourite);

    @Insert("insert into collect(id,name,icon_url,url,information) values(#{userId}, #{name},#{iconUrl},#{url},#{information})")
    void addFavourite(Favourite favourite);


    @Delete("delete from collect where id = #{userId} and url = #{url}")
    void removeFavourite(Favourite favourite);

    @Select("select name,icon_url,url,information from collect where id = #{userId}")
    List<Favourite> getFavourite(String userId);
}