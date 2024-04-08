package com.kob.backend.service.impl.ranklist.follow;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.FollowMapper;
import com.kob.backend.pojo.Follow;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.ranklist.follow.GetFollowListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetFollowListServiceImpl implements GetFollowListService {
    @Autowired
    private FollowMapper followMapper;
    @Override
    public List<Follow> getList(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        String type = data.get("type"); // 1表示只查userId相关 2表示查全部
        if(type.equals("1")){
            QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("follower_id",user.getId());
            return followMapper.selectList(queryWrapper);
        }else { // 否则返回全部的 list
            return followMapper.selectList(null);
        }
    }
}
