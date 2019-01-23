package com.video.manager.model.mapper;


import com.video.manager.model.TMerchant;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ITMerchantMapper extends Mapper<TMerchant> {

    @Select({"select menchant_id as menchantId ,state from t_merchant where  state = 0 ORDER BY id limit 1 "})
    TMerchant selectRandomOne();
}