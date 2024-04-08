package com.kob.backend.controller.record.like;

import com.kob.backend.service.record.like.AddLikeNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddLikeNumControlller {
    @Autowired
    private AddLikeNumService addLikeNumService;

    @GetMapping("/record/addLikeNum/")
    public Map<String, String> add(@RequestParam Map<String,String> data) {
        return addLikeNumService.addLikeNum(data);
    }
}
