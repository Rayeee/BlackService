package me.zgy.service;

import me.zgy.api.UserInfoService;
import me.zgy.bean.dto.UserInfoDto;
import me.zgy.es.ESHandler;
import me.zgy.es.index.UserInfoIndex;
import me.zgy.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Rayee on 2017/12/28.
 */
@Service
public class UserInfoIndexService extends ESIndexService<UserInfoIndex> {

    @Resource
    private UserInfoService userInfoService;

    public void index(Long id) throws ServiceException {
        UserInfoDto userInfoDto = userInfoService.query(id);
        index(buildIndex(userInfoDto), ESHandler.getIndexName("user_info_", userInfoDto.getCreatedAt()), "user-info");
    }

    private UserInfoIndex buildIndex(UserInfoDto dto){
        UserInfoIndex index = new UserInfoIndex();
        index.setId(dto.getId());
        index.setName(dto.getName());
        return index;
    }

}
