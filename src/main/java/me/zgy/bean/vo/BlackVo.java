package me.zgy.bean.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by Rayee on 2018/1/2.
 */
@Data
public class BlackVo {

    private Integer id;

    private Integer cid;

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
