package com.vdc.board.service.interfaces;

import java.util.List;
import java.util.Map;

public interface BoardService {

    Map<String, Object> getTotalCallInfo();

    Map<String, Map<String, Object>> getJobCallInfo();

    List<Map<String, Object>> getJobCallInfoList();
}
