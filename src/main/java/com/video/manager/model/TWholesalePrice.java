package com.video.manager.model;


import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class TWholesalePrice implements Serializable {
    /**
     * 主键
     * 表字段 : t_wholesale_price.id
     */
    @Id
    private Integer id;

    /**
     * vip类型 1-月卡，2-季卡，3-年卡
     * 表字段 : t_wholesale_price.vip_type
     */
    private Integer vipType;

    /**
     * 采购价格
     * 表字段 : t_wholesale_price.vip_price
     */
    private BigDecimal vipPrice;

    /**
     * 采购数量区间开始
     * 表字段 : t_wholesale_price.vip_count_start
     */
    private Integer vipCountStart;

    /**
     * 采购数量区间结束
     * 表字段 : t_wholesale_price.vip_count_end
     */
    private Integer vipCountEnd;
}