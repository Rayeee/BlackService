package me.zgy.service;

import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.indices.CreateIndex;
import me.zgy.api.UserInfoService;
import me.zgy.bean.dto.UserInfoDto;
import me.zgy.es.ESClientFactory;
import me.zgy.es.ESHandler;
import me.zgy.es.index.UserInfoIndex;
import me.zgy.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

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

    public void batchIndex(String address) throws ServiceException, IOException {
        List<UserInfoDto> infos = userInfoService.queryList(address);
        Bulk.Builder bulkBuilder = new Bulk.Builder();
        CreateIndex cIndex = new CreateIndex.Builder("ix_user_info").build();
        ESClientFactory.getClient().execute(cIndex);
        infos.forEach(e -> {
            Index index = new Index.Builder(e).index("ix_user_info").type("user_info").build();
            bulkBuilder.addAction(index);
        });
        ESClientFactory.getClient().execute(bulkBuilder.build());
    }

    private UserInfoIndex buildIndex(UserInfoDto dto){
        UserInfoIndex index = new UserInfoIndex();
        index.setId(dto.getId());
        index.setName(dto.getName());
        index.setEmail(dto.getEmail());
        index.setPassword(dto.getPassword());
        index.setDob(dto.getDob());
        index.setAddress(dto.getAddress());
        index.setCity(dto.getCity());
        index.setStateId(dto.getStateId());
        index.setZip(dto.getZip());
        index.setCountryId(dto.getCountryId());
        index.setGender(dto.getGender());
        index.setCreatedAt(dto.getCreatedAt());
        index.setUpdatedAt(dto.getUpdatedAt());
        return index;
    }

}
