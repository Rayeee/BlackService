package me.zgy.test;

import me.zgy.bean.dto.TBlack;
import me.zgy.bean.param.BlackSearchParam;
import me.zgy.utils.JsonUtils;
import org.junit.Test;

/**
 * Created by Rayee on 2018/1/2.
 */
public class BlackQueryTest extends BaseTest {

    @Test
    public void test01(){
        TBlack black = blackService.query(BlackSearchParam.builder().creditNo("320681190000000000").build());
        System.out.println(JsonUtils.toJson(black));
    }

}
