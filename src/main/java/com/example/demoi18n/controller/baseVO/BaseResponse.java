package com.example.demoi18n.controller.baseVO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

/**
 * Created by ligang on 2017/4/1.
 */
public class BaseResponse {

    protected int statusCode = 200;
    protected int bizStatus;
    protected String bizMessage;
    @JsonIgnore
    protected Map<String, Object> headerMap;


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getBizStatus() {
        return bizStatus;
    }

    public void setBizStatus(int bizStatus) {
        this.bizStatus = bizStatus;
    }

    public String getBizMessage() {
        return bizMessage;
    }

    public void setBizMessage(String bizMessage) {
        this.bizMessage = bizMessage;
    }

    public Map<String, Object> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, Object> headerMap) {
        this.headerMap = headerMap;
    }
}
