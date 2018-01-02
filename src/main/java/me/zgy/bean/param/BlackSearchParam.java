package me.zgy.bean.param;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Rayee on 2018/1/2.
 */
@Builder
@Data
public class BlackSearchParam extends BaseSearchParam {

    private String creditNo;

}
