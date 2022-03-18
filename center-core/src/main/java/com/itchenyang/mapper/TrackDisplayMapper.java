package com.itchenyang.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TrackDisplayMapper {
    List<Map<String, Object>> getUserDisplay();

    List<Map<String, Object>> getUserTrack();
}
