package me.zgy.test;

import me.zgy.exception.ServiceException;
import me.zgy.routing.DataSource;
import me.zgy.service.UserInfoIndexService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by Rayee on 2017/12/28.
 */
public class ESTest extends BaseTest {

    @Resource
    private UserInfoIndexService userInfoIndexService;

    @Test
    @DataSource("dataSource1")
    public void test() throws ServiceException {
        userInfoIndexService.index(48l);
    }

}
