package com.vdc.board.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    // AgentStatus(로그인, 대기, 통화중, 후처리, 이석)
    Map<String, Object> getAgentStatus(Map<String, Object> map);

    // QueueRealTime(대기호, 평균대기시간, 서비스레벨)
    Map<String, Object> getQueueRealTime(Map<String, Object> map);

    // QueueCumulative_Rate(인입, 응대, 포기, 응대율)
    List<Map<String, Object>> getQueueCumulativeRate(Map<String, Object> map);

    // getUserRealTime(개인별 상태)
    List<Map<String, Object>> getUserRealTime(Map<String, Object> map);

    // dept name
    Map<String, Object> getDeptNm(Map<String, Object> map);
}
