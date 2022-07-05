package com.example.demoi18n.controller.api;

import com.example.demoi18n.controller.api.vo.MessageInfoVo;
import com.example.demoi18n.controller.api.vo.SwiftMessagesRequest;
import com.example.demoi18n.controller.api.vo.SwiftMessagesResponse;
import com.example.demoi18n.controller.baseVO.ChloeResponse;
import com.example.demoi18n.mapper.MessagesSceneSlaveMapper;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SwiftMessagesController {
    @Resource
    private MessagesSceneSlaveMapper messagesSceneSlaveMapper;

    @GetMapping("/obtainSwiftMessages")
    public ChloeResponse obtainSwiftMessages(SwiftMessagesRequest request){
        SwiftMessagesResponse response = new SwiftMessagesResponse();
        //入参校验
        ChloeResponse chloeResponse = new ChloeResponse();
//        chloeResponse.setData(buildResp((int)request.getPageIndex(), (int)request.getCount()));
        List<Integer> msgIdBySceneId = messagesSceneSlaveMapper.getMsgIdBySceneId(request.getSceneId());
        return chloeResponse;
    }

    private SwiftMessagesResponse buildResp(int pageIndex, int pageSize ){
        SwiftMessagesResponse response = new SwiftMessagesResponse();
        response.setShow(true);

        PageHelper.startPage(pageIndex, pageSize);
        List<MessageInfoVo> list = Lists.newArrayList();
        for(int i=0; i<13; i++){
            MessageInfoVo messageInfoVo = new MessageInfoVo();
            messageInfoVo.setMsgId(i);
            messageInfoVo.setMsgContent("测试消息" + i);
            messageInfoVo.setScore(100-i);
            list.add(messageInfoVo);
        }
        response.setTotal(((Page)list).getTotal());
        response.setMessages(list);
        return response;
    }
}
