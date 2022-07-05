package com.example.demoi18n.mapper;

import com.example.demoi18n.entity.SwiftMessagesPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SwiftMessagesSlaveMapper {
    List<SwiftMessagesPo> getMsgInfoByMsgIds(@Param("msgIds") List<Integer> msdIds);
}
