package com.example.demoi18n.controller.swiftmessages.service.bo;

import lombok.Data;

@Data
public class ObtainMsgInfoReq {
    /**
     * 页码
     */
    private long pageIndex;
    /**
     * 消息数量
     */
    private long count;
    /**
     * 场景id，1001:跳舞
     */
    private long sceneId;
}
