package com.vdc.board.service.classes;

import com.vdc.board.repository.interfaces.BoardMapper;
import com.vdc.board.repository.interfaces.BoardRepository;
import com.vdc.board.service.interfaces.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service("boardService")
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    @Override
    public Map<String, Object> getTotalCallInfo() {
        return boardRepository.getTotalCallInfo();
    }

    @Override
    public Map<String, Map<String, Object>> getJobCallInfo() {
        return boardRepository.getJobCallInfo();
    }

    @Override
    public List<Map<String, Object>> getJobCallInfoList() {
        return boardRepository.getJobCallInfoList();
    }

    @Override
    public Map<String, Object> getTeamCallInfo() {
        return boardRepository.getTeamCallInfo();
    }

    @Override
    public List<Map<String, Object>> getIndividualPerformance() {
        return boardRepository.getIndividualPerformance();
    }

    @Override
    public Map<String, Object> getTeamCounselorInfo() {
        return boardRepository.getTeamCounselorInfo();
    }

    @Override
    public Map<String, Object> getTeamCounselorInfo2() {
        return boardRepository.getTeamCounselorInfo2();
    }

    @Override
    public List<Map<String, Object>> getIndividualCounselorState() {
        return boardRepository.getIndividualCounselorState();
    }

    @Override
    public Map<String, Object> getAgentStatus(Map<String, Object> map) {
        return boardMapper.getAgentStatus(map);
    }

    @Override
    public Map<String, Object> getQueueRealTime(Map<String, Object> map) {
        return boardMapper.getQueueRealTime(map);
    }

    @Override
    public Map<String, Object> getQueueCumulativeRate(Map<String, Object> map) {
        return boardMapper.getQueueCumulativeRate(map);
    }

    @Override
    public List<Map<String, Object>> getUserRealTime(Map<String, Object> map) {
        return boardMapper.getUserRealTime(map);
    }
}
