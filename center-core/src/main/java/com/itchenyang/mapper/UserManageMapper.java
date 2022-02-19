package com.itchenyang.mapper;

import com.itchenyang.entity.UserInformation;
import com.itchenyang.entity.UserSearchQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserManageMapper {
    // 根据角色分页获取用户列表
    List<UserInformation> getUserListPage(@Param("offset") Integer offset,
                                          @Param("limit") Integer limit,
                                          @Param("pid") Integer pid,
                                          @Param("cid") Integer cid,
                                          @Param("query")UserSearchQuery searchQuery,
                                          @Param("s_pid") Integer s_pid,
                                          @Param("s_cid") Integer s_cid);

    // 获取列表总数
    Integer getUserCount(@Param("offset") Integer offset,
                         @Param("limit") Integer limit,
                         @Param("pid") Integer pid,
                         @Param("cid") Integer cid,
                         @Param("query")UserSearchQuery searchQuery,
                         @Param("s_pid") Integer s_pid,
                         @Param("s_cid") Integer s_cid);

    Boolean deleteUser(@Param("id") Integer id);

    Boolean lockUserOrNot(@Param("id") Integer id, @Param("flag") Integer flag);
}
