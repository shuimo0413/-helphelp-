package xyz.dxstoolnavigation.service;

import xyz.dxstoolnavigation.pojo.Favourite;
import xyz.dxstoolnavigation.pojo.UrlData;

import java.util.List;

public interface UrlService {

    String addFavourite(Favourite favourite);

    // 添加表名参数
    List<UrlData> getCommonSoftware(String tableName);

    void addUrl(UrlData urlData);

    void
    deleteUrl(UrlData urlData);

    void updateUrl(UrlData urlData);

    String removeFavourite(Favourite favourite);

    List<Favourite> getFavourite(String userId);
}