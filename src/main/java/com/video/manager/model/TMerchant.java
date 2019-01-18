package com.video.manager.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TMerchant implements Serializable {
     //主键
     @Id
    private Integer id;

     //商户id
    private String menchantId;

     //商户名称
    private String menchantName;

    private String menchantAddr;

    private String mobile;

    private BigDecimal rate;

    private Integer state;

    public TMerchant() {
    }
}