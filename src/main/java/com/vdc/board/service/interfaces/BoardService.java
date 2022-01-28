package com.vdc.board.service.interfaces;

import java.util.List;
import java.util.Map;

public interface BoardService {

    Map<String, Object> getTotalCallInfo();

    Map<String, Map<String, Object>> getJobCallInfo();
    List<Map<String, Object>> getJobCallInfoList();

    Map<String, Object> getTeamCallInfo();
    List<Map<String, Object>> getIndividualPerformance();

    Map<String, Object> getTeamCounselorInfo();
    Map<String, Object> getTeamCounselorInfo2();
    List<Map<String, Object>> getIndividualCounselorState();



    Map<String, Object> getAgentStatus(Map<String, Object> map);
    Map<String, Object> getQueueRealTime(Map<String, Object> map);
    List<Map<String, Object>> getQueueCumulativeRate(Map<String, Object> map);
    List<Map<String, Object>> getUserRealTime(Map<String, Object> map);
    Map<String, Object> getDeptNm(Map<String, Object> map);
}
