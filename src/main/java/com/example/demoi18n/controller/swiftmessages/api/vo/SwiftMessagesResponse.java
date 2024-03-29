package com.example.demoi18n.controller.swiftmessages.api.vo;

import com.example.demoi18n.controller.baseVO.BaseRespData;
import com.example.demoi18n.controller.swiftmessages.service.bo.MessageInfoBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
public class SwiftMessagesResponse extends BaseRespData {
    /**
     * 是否展示快捷消息
     */
    private boolean show;
    /**
     * 对应场景支持消息总数
     */
    private long total;
    /**
     * 消息列表
     */
    private List<MessageInfoBo> messages;
    /**
     * 随机种子
     */
    private long seed;

}
