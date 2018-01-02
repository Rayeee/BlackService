package me.zgy.utils;

/**
 * Created by Rayee on 2017/12/29.
 */
public class RandomMail {

    public static String generate() throws InterruptedException {
        Thread.sleep(1);
        return System.currentTimeMillis() + "@163.com";
    }

}
