package com.example.demoi18n.controller.swiftmessages.service;

import com.example.demoi18n.controller.swiftmessages.api.vo.SwiftMessagesResponse;
import com.example.demoi18n.controller.swiftmessages.service.bo.ObtainMsgInfoReq;

public interface SwiftMessageInfoService {
    SwiftMessagesResponse obtainMessagesInfoWithScene(ObtainMsgInfoReq obtainMsgInfoReq);
}
