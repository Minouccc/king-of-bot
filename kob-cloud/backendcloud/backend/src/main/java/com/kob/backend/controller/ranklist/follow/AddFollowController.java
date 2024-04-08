package com.kob.backend.controller.ranklist.follow;

import com.kob.backend.service.ranklist.follow.AddFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddFollowController {
    @Autowired
    private AddFollowService addFollowService;

    @PostMapping("/ranklist/follow/")
    public Map<String, String> addFollow(@RequestParam Map<String, String> data) {
        return addFollowService.addFollow(data);
    }
}
