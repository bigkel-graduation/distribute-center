package com.itchenyang.time;


import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EarlyMorningTime {

    @SneakyThrows
    public Pair<Long, Long> getTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endTime = simpleDateFormat.format(calendar.getTime());
        calendar.add(Calendar.DATE, -1);
        String startTime = simpleDateFormat.format(calendar.getTime());
        long startTimeTamp = simpleDateFormat.parse(startTime).getTime();
        long endTimeTamp = simpleDateFormat.parse(endTime).getTime();
        return Pair.of(startTimeTamp,endTimeTamp);
    }
}
