package com.example.demoi18n.controller.api.vo;

import lombok.Data;

@Data
public class MessageInfoVo {
    /**
     * 消息id
     */
    private long msgId;
    /**
     * 消息内容
     */
    private String msgContent;
    /**
     * 消息权重
     */
    private long score;
}
