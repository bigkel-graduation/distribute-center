package com.itchenyang.service.impl;

import com.itchenyang.mapper.TrackDisplayMapper;
import com.itchenyang.service.TrackDisplayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TrackDisplayServiceImpl implements TrackDisplayService {

    @Resource
    private TrackDisplayMapper trackDisplayMapper;

    @Override
    public List<Map<String, Object>> getUserDisplay() {
        return trackDisplayMapper.getUserDisplay();
    }

    @Override
    public List<Map<String, Object>> getUserTrack() {
        return trackDisplayMapper.getUserTrack();
    }
}
