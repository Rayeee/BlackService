package me.zgy.api;

import me.zgy.bean.dto.UserInfoDto;
import me.zgy.bean.param.QueryParam;

import java.util.List;

/**
 * Created by Rayee on 2017/12/29.
 */
public interface BlackService {

    List<UserInfoDto> queryList(QueryParam param);

}
