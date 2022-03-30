package com.itchenyang.mapper;

import com.itchenyang.entity.MessageInformation;
import com.itchenyang.entity.UserInformation;
import com.itchenyang.entity.UserSearchQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ThirdRequestMapper {
    Boolean saveRequestMessage(@Param("userInfo") UserInformation userInformation);

    Integer getNotDealCount();

    List<MessageInformation> getMessageList(@Param("offset") Integer offset,
                                            @Param("limit") Integer limit,
                                            @Param("query") UserSearchQuery searchQuery);

    Integer getMessageCount(@Param("offset") Integer offset,
                            @Param("limit") Integer limit,
                            @Param("query") UserSearchQuery searchQuery);

    Boolean dealRequestMessage(@Param("id") Integer id,
                               @Param("flag") Integer flag);

    MessageInformation getMsgInfo(@Param("id") Integer id);

    Boolean delMessage(@Param("id") Integer id);
}
