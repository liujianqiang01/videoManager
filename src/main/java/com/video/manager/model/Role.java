package com.video.manager.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 系统角色
 * Create by Raye on 2018年3月1日21:13:03
 */
@Data
public class Role {
    @Id
    private Integer id;

    private String name;

    private Date createtime;

    private Integer creator;

    private String description;

    private Integer updateuser;

    private Date updatetime;
    @Transient
    private int userid;
    @Transient
    private String createuser;
}
