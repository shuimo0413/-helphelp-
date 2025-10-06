package xyz.dxstoolnavigation.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dxstoolnavigation.mapper.FeedbackMapper;
import xyz.dxstoolnavigation.pojo.Feedback;
import xyz.dxstoolnavigation.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;


    @Override
    public void addFeedback(Feedback feedback) {
        feedbackMapper.addFeedback(feedback);
    }
}
