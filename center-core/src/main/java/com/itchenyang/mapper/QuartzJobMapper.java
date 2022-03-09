package com.itchenyang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itchenyang.entity.QuartzJobEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuartzJobMapper extends BaseMapper<QuartzJobEntity> {
}
