package com.itchenyang.service.impl;

import com.itchenyang.entity.Province;
import com.itchenyang.entity.UserInformation;
import com.itchenyang.mapper.RoleManageMapper;
import com.itchenyang.service.RoleManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RoleManageServiceImpl implements RoleManageService {

    @Resource
    private RoleManageMapper roleManageMapper;

    @Override
    public List<UserInformation> getUserByRole(Integer pid, Integer cid) {
        return roleManageMapper.getUserByRole(pid, cid);
    }

    @Override
    public Boolean delUser(Integer id) {
        return roleManageMapper.delUser(id);
    }

    @Override
    public List<Province> canAddRole(Integer pid, Integer cid) {
        return roleManageMapper.canAddRole(pid,cid);
    }

    @Override
    public Boolean addRole(Integer pid, Integer cid) {
        Boolean addProvince = true;
        Boolean addCity = true;

        // 如果新增角色市级，其对应的省级没创建，则先创建其上级角色
        if (cid != 0) {
            Integer count = roleManageMapper.selectProvinceRole(pid);
            // 上级角色不存在
            if (count == 0) {
                addProvince = roleManageMapper.addProvince(pid);
            }
        }else {
            // 如果创建的是省级，则创建出其全部下级
            addCity = roleManageMapper.addCity(pid);
        }
        Boolean flag = roleManageMapper.addRole(pid, cid);
        return addProvince && addCity && flag;
    }

    @Override
    public Boolean delRole(Integer pid, Integer cid) {
        Boolean delProvince = true;
        // 删除用户
        Boolean delUser = roleManageMapper.delUserByRole(pid, cid);
        // 删除角色
        Boolean delCity = roleManageMapper.delCityRole(pid, cid);

        // 省级角色还得删掉本级
        if (cid == 0) {
            delProvince = roleManageMapper.delProvinceRole(pid);
        }
        return delCity && delProvince;
    }
}
