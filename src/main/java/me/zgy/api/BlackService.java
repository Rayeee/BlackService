package me.zgy.api;

import me.zgy.bean.dto.TBlack;
import me.zgy.bean.param.BlackSearchParam;

/**
 * Created by Rayee on 2018/1/2.
 */
public interface BlackService {

    TBlack query(BlackSearchParam param);

}
