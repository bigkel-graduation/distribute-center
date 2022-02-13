package com.itchenyang.service;

import com.itchenyang.entity.UserInformation;
import com.itchenyang.result.R;

public interface LoginService {
    public R login(UserInformation loginVO);
}
