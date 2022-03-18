package com.itchenyang.mapper;

import com.itchenyang.entity.UserInformation;
import com.itchenyang.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
    UserInformation getUserInformation(@Param("loginVO") UserInformation loginVO);

    UserRole getRoleInfomation(@Param("pid") Integer pid, @Param("cid") Integer cid);

    Boolean updateIpAddress(@Param("user") UserInformation userInformation);

    String getAddress(@Param("ip") String ip);
}
