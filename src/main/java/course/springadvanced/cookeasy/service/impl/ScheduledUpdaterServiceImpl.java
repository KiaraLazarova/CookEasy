package course.springadvanced.cookeasy.service.impl;

import course.springadvanced.cookeasy.service.CommentService;
import course.springadvanced.cookeasy.service.ScheduledUpdaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ScheduledUpdaterServiceImpl implements ScheduledUpdaterService {
    private final CommentService commentService;

    @Autowired
    public ScheduledUpdaterServiceImpl(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    @PostConstruct
    @Scheduled(cron = "0 0 12 * * ?")
    public void scheduleDatabaseUpdate() {
        this.commentService.deleteArchivedComments();
    }
}