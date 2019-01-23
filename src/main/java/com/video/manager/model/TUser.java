package com.video.manager.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@ToString
public class TUser implements Serializable {
     //用户id
     @Id
    private Integer userId;

     //电话
    private String phone;

     //用户微信唯一身份识别码
    private String openId;

     //微信昵称
    private String nickName;

     //用户类型 0-普通用户，1-商户
    private Integer userType;

     //商户Id
    private String menchantId;

     //性别 0-未知、1-男、2-女
    private Integer gender;

     //省
    private String province;

     //市
    private String city;

     //区
    private String country;

    //用户头像
    private String avatarUrl;

    //申请状态
    private Integer applyState;

    //申请理由
    private String applyReason;

}