package com.itchenyang.service.impl;

import com.itchenyang.entity.MessageInformation;
import com.itchenyang.entity.UserInformation;
import com.itchenyang.entity.UserRole;
import com.itchenyang.entity.UserSearchQuery;
import com.itchenyang.exception.Assert;
import com.itchenyang.mapper.LoginMapper;
import com.itchenyang.mapper.ThirdRequestMapper;
import com.itchenyang.result.ResponseEnum;
import com.itchenyang.service.ThirdRequestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ThirdRequestServiceImpl implements ThirdRequestService {

    @Resource
    private ThirdRequestMapper thirdRequestMapper;
    @Resource
    private LoginMapper loginMapper;

    @Override
    public Boolean saveRequestMessage(UserInformation userInformation) {
        return thirdRequestMapper.saveRequestMessage(userInformation);
    }

    @Override
    public Integer getNotDealCount() {
        return thirdRequestMapper.getNotDealCount();
    }

    @Override
    public Map<String, Object> getMessageList(Integer offset, Integer limit, UserSearchQuery searchQuery) {
        List<MessageInformation> messageLists = thirdRequestMapper.getMessageList(offset, limit, searchQuery);
        Integer count = thirdRequestMapper.getMessageCount(offset, limit, searchQuery);
        for (MessageInformation messageList : messageLists) {
            Integer rolePid = messageList.getRolePid();
            Integer roleCid = messageList.getRoleCid();
            String role;
            UserRole userRole = loginMapper.getRoleInfomation(rolePid,roleCid);
            Assert.notNull(userRole, ResponseEnum.ROLE_NOT_EXIST);
            if (userRole.getCity() == null || StringUtils.isBlank(userRole.getCity())) {
                role = userRole.getProvince();
            }else {
                role = userRole.getProvince() + "-" + userRole.getCity();
            }
            messageList.setUserRole(role);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("count",count);
        map.put("list",messageLists);
        return map;
    }

    @Override
    public Boolean dealRequestMessage(Integer id, Integer flag) {
        return thirdRequestMapper.dealRequestMessage(id,flag);
    }

    @Override
    public MessageInformation getMsgInfo(Integer id) {
        return thirdRequestMapper.getMsgInfo(id);
    }

    @Override
    public Boolean delMessage(Integer id) {
        return thirdRequestMapper.delMessage(id);
    }
}
