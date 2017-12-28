package me.zgy.api;

import me.zgy.bean.dto.UserInfoDto;

/**
 * Created by Rayee on 2017/12/28.
 */
public interface UserInfoService {

    UserInfoDto query(Long id);

}
