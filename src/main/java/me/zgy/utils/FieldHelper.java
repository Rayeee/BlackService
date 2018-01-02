package me.zgy.utils;

import com.google.common.collect.Lists;

import java.util.List;

import static me.zgy.cst.BlackCst.*;

/**
 * Created by Rayee on 2018/1/2.
 */
public class FieldHelper {

    public static List<String> getBlackBaseFiled() {
        return Lists.newArrayList(
                INDEX_CREDIT_NO,
                INDEX_CID,
                INDEX_MEMBER_NAME,
                INDEX_REASON_CODE,
                INDEX_ADD_TIME,
                INDEX_UPDATE_TIME,
                INDEX_ISBLACK,
                INDEX_CHANNEL,
                INDEX_MOBILE_PHONE,
                INDEX_RELATION,
                INDEX_RTCODE,
                INDEX_RTMSG,
                INDEX_SEQNO,
                INDEX_ENTITYAUTHCODE,
                INDEX_ENTITYAUTHDATE);
    }

}
