package me.zgy.mapping;

import me.zgy.bean.entity.UserInfoEntity;
import me.zgy.bean.vo.UserInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by zhugongyi on 2017/5/5.
 */
public interface UserInfoDao {

    int add(UserInfoEntity userInfoEntity);

    UserInfoVo queryById(@Param("id") Long id);

    List<UserInfoVo> queryList(@Param("address") String address);

    List<UserInfoVo> queryByAddress(@Param("address") String address);

    int updateNameById(@Param("id") Long id, @Param("name") String name, @Param("version") Integer version);

}
