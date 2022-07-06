package com.example.demoi18n.controller.swiftmessages.service.bo;

import lombok.Data;

@Data
public class MessageInfoBo {
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
