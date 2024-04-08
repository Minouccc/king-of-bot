package com.kob.backend.service.impl.ranklist.follow;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.FollowMapper;
import com.kob.backend.pojo.Follow;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.ranklist.follow.AddFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddFollowServiceImpl implements AddFollowService {
    @Autowired
    private FollowMapper followMapper;
    @Override
    public Map<String, String> addFollow(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser(); // 关注者

        String flag = data.get("flag"); // 1表示关注 -1表示取消关注
        Integer followeeId = Integer.parseInt(data.get("followeeId"));


        Map<String, String> map = new HashMap<>();

        if(flag.equals("1")){
            Follow follow = new Follow(null, user.getId(), followeeId);
            followMapper.insert(follow);
            map.put("message","success");
            return map;
        }else if(flag.equals("-1")) {
            // 查询关注表中是否存在对应的关注记录
            QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("follower_id", user.getId())
                    .eq("followee_id", followeeId);
            Follow follow = followMapper.selectOne(queryWrapper);
            if (follow != null) {
                // 存在关注记录，进行取消关注操作
                followMapper.deleteById(follow.getId());
                map.put("message", "success");
            } else {
                map.put("message", "failure"); // 关注关系不存在，取消失败
            }
        }

        return map;
    }
}
