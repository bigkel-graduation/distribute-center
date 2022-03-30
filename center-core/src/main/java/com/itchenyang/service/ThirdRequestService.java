package com.itchenyang.service;

import com.itchenyang.entity.MessageInformation;
import com.itchenyang.entity.UserInformation;
import com.itchenyang.entity.UserSearchQuery;

import java.util.Map;

public interface ThirdRequestService {
    Boolean saveRequestMessage(UserInformation userInformation);

    Integer getNotDealCount();

    Map<String, Object> getMessageList(Integer offset, Integer limit, UserSearchQuery searchQuery);

    Boolean dealRequestMessage(Integer id, Integer flag);

    MessageInformation getMsgInfo(Integer id);

    Boolean delMessage(Integer id);
}
