package me.zgy.es.index.base;

import io.searchbox.annotations.JestId;
import lombok.Builder;

import java.util.Date;

/**
 * Created by Rayee on 2018/1/2.
 */
@Builder
public class BlackIndex extends BaseIndex {

    private Integer cid;

    @JestId
    private String creditNo;

    private String memberName;

    private String reasonCode;

    private Date addTime;

    private Date updateTime;

    private String isblack;

    private String channel;

    private String mobilePhone;

    private String relation;

    private String rtcode;

    private String rtmsg;

    private String seqno;

    private String entityauthcode;

    private String entityauthdate;

}
