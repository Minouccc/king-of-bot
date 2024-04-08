package com.kob.backend.service.impl.record.comment;

import com.kob.backend.mapper.CommentMapper;
import com.kob.backend.pojo.Comment;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.record.comment.AddCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddCommentServiceImpl implements AddCommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public Map<String, String> addComment(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Integer recordId = Integer.parseInt(data.get("recordId"));
        String content = data.get("content");

        Map<String, String> map = new HashMap<>();

        if(content == null || content.length() == 0) {
            map.put("message","内容不能为空");
            return map;
        }

        Date now = new Date();
        Comment comment = new Comment(null,recordId, user.getPhoto(), user.getUsername(), content,now);
        commentMapper.insert(comment);
        map.put("message","success");
        return map;
    }
}
