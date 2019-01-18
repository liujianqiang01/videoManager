package com.video.manager.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-18
 * @Description:
 */
@Data
public class WeiXinTransfersReturn implements Serializable {
    private String return_code;//SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
    private String return_msg;//返回信息，如非空，为错误原因 签名失败 参数格式校验错误
    private String result_code;//业务结果：SUCCESS/FAIL，注意：当状态为FAIL时，存在业务结果未明确的情况。如果如果状态为FAIL，请务必关注错误代码（err_code字段），通过查询查询接口确认此次付款的结果
    private String err_code;//错误码信息，注意：出现未明确的错误码时（SYSTEMERROR等），请务必用原商户订单号重试，或通过查询接口确认此次付款的结果。
    private String err_code_des;//错误代码描述
    private String partner_trade_no;//商户订单号
    private String payment_no;//微信付款单号
    private String payment_time;//付款成功时间
}