package com.itchenyang.mapper;

import com.itchenyang.entity.Province;
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
                                          @Param("query")UserSearchQuery searchQuery);

    // 获取列表总数
    Integer getUserCount(@Param("offset") Integer offset,
                         @Param("limit") Integer limit,
                         @Param("pid") Integer pid,
                         @Param("cid") Integer cid,
                         @Param("query")UserSearchQuery searchQuery);

    Boolean deleteUser(@Param("id") Integer id);

    Boolean lockUserOrNot(@Param("id") Integer id, @Param("flag") Integer flag);

    Boolean updateOrInsertUser(@Param("user") UserInformation userInformation);

    List<Province> getProvince(@Param("pid") Integer pid,
                               @Param("cid") Integer cid);
}
