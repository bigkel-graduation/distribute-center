package com.itchenyang.service;

import java.util.List;
import java.util.Map;

public interface TrackDisplayService {
    List<Map<String, Object>> getUserDisplay();

    List<Map<String, Object>> getUserTrack();
}
