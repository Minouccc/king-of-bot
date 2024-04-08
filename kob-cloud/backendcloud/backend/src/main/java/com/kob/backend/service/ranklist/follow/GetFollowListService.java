package com.kob.backend.service.ranklist.follow;

import com.kob.backend.pojo.Follow;

import java.util.List;
import java.util.Map;

public interface GetFollowListService {
    List<Follow> getList(Map<String, String> data);
}
