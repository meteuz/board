package com.vdc.board.service.classes;

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
}
