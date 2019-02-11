package com.video.manager.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TVipCodes implements Serializable {
     //
    private Integer id;

     //vip编码
    private String vipCode;

     //vip类型 0-免费试用，1-月卡，2-季卡，3-年卡
    private Integer vipType;

     //有效期 /天
    private Integer indate;

     //状态 0-失效，1-有效
    private Integer vipState;

    private String merchantId;

    private Date exportDate;



}