package com.video.manager.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class TOrder implements Serializable {
     //主键Id
     @Id
    private Integer id;

     //订单编码
    private String orderCode;

     //订单状态 0-新建，1-待支付，2-支付成功
    private Integer orderState;

     //订单金额
    private BigDecimal orderPrice;

     //商户Id
    private String merchantId;

     //用户Id
    private String openId;

     //vip编码
    private String vipCode;

     //vip状态 0-失效，1-有效
    private Integer vipState;

     //vip开始时间
    private Date vipStartTime;

     //vip结束时间
    private Date vipEndTime;

     //三方订单
    private String thirdOederCode;
    //vip开始时间
    @Transient
    private String vipStartDate;

    //vip结束时间
    @Transient
    private String vipEndDate;

    //第三方预支付id
    private String prepayId;

    //卡名称
    @Transient
    private String vipName;

    //是否展示激活码
    @Transient
    private boolean showVipCode = false;

    private Integer settleAccountState;

}