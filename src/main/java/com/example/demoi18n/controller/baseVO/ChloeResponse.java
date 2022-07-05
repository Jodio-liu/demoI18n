package com.example.demoi18n.controller.baseVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChloeResponse extends BaseResponse {
    protected BaseRespData data;
    public static final ChloeResponse SUCCESS = new ChloeResponse();
    public static final ChloeResponse INNER_ERROR = new ChloeResponse(-1, "inner error");

    public ChloeResponse(int bizStatus, String bizMessage) {
        this.setBizStatus(bizStatus);
        this.setBizMessage(bizMessage);
    }

    public ChloeResponse(BaseRespData data) {
        this.data = data;
    }
}
