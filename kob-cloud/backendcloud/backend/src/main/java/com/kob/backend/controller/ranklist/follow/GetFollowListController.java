package com.kob.backend.controller.ranklist.follow;

import com.kob.backend.pojo.Follow;
import com.kob.backend.service.ranklist.follow.GetFollowListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetFollowListController {
    @Autowired
    private GetFollowListService getFollowListService;

    @GetMapping("/ranklist/follow/getlist/")
    public List<Follow> getList(@RequestParam Map<String, String> data){
        return getFollowListService.getList(data);
    }
}
