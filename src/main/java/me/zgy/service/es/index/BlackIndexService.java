package me.zgy.service.es.index;

import com.google.common.collect.Lists;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.indices.CreateIndex;
import me.zgy.api.BlackService;
import me.zgy.bean.dto.TBlack;
import me.zgy.bean.param.BlackSearchParam;
import me.zgy.cst.BlackCst;
import me.zgy.es.ESClientFactory;
import me.zgy.es.index.base.BlackIndex;
import me.zgy.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by Rayee on 2018/1/2.
 */
@Service
public class BlackIndexService extends ESIndexService<BlackIndex> {


    @Resource
    private BlackService blackService;

    public void index(BlackSearchParam param) throws ServiceException {
        //查询数据
        TBlack black = blackService.query(param);
        index(buildIndex(black), BlackCst.BLACK_INDEX_NAME, BlackCst.BLACK_INDEX_TYPE);
    }

//    public void batchIndex(BlackSearchParam param) throws ServiceException, IOException {
//        List<TBlack> infos = Lists.newArrayList();
//        Bulk.Builder bulkBuilder = new Bulk.Builder();
//        CreateIndex cIndex = new CreateIndex.Builder("ix_user_info").build();
//        ESClientFactory.getClient().execute(cIndex);
//        infos.forEach(e -> {
//            Index index = new Index.Builder(e).index("ix_user_info").type("user_info").build();
//            bulkBuilder.addAction(index);
//        });
//        ESClientFactory.getClient().execute(bulkBuilder.build());
//    }

    private BlackIndex buildIndex(TBlack black) {
        return BlackIndex.builder()
                .cid(black.getCid())
                .credit_no(black.getCreditNo())
                .member_name(black.getMemberName())
                .reason_code(black.getReasonCode())
                .add_time(black.getAddTime())
                .update_time(black.getUpdateTime())
                .isblack(black.getIsblack())
                .channel(black.getChannel())
                .mobile_phone(black.getMobilePhone())
                .relation(black.getRelation())
                .rtcode(black.getRtcode())
                .rtmsg(black.getRtmsg())
                .seqno(black.getSeqno())
                .entityauthcode(black.getEntityauthcode())
                .entityauthdate(black.getEntityauthdate())
                .build();
    }

}
