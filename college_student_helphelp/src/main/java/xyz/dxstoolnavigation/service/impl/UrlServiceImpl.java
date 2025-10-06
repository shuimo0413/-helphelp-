package xyz.dxstoolnavigation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dxstoolnavigation.mapper.UrlMapper;
import xyz.dxstoolnavigation.pojo.Favourite;
import xyz.dxstoolnavigation.pojo.UrlData;
import xyz.dxstoolnavigation.service.UrlService;
import xyz.dxstoolnavigation.utils.HumpConversion;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlMapper urlMapper;

    //    添加收藏
    @Override
    public String addFavourite(Favourite favourite) {
//        先检查在不在
        Integer count = urlMapper.checkFavourite(favourite);

        if (count > 0) {
            return "该收藏已存在";
        }


        log.info("添加收藏，信息：{}", favourite);
        urlMapper.addFavourite(favourite);
        return "添加收藏成功";
    }

    @Override
    public List<UrlData> getCommonSoftware(String tableName) {
        List<UrlData> data = urlMapper.getCommonSoftware(tableName);
        log.info("获取表{}数据成功，信息：{}", tableName, data);
        return data;
    }

    @Override
    public void addUrl(UrlData urlData) {
        urlMapper.addUrl(urlData);
    }

    @Override
    public void deleteUrl(UrlData urlData) {
        // 转换表名
        urlData.setTableName(HumpConversion.humpToUnderline(urlData.getTableName()));
        log.info("删除信息，信息：{}", urlData);
        urlMapper.deleteUrl(urlData);
        log.info("删除了对应的url{}", urlData);

    }

    @Override
    public void updateUrl(UrlData urlData) {
        log.info("修改信息,之前的名字为{},修改后的名字为{}", urlData.getBeforeName(), urlData.getName());
        urlMapper.updateUrl(urlData);
        log.info("修改成功");
    }

    @Override
    public String removeFavourite(Favourite favourite) {
        urlMapper.removeFavourite(favourite);
        return "删除收藏成功";
    }

    @Override
    public List<Favourite> getFavourite(String userId) {
        List<Favourite> favourite = urlMapper.getFavourite(userId);
        return favourite;
    }


}