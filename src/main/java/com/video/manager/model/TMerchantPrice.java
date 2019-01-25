package com.video.manager.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TMerchantPrice implements Serializable {
    /**
     * 
     * 表字段 : t_merchant_price.id
     */
    @Id
    private Integer id;

    /**
     * 商户ID
     * 表字段 : t_merchant_price.merchan_id
     */
    private String merchanId;

    /**
     * 月卡价格
     * 表字段 : t_merchant_price.month_card_price
     */
    private BigDecimal monthCardPrice;

    /**
     * 季卡价格
     * 表字段 : t_merchant_price.season_card_price
     */
    private BigDecimal seasonCardPrice;

    /**
     * 
     * 表字段 : t_merchant_price.year_card_price
     */
    private BigDecimal yearCardPrice;

    /**
     * 状态 0-申请，1-通过，2-失效
     * 表字段 : t_merchant_price.state
     */
    private Integer state;

    /**
     * 
     * 表字段 : t_merchant_price.create_time
     */
    private Date createTime;

}