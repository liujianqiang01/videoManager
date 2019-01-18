package com.video.manager.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class TSettleAccount implements Serializable {
    /**
     * 主键
     * 表字段 : t_settle_account.id
     */
    @Id
    private Integer id;

    /**
     * 汇款状态 0-未汇款，1-已汇款
     * 表字段 : t_settle_account.remittance_state
     */
    private Integer remittanceState;

    /**
     * 结算总金额
     * 表字段 : t_settle_account.settle_account_price
     */
    private BigDecimal settleAccountPrice;


    /**
     * 结算时间
     * 表字段 : t_settle_account.settle_account_end_time
     */
    private Date settleAccountTime;

    /**
     * 提成金额
     * 表字段 : t_settle_account.rate_price
     */
    private BigDecimal ratePrice;

    /**
     * 商户Id
     * 表字段 : t_settle_account.merchant_id
     */
    private String merchantId;
}