package com.kob.backend.service.record.comment;

import com.kob.backend.pojo.Comment;

import java.util.List;
import java.util.Map;

public interface GetCommentListService {
    List<Comment> getList(Map<String, String> data);
}
