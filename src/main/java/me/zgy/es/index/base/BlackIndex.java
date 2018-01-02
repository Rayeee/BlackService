package me.zgy.es.index.base;

import io.searchbox.annotations.JestId;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Created by Rayee on 2018/1/2.
 */
@Builder
@Data
public class BlackIndex extends BaseIndex {

    private Integer cid;

    @JestId
    private String credit_no;

    private String member_name;

    private String reason_code;

    private Date add_time;

    private Date update_time;

    private String isblack;

    private String channel;

    private String mobile_phone;

    private String relation;

    private String rtcode;

    private String rtmsg;

    private String seqno;

    private String entityauthcode;

    private String entityauthdate;

}
