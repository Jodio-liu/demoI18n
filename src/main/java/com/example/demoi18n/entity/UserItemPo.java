package com.example.demoi18n.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserItemPo {
    private long id;
    private long userId;
    /**
     * 对象id
     */
    private long objId;
    /**
     * objId类型,与cn.j.chloe.joint.party.bean.ItemType对齐
     */
    private long objType;
    /**
     * 是否置顶
     */
    private int top;
    private Date creatTime;
    private Date updateTime;
}
