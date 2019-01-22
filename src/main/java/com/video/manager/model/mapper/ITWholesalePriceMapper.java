package com.video.manager.model.mapper;

import com.video.manager.model.TWholesalePrice;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @Author: liujianqiang
 * @Date: 2019-01-21
 * @Description:
 */
public interface ITWholesalePriceMapper  extends Mapper<TWholesalePrice> {

    /**
     * 判断是否存在
     * @param wholesalePrice
     * @return
     */
    @Select({"SELECT id,vip_type as vipType, vip_count_start as vipCountStart, vip_count_end as vipCountEnd, vip_price as vipPrice" +
            " FROM t_wholesale_price WHERE vip_type=#{vipType} and ((vip_count_start <= #{vipCountStart} " +
            " and vip_count_end >= #{vipCountStart}) or (vip_count_start <= #{vipCountEnd}" +
            " and vip_count_end >= #{vipCountEnd})) and state = 1 "})
    List<TWholesalePrice> chenkExist(TWholesalePrice wholesalePrice);
}
