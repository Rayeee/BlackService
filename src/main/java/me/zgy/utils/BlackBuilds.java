package me.zgy.utils;

import com.google.common.collect.Lists;
import me.zgy.bean.dto.TBlack;
import me.zgy.es.ESSearchResult;
import me.zgy.es.index.base.BlackIndex;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rayee on 2018/1/2.
 */
public class BlackBuilds {

    public static List<TBlack> buildBlacks(ESSearchResult<BlackIndex> result){
        if(CollectionUtils.isEmpty(result.getHits())){
            return Lists.newArrayList();

        }
        return result.getHits().stream().map(BlackBuilds::buildTBlack).collect(Collectors.toList());
    }

    public static TBlack buildTBlack(BlackIndex index){
        TBlack black = new TBlack();
        black.setCid(index.getCid());
        black.setCreditNo(index.getCredit_no());
        black.setMemberName(index.getMember_name());
        black.setReasonCode(index.getReason_code());
        black.setAddTime(index.getAdd_time());
        black.setUpdateTime(index.getUpdate_time());
        black.setIsblack(index.getIsblack());
        black.setChannel(index.getChannel());
        black.setMobilePhone(index.getMobile_phone());
        black.setRelation(index.getRelation());
        black.setRtcode(index.getRtcode());
        black.setRtmsg(index.getRtmsg());
        black.setSeqno(index.getSeqno());
        black.setEntityauthcode(index.getEntityauthcode());
        black.setEntityauthdate(index.getEntityauthdate());
        return black;
    }

}
