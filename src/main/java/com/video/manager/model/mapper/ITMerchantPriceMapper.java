package com.video.manager.model.mapper;

import com.video.manager.model.TMerchantPrice;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-25
 * @Description:
 */
public interface ITMerchantPriceMapper extends Mapper<TMerchantPrice> {
    @Update({"update t_merchant_price set state = #{state} where merchan_id = #{merchanId}"})
    void updateState(TMerchantPrice merchantPrice);

}