package com.example.demoi18n.controller.swiftmessages.service.Impl;

import com.example.demoi18n.controller.swiftmessages.api.vo.SwiftMessagesResponse;
import com.example.demoi18n.controller.swiftmessages.service.SwiftMessageInfoService;
import com.example.demoi18n.controller.swiftmessages.service.bo.MessageInfoBo;
import com.example.demoi18n.controller.swiftmessages.service.bo.ObtainMsgInfoReq;
import com.example.demoi18n.entity.MsgIdMapSceneIdsPo;
import com.example.demoi18n.entity.SwiftMessagesPo;
import com.example.demoi18n.mapper.MessagesSceneSlaveMapper;
import com.example.demoi18n.mapper.SwiftMessagesSlaveMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class SwiftMessageInfoServiceImpl implements SwiftMessageInfoService {
    @Resource
    private MessagesSceneSlaveMapper messagesSceneSlaveMapper;
    @Resource
    private SwiftMessagesSlaveMapper swiftMessagesSlaveMapper;

    /**
     * 通过场景id，获取快捷消息信息
     *
     * @param obtainMsgInfoReq 入参
     * @return SwiftMessagesResponse
     */
    @Override
    public SwiftMessagesResponse obtainMessagesInfoWithScene(ObtainMsgInfoReq obtainMsgInfoReq) {
        SwiftMessagesResponse swiftMessagesResponse = new SwiftMessagesResponse();
        List<Integer> msgIds = messagesSceneSlaveMapper.getMsgIdBySceneId(obtainMsgInfoReq.getSceneId());
        List<MsgIdMapSceneIdsPo> sceneIdsByMsgIds = messagesSceneSlaveMapper.getSceneIdsByMsgIds(msgIds);
        if (msgIds.size() <= 0) {
            swiftMessagesResponse.setShow(false);
            return swiftMessagesResponse;
        }
        int pageIndex = (int) obtainMsgInfoReq.getPageIndex();
        int count = (int) obtainMsgInfoReq.getCount();
        PageHelper.startPage(pageIndex, count);
        List<SwiftMessagesPo> msgInfos = swiftMessagesSlaveMapper.selectMsgInfoByMsgIds(msgIds);

        swiftMessagesResponse.setTotal(((Page<?>) msgInfos).getTotal());
        if (msgInfos.size() < count) {
            //如果最后一页不够三条，补充到三条，即count数量
            supplementMsgInfos(msgInfos, msgIds, pageIndex, count);
        }
        List<MessageInfoBo> messages = Lists.newArrayList();
        msgInfos.forEach(msgInfo -> {
            MessageInfoBo messageInfoBo = new MessageInfoBo();
            messageInfoBo.setMsgId(msgInfo.getId());
            messageInfoBo.setMsgContent(msgInfo.getMsgContent());
            messageInfoBo.setScore(msgInfo.getScore());
            messages.add(messageInfoBo);
        });
        swiftMessagesResponse.setMessages(messages);

        return swiftMessagesResponse;

    }

    private void supplementMsgInfos(List<SwiftMessagesPo> msgInfos, List<Integer> msgIds, int pageIndex, int count) {
        int supplementCount = count- msgInfos.size();
        List<Integer> tmpList = msgIds.subList(0, count * (pageIndex - 1));
        Collections.shuffle(tmpList);
        msgInfos.addAll(swiftMessagesSlaveMapper.selectMsgInfoByMsgIds(tmpList.subList(0,supplementCount)));
    }
}
