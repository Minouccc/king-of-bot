package com.kob.backend.controller.record.comment;

import com.kob.backend.pojo.Comment;
import com.kob.backend.service.record.comment.GetCommentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetCommentListController {
    @Autowired
    private GetCommentListService getCommentListService;

    @GetMapping("/record/comment/getlist")
    public List<Comment> getList(@RequestParam Map<String, String> data){
        return getCommentListService.getList(data);
    }
}
