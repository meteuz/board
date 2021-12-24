package com.vdc.board.repository.interfaces;

import java.util.List;
import java.util.Map;

public interface BoardRepository {

    // Total Call
    Map<String, Object> getTotalCallInfo();

    // Job Call
    Map<String, Map<String, Object>> getJobCallInfo();
    List<Map<String, Object>> getJobCallInfoList();

    // Team Call
    Map<String, Object> getTeamCallInfo();
    List<Map<String, Object>> getIndividualPerformance();

}
