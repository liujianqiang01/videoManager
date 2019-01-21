package com.video.manager.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class TWholesaleOrder implements Serializable {
    /**
     * 主键
     * 表字段 : t_wholesale_order.id
     */
    @Id
    private Integer id;

    /**
     * 采购订单编号
     * 表字段 : t_wholesale_order.order_code
     */
    private String orderCode;

    /**
     * 订单总金额
     * 表字段 : t_wholesale_order.order_price
     */
    private BigDecimal orderPrice;

    /**
     * 订单详情
     * 表字段 : t_wholesale_order.order_desc
     */
    private String orderDesc;

    /**
     * 创建时间
     * 表字段 : t_wholesale_order.create_time
     */
    private Date createTime;

    /**
     * 订单状态 1-待支付，2-已支付，3-支付成功，4-支付失败
     * 表字段 : t_wholesale_order.order_state
     */
    private Integer orderState;

    /**
     * 微信凭证
     * 表字段 : t_wholesale_order.open_id
     */
    private String openId;

    /**
     * 商户Id
     * 表字段 : t_wholesale_order.merchant_id
     */
    private String merchantId;

    /**
     * 单方订单
     * 表字段 : t_wholesale_order.third_order_code
     */
    private String thirdOrderCode;

    /**
     * 预支付订单
     * 表字段 : t_wholesale_order.prepay_id
     */
    private String prepayId;
}