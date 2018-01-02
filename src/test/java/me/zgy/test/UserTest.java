package me.zgy.test;

import me.zgy.bean.param.UserInfoParam;
import me.zgy.utils.ChineseName;
import me.zgy.utils.JsonUtils;
import me.zgy.utils.RandomMail;
import org.junit.Test;

import java.util.Date;
import java.util.Random;

/**
 * Created by Rayee on 2017/12/29.
 */
public class UserTest extends BaseTest {

    Random random = new Random(2 << 10);

    private static final long dayMillionSeconds = 1000 * 3600 * 24;

    @Test
    public void addUser() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            UserInfoParam param = new UserInfoParam();
            param.setName(ChineseName.generate());
            param.setEmail(RandomMail.generate());
            param.setPassword(random.nextInt() + "");
            param.setDob(new Date());
            param.setAddress("上海");
            param.setCity("2");
            userInfoService.addUser(param);
        }
    }

    @Test
    public void queryList() {
        System.out.println(JsonUtils.toJson(userInfoService.queryList("上海")));
    }

}
