package me.zgy.test;

import me.zgy.api.BlackSearchService;
import me.zgy.bean.dto.TBlack;
import me.zgy.bean.param.BlackSearchParam;
import me.zgy.exception.ServiceException;
import me.zgy.service.es.index.BlackIndexService;
import me.zgy.service.es.search.BlackIndexSearchService;
import me.zgy.utils.ChineseName;
import me.zgy.utils.JsonUtils;
import me.zgy.utils.test.IdCardGenerator;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rayee on 2017/12/28.
 */
public class ESTest extends BaseTest {

    @Resource
    private BlackIndexService blackIndexService;

    @Resource
    private BlackSearchService blackSearchService;

    @Test
    public void test01() throws ServiceException {
        blackIndexService.index(BlackSearchParam.builder().creditNo("320681190000000000").build());
    }

    @Test
    public void test02() throws ServiceException {
        List<TBlack> blacks = blackSearchService.query(BlackSearchParam.builder().creditNo("320681190000000000").build());
        System.out.println(JsonUtils.toJson(blacks));
    }

    @Test
    public void test03() throws ServiceException, IOException {
        long start = System.currentTimeMillis();
        for (int j = 0; j < 600; j++) {
            long start1 = System.currentTimeMillis();
            List<TBlack> list = new ArrayList<>(100);
            for (int i = 0; i < 30000; i++) {
                list.add(buildTBlack());
            }
            blackIndexService.batchIndex(list);
            list.clear();
            System.out.println("j = " + j + "3W数据耗时" + (System.currentTimeMillis() - start1) + "ms");
        }
        System.out.println("插入2000W数据耗时" + (System.currentTimeMillis() - start) + "ms");
    }

    IdCardGenerator generator = new IdCardGenerator();

    private TBlack buildTBlack() {
        TBlack black = new TBlack();
        black.setCreditNo(generator.generate());
        black.setChannel("abc");
        black.setEntityauthdate("2018-01-02");
        black.setEntityauthcode("code");
        black.setSeqno("qazwsx");
        black.setRelation("relation");
        black.setMobilePhone("13900000000");
        black.setMemberName(ChineseName.generate());
        black.setReasonCode("REASON_CODE");
        black.setRtmsg("message yeyeye");
        black.setIsblack("是");
        black.setAddTime(new Date());
        black.setUpdateTime(new Date());
        black.setRtcode("RT_CODE");
        return black;
    }

}
