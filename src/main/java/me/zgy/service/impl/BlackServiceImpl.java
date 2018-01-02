package me.zgy.service.impl;

import me.zgy.api.BlackService;
import me.zgy.bean.dto.TBlack;
import me.zgy.bean.param.BlackSearchParam;
import me.zgy.bean.query.BlackQuery;
import me.zgy.bean.vo.BlackVo;
import me.zgy.mapping.BlackDao;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Rayee on 2018/1/2.
 */
@Service
public class BlackServiceImpl implements BlackService {

    @Resource
    private BlackDao blackDao;

    @Override
    public TBlack query(BlackSearchParam param) {
        return buildTBlack(blackDao.queryObj(BlackQuery.builder().creditNo(param.getCreditNo()).build()));
    }

    private TBlack buildTBlack(BlackVo vo) {
        TBlack black = new TBlack();
        BeanUtils.copyProperties(vo, black);
        return black;
    }

}
