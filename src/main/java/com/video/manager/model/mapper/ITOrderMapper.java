package com.video.manager.model.mapper;

import com.video.manager.model.TOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import java.math.BigDecimal;
import java.util.Date;

public interface ITOrderMapper extends Mapper<TOrder> {

    @Select({"SELECT SUM(order_price) as orderPrice FROM t_order WHERE merchant_id = #{merchantId} and order_state = 3 and vip_start_time < #{date}"})
    BigDecimal selectSumOrderPriceByName(@Param("date") Date date , @Param("merchantId") String merchantId);

    @Update({"update t_order set settle_account_state = 1 WHERE merchant_id = #{merchantId} and order_state = 3 and vip_start_time < #{date}"})
    void updateSumOrderPriceByName(@Param("date") Date date , @Param("merchantId") String merchantId);



}