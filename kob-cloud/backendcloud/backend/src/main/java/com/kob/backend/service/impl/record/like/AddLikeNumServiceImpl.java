package com.kob.backend.service.impl.record.like;

import com.kob.backend.mapper.RecordMapper;
import com.kob.backend.pojo.Record;
import com.kob.backend.service.record.like.AddLikeNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddLikeNumServiceImpl implements AddLikeNumService {
    @Autowired
    private RecordMapper recordMapper;
    @Override
    public Map<String, String> addLikeNum(Map<String, String> data) {
        Integer id = Integer.parseInt(data.get("recordId"));
        Record record = recordMapper.selectById(id);

        Record new_Record = new Record(
                record.getId(),
                record.getAId(),
                record.getASx(),
                record.getASy(),
                record.getBId(),
                record.getBSx(),
                record.getBSy(),
                record.getASteps(),
                record.getBSteps(),
                record.getMap(),
                record.getLoser(),
                record.getABotId(),
                record.getBBotId(),
                record.getLikeNum()+1,
                record.getCreatetime()
        );

        recordMapper.updateById(new_Record);
        Map<String, String> map = new HashMap<>();
        map.put("message","success");
        return map;
    }
}
