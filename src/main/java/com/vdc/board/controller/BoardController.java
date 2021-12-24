package com.vdc.board.controller;

import com.vdc.board.common.util.Utils;
import com.vdc.board.service.interfaces.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @RequestMapping("current-date")
    public String currentDate(Model model) {
        model.addAttribute("currentDate", Utils.getCurrentDate());
        model.addAttribute("currentTime", Utils.getCurrentTime());
        return "/board/current-date";
    }

    @RequestMapping("total-call")
    public String totalCall(Model model) {

        Map<String, Object> totalCallInfo = boardService.getTotalCallInfo();
        model.addAttribute("totalCallInfo", totalCallInfo);

        return "/board/total-call";
    }

    @RequestMapping("job-call")
    public String jobCall(Model model) {

        Map<String, Map<String, Object>> jobCallInfo = boardService.getJobCallInfo();
        model.addAttribute("jobCallInfo", jobCallInfo);

        List<Map<String, Object>> jobCallInfoList = boardService.getJobCallInfoList();
        model.addAttribute("jobCallInfoList", jobCallInfoList);

        return "/board/job-call";
    }

    @RequestMapping("team-call")
    public String teamCall() {
        return "/board/team-call";
    }

    @RequestMapping("team-counselor")
    public String teamCounselor() {
        return "/board/team-counselor";
    }


}
