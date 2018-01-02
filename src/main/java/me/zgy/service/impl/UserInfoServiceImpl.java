package me.zgy.service.impl;

import com.google.common.collect.Lists;
import me.zgy.api.UserInfoService;
import me.zgy.bean.dto.UserInfoDto;
import me.zgy.bean.entity.UserInfoEntity;
import me.zgy.bean.param.UserInfoParam;
import me.zgy.bean.vo.UserInfoVo;
import me.zgy.mapping.UserInfoDao;
import me.zgy.routing.DataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Rayee on 2017/12/28.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    @DataSource("dataSource1")
    public void addUser(UserInfoParam param) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        BeanUtils.copyProperties(param, userInfoEntity);
        userInfoDao.add(userInfoEntity);
    }

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

    @Override
    @DataSource("dataSource1")
    public List<UserInfoDto> queryList(String address) {
        List<UserInfoDto> result = Lists.newArrayList();
        List<UserInfoVo> list = userInfoDao.queryList(address);
        if(!CollectionUtils.isEmpty(list)){
            list.forEach(e -> {
                UserInfoDto dto = new UserInfoDto();
                BeanUtils.copyProperties(e, dto);
                result.add(dto);
            });
        }
        return result;
    }
}
