package me.zgy.mapping;

import me.zgy.bean.query.BlackQuery;
import me.zgy.bean.vo.BlackVo;

/**
 * Created by Rayee on 2018/1/2.
 */
public interface BlackDao {

    BlackVo queryObj(BlackQuery query);

}
