package com.example.demoi18n.controller.api.vo;

import lombok.Data;

@Data
public class SwiftMessagesRequest{
    /**
     * 页码
     */
    private long pageIndex;
    /**
     * 消息数量，默认3条
     */
    private long count = 3;
    /**
     * 场景id，1001:跳舞
     */
    private long sceneId;

}
