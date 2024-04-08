package com.kob.backend.controller.record.comment;

import com.kob.backend.service.record.comment.AddCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddCommentController {
    @Autowired
    private AddCommentService addCommentService;

    @PostMapping("/record/comment/add/")
    public Map<String, String> addComment(@RequestParam Map<String, String> data) {
        return addCommentService.addComment(data);
    }
}
