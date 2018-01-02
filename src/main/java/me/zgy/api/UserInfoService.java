package me.zgy.api;

import me.zgy.bean.dto.UserInfoDto;
import me.zgy.bean.param.UserInfoParam;

import java.util.List;

/**
 * Created by Rayee on 2017/12/28.
 */
public interface UserInfoService {

    void addUser(UserInfoParam param);

    UserInfoDto query(Long id);

    List<UserInfoDto> queryList(String address);

}
