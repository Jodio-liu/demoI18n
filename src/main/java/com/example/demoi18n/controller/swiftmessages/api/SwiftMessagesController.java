package com.example.demoi18n.controller.swiftmessages.api;

import com.example.demoi18n.controller.baseVO.ChloeResponse;
import com.example.demoi18n.controller.swiftmessages.api.vo.SwiftMessagesRequest;
import com.example.demoi18n.controller.swiftmessages.api.vo.SwiftMessagesResponse;
import com.example.demoi18n.controller.swiftmessages.service.Impl.SwiftMessageInfoServiceImpl;
import com.example.demoi18n.controller.swiftmessages.service.bo.MessageInfoBo;
import com.example.demoi18n.controller.swiftmessages.service.bo.ObtainMsgInfoReq;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SwiftMessagesController {

    @Resource
    private SwiftMessageInfoServiceImpl swiftMessageInfoService;

    @GetMapping("/obtainSwiftMessages")
    public ChloeResponse obtainSwiftMessages(SwiftMessagesRequest request){
        ChloeResponse chloeResponse = new ChloeResponse();
        SwiftMessagesResponse response = new SwiftMessagesResponse();
        //入参校验
        if(request.getSceneId() <= 0){
            response.setShow(false);
            chloeResponse.setData(response);
            return chloeResponse;
        }
        Preconditions.checkArgument(request.getPageIndex() > 0, "页码错误，请检查");
        Preconditions.checkArgument(request.getCount() > 0, "获取条数应大于0，请检查");

        response = swiftMessageInfoService.obtainMessagesInfoWithScene(buildReq(request));

        chloeResponse.setData(response);
        return chloeResponse;
    }

    private ObtainMsgInfoReq buildReq(SwiftMessagesRequest request) {
        ObtainMsgInfoReq obtainMsgInfoReq = new ObtainMsgInfoReq();
        obtainMsgInfoReq.setPageIndex(request.getPageIndex());
        obtainMsgInfoReq.setCount(request.getCount());
        obtainMsgInfoReq.setSceneId(request.getSceneId());
        return obtainMsgInfoReq;
    }
}
