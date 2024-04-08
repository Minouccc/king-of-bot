package com.kob.backend.service.impl.record.like;

import com.kob.backend.mapper.RecordMapper;
import com.kob.backend.pojo.Record;
import com.kob.backend.service.record.like.GetLikeNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetLikeNumServiceImpl implements GetLikeNumService {
    @Autowired
    private RecordMapper recordMapper;
    @Override
    public Map<String, String> getLikeNum(Map<String, String> data) {
        Integer id = Integer.parseInt(data.get("recordId"));
        Record record = recordMapper.selectById(id);
        Map<String, String> map = new HashMap<>();

        map.put("message","success");
        map.put("likeNum",record.getLikeNum().toString());
        return map;
    }
}
