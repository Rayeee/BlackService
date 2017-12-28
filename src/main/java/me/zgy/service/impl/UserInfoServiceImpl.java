package me.zgy.service.impl;

import me.zgy.api.UserInfoService;
import me.zgy.bean.dto.UserInfoDto;
import me.zgy.bean.vo.UserInfoVo;
import me.zgy.mapping.UserInfoDao;
import me.zgy.routing.DataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Rayee on 2017/12/28.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    @DataSource("dataSource1")
    public UserInfoDto query(Long id) {
        UserInfoDto dto = new UserInfoDto();
        UserInfoVo userInfoVo = userInfoDao.queryById(id);
        if(userInfoVo != null){
            BeanUtils.copyProperties(userInfoVo, dto);
        }
        return dto;
    }
}
