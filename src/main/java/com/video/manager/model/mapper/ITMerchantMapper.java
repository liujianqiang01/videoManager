package com.video.manager.model.mapper;


import com.video.manager.model.TMerchant;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ITMerchantMapper extends Mapper<TMerchant> {
    /**
    *批量新增
    */
    int insertBatch(List<TMerchant> record);

    /**
    *根据主键Id删除
    */
    int deleteByPrimaryKey(Integer id);

    /**
    *根据主键Id查询
    */
    TMerchant selectByPrimaryKey(Integer id);

    /**
    *根据主键Id修改
    */
    int updateByPrimaryKeySelective(TMerchant record);

    /**
    *条件查询，返回集合
    */
    List<TMerchant> selectListByWhere(TMerchant record);

    /**
    *条件查询
    */
    TMerchant selectByWhere(TMerchant record);
}