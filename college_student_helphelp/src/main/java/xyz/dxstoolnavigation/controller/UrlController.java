package xyz.dxstoolnavigation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.dxstoolnavigation.pojo.Favourite;
import xyz.dxstoolnavigation.pojo.Result;
import xyz.dxstoolnavigation.pojo.UrlData;
import xyz.dxstoolnavigation.service.UrlService;

import javax.swing.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UrlController {
    @Autowired
    private UrlService urlService;
    @GetMapping("/common_software")
    public Result commonSoftware() {
        log.info("获取常用软件");
        List<UrlData> commonSoftware = urlService.getCommonSoftware("common_software");
        log.info("controller获取常用软件成功，常用软件信息：{}", commonSoftware);
        return Result.success(commonSoftware);
    }
    @GetMapping("/life_tool")
    public Result lifeTool() {
        log.info("获取生活工具");
        List<UrlData> lifeTool = urlService.getCommonSoftware("life_tool");
        log.info("controller获取生活工具成功，生活工具信息：{}", lifeTool);
        return Result.success(lifeTool);
    }

    @GetMapping("/exam_information")
    public Result examInformation(){
        log.info("获取考试信息");
        List<UrlData> examInformation = urlService.getCommonSoftware("exam_information");
        log.info("controller获取考试信息成功，考试信息：{}", examInformation);
        return Result.success(examInformation);
    }
    @GetMapping("/graduate_student_information")
    public Result graduateStudentInformation(){
//        log.info("获取研究生信息");
        List<UrlData> graduateStudentInformation = urlService.getCommonSoftware("graduate_student_information");
        log.info("controller获取研究生信息成功，研究生信息：{}", graduateStudentInformation);
        return Result.success(graduateStudentInformation);
    }
    @GetMapping("/practice_information")
    public Result practiceInformation(){
//        log.info("获取实习信息");
        List<UrlData> practiceInformation = urlService.getCommonSoftware("practice_information");
        log.info("controller获取实习信息成功，实习信息：{}", practiceInformation);
        return Result.success(practiceInformation);
    }
    @GetMapping("/competition_information")
    public Result competetionInformation(){
        List<UrlData> competitionInformation = urlService.getCommonSoftware("competition_information");
        log.info("controller获取比赛信息成功，比赛信息：{}", competitionInformation);
        return Result.success(competitionInformation);
    }

    @GetMapping("/domestic_academic_resources")
    public Result domesticAcademicResources(){
        List<UrlData> domesticAcademicResources = urlService.getCommonSoftware("domestic_academic_resources");
        log.info("controller获取国内学术资源成功，国内学术资源：{}", domesticAcademicResources);
        return Result.success(domesticAcademicResources);
    }
    @GetMapping("/foreign_academic_resources")
    public Result foreignAcademicResources(){
        List<UrlData> foreignAcademicResources = urlService.getCommonSoftware("foreign_academic_resources");
        log.info("controller获取国外学术资源成功，国外学术资源：{}", foreignAcademicResources);
        return Result.success(foreignAcademicResources);
    }


    //文件根目录
    private static final String UPLOAD_ROOT = "C:/Users/17433/Desktop/main/ico/";
    private static final String UPLOAD_ROOT1 = "./ico/";
//    增加信息
    @PostMapping("/addUrl")
    public Result addUrl(@RequestBody UrlData urlData){
        log.info("增加信息，信息：{}", urlData);
//        传过来的iconUrl是文件名，所以记得修改
        urlData.setIconUrl(UPLOAD_ROOT1 + urlData.getIconUrl());
        log.info(urlData.getIconUrl());
        urlService.addUrl(urlData);
        return Result.success();
    }

    @PostMapping("/addUrlImage")
    public Result addUrlImage(@RequestParam("image") MultipartFile image, @RequestParam("path") String path) {
        if (image.isEmpty()){
            return Result.error("文件为空");
        }
        String filePath = UPLOAD_ROOT + path + '/'+ image.getOriginalFilename();
//        保存文件
        try {
            image.transferTo(new java.io.File(filePath));
        } catch (Exception e) {
            return Result.error("文件保存失败");
        }

        return Result.success();
    }

//    删除信息
    @PostMapping("/deleteUrl")
    public Result deleteUrl(@RequestBody UrlData urlData){
        log.info("删除信息，信息：{}", urlData);
        urlService.deleteUrl(urlData);
        return Result.success();
    }


//    修改信息
    @PostMapping("/updateUrl")
    public Result updateUrl(@RequestBody UrlData urlData){
        log.info("修改信息，信息：{}", urlData);
        urlService.updateUrl(urlData);
        return Result.success();
    }

//    添加收藏
    @PostMapping("/addFavourite")
    public Result addFavourite(@RequestBody Favourite favourite){
        log.info("添加收藏，信息：{}", favourite);
        String res = urlService.addFavourite(favourite);
        log.info("添加收藏，结果：{}", res);
        return Result.success(res);
    }
    @PostMapping("/removeFavourite")
    public Result removeFavourite(@RequestBody Favourite favourite){
        log.info("删除收藏，信息：{}", favourite);
        String res = urlService.removeFavourite(favourite);
        log.info("删除收藏，结果：{}", res);
        return Result.success(res);
    }

    @GetMapping("/getFavourite")
    public Result getFavourite(@RequestParam String userId){
        log.info("获取收藏，用户id：{}", userId);
        List<Favourite> favourites = urlService.getFavourite(userId);
        log.info("获取收藏，结果：{}", favourites);
        return Result.success(favourites);
    }

    
}
