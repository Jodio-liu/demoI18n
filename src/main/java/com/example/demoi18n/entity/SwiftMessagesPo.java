package com.example.demoi18n.entity;

import lombok.Data;

import java.util.Date;

/**
 * 快捷消息表返回结果对象
 */
@Data
public class SwiftMessagesPo {
    private long id;
    private String msgContent;
    private long score;
    private Date createTime;
    private Date updateTime;
}
