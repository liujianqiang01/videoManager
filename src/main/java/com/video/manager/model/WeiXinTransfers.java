package com.video.manager.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-18
 * @Description:微信转账
 */
@Data
public class WeiXinTransfers implements Serializable {
    private String mch_appid;//商户账号appid
    private String mchid;//商户号
    private String appid;//商户账号appid
    private String nonce_str;//随机字符串
    private String partner_trade_no;//商户订单号
    private String openid;//用户openid
    private String check_name;//校验用户姓名选项 NO_CHECK：不校验真实姓名 FORCE_CHECK：强校验真实姓名
    private String re_user_name;//收款用户真实姓名。如果check_name设置为FORCE_CHECK，则必填用户真实姓名
    private String amount;//金额
    private String desc;//企业付款备注
    private String spbill_create_ip;//Ip地址
    private String sign;//签名
}