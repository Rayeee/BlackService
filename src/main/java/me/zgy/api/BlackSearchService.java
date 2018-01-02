package me.zgy.api;

import me.zgy.bean.dto.TBlack;
import me.zgy.bean.param.BlackSearchParam;

import java.util.List;

/**
 * 走ES查询
 * Created by Rayee on 2018/1/2.
 */
public interface BlackSearchService {

    List<TBlack> query(BlackSearchParam param);

}
