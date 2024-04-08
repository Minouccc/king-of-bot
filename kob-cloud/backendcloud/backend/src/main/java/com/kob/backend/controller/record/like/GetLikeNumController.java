package com.kob.backend.controller.record.like;

import com.kob.backend.service.record.like.GetLikeNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetLikeNumController {
    @Autowired
    private GetLikeNumService getLikeNumService;

    @GetMapping("/record/getLikeNum/")
    public Map<String, String> add(@RequestParam Map<String,String> data) {
        return getLikeNumService.getLikeNum(data);
    }
}
