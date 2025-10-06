package xyz.dxstoolnavigation.pojo;

import lombok.Data;

@Data
public class UrlData {
//    所在的表名，可能没有
    private String tableName;
    private String beforeName;

    private String name;
    private String iconUrl;
    private String url;
    private String information;

}
