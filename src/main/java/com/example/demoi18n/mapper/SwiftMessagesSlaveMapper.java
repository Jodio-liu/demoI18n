package com.example.demoi18n.mapper;

import com.example.demoi18n.entity.SwiftMessagesPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SwiftMessagesSlaveMapper {
    List<SwiftMessagesPo> selectMsgInfoByMsgIds(@Param("msgIds") List<Integer> msdIds,
                                                @Param("seed") Long seed);
    int insert(@Param("bean") SwiftMessagesPo bean,
               @Param("key1") String key1);
}
