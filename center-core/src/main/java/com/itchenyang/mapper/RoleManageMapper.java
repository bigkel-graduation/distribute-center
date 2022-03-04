package com.itchenyang.mapper;

import com.itchenyang.entity.Province;
import com.itchenyang.entity.UserInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleManageMapper {
    List<UserInformation> getUserByRole(@Param("pid") Integer pid, @Param("cid") Integer cid);

    Boolean delUser(@Param("id") Integer id);

    List<Province> canAddRole(@Param("pid") Integer pid, @Param("cid") Integer cid);

    Boolean addRole(@Param("pid") Integer pid, @Param("cid") Integer cid);

    Integer selectProvinceRole(@Param("pid") Integer pid);

    Boolean addProvince(@Param("pid") Integer pid);

    Boolean addCity(@Param("pid") Integer pid);

    Boolean delUserByRole(@Param("pid") Integer pid, @Param("cid") Integer cid);

    Boolean delCityRole(@Param("pid") Integer pid, @Param("cid") Integer cid);

    Boolean delProvinceRole(@Param("pid") Integer pid);
}
