package com.itchenyang.mapper;

import com.itchenyang.entity.UserInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserManageMapper {
    // 根据角色分页获取用户列表
    List<UserInformation> getUserList(@Param("offset") Integer offset,
                                      @Param("limit") Integer limit,
                                      @Param("pid") Integer pid,
                                      @Param("cid") Integer cid);
}
