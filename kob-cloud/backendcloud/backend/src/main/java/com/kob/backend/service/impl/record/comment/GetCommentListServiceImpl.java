package com.kob.backend.service.impl.record.comment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.CommentMapper;
import com.kob.backend.pojo.Comment;
import com.kob.backend.service.record.comment.GetCommentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetCommentListServiceImpl implements GetCommentListService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> getList(Map<String, String> data) {
        Integer recordId = Integer.parseInt(data.get("recordId"));

        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("record_id",recordId);
        return commentMapper.selectList(queryWrapper);
    }
}
