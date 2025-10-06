package xyz.dxstoolnavigation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.dxstoolnavigation.pojo.Result;
import xyz.dxstoolnavigation.pojo.Feedback;
import xyz.dxstoolnavigation.service.FeedbackService;

@Slf4j
@RestController
@RequestMapping("/api")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;


    @PostMapping("/feedback")
    public Result feedback(@RequestBody Feedback feedback) {
        log.info("反馈信息:{}", feedback);
        feedbackService.addFeedback(feedback);
        return Result.success();
    }
}
