package com.vdc.board.controller;

import com.vdc.board.common.enums.*;
import com.vdc.board.common.util.Utils;
import com.vdc.board.service.interfaces.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private static final boolean IS_TEST = true;

    private final BoardService boardService;

    // AgentStatus(로그인, 대기, 통화중, 후처리, 이석)
    // QueueRealTime(대기호, 평균대기시간, 서비스레벨)
    // QueueCumulative_Rate(인입, 응대, 포기, 응대율, 서비스레벨) - 15분마다 레코드 추가
    // getUserRealTime(개인별 상태)

    @RequestMapping("current-date")
    public String currentDate(@RequestParam Map<String, Object> param, Model model) {

        model.addAttribute("season", getSeason(param.get("season")));

        model.addAttribute("currentDate", Utils.getCurrentDate());
        model.addAttribute("currentTime", Utils.getCurrentTime());

        return "/board/current-date";
    }

    @RequestMapping("total-call")
    public String totalCall(@RequestParam Map<String, Object> param, Model model) {

        model.addAttribute("season", getSeason(param.get("season")));

        Map<String, Object> queueRealTime = null;

        if (IS_TEST) {
            queueRealTime = boardService.getTotalCallInfo();
        } else {
            Map<String, Object> queueCumulativeRate = boardService.getQueueCumulativeRate(param).get(0);
            queueRealTime = boardService.getQueueRealTime(param);
            queueRealTime.putAll(queueCumulativeRate);
            Map<String, Object> agentStatus = boardService.getAgentStatus(param);
            queueRealTime.putAll(agentStatus);
        }

        model.addAttribute("queueRealTime", queueRealTime);

        return "/board/total-call";
    }

    @RequestMapping("job-call")
    public String jobCall(@RequestParam Map<String, Object> param, Model model) {

        model.addAttribute("season", getSeason(param.get("season")));

        List<Map<String, Object>> jobCallInfoList = null;

        if (IS_TEST) {
            jobCallInfoList = boardService.getJobCallInfoList();
        } else {
            jobCallInfoList = boardService.getQueueCumulativeRate(param);
        }

        model.addAttribute("jobCallInfoList", jobCallInfoList);

        return "/board/job-call";
    }

    @RequestMapping("team-call")
    public String teamCall(@RequestParam Map<String, Object> param, Model model) {

        model.addAttribute("season", getSeason(param.get("season")));

        Map<String, Object> queueRealTime = null;
        List<Map<String, Object>> userRealTime = null;

        if (IS_TEST) {
            queueRealTime = boardService.getTeamCallInfo();
            userRealTime = boardService.getIndividualPerformance();
        } else {
            Map<String, Object> queueCumulativeRate = boardService.getQueueCumulativeRate(param).get(0);
            queueRealTime = boardService.getQueueRealTime(param);
            queueRealTime.putAll(queueCumulativeRate);

            userRealTime = boardService.getUserRealTime(param);
        }

        model.addAttribute("queueRealTime", queueRealTime);
        model.addAttribute("userRealTime", userRealTime);

        return "/board/team-call";
    }

    @RequestMapping("team-counselor")
    public String teamCounselor(@RequestParam Map<String, Object> param, Model model) {

        model.addAttribute("season", getSeason(param.get("season")));

        Map<String, Object> queueRealTime = null;
        Map<String, Object> agentStatus = null;
        List<Map<String, Object>> userRealTime = null;

        if (IS_TEST) {
            queueRealTime = boardService.getTeamCounselorInfo();
            agentStatus = boardService.getTeamCounselorInfo2();
            userRealTime = boardService.getIndividualCounselorState();
        } else {
            Map<String, Object> queueCumulativeRate = boardService.getQueueCumulativeRate(param).get(0);
            queueRealTime = boardService.getQueueRealTime(param);
            queueRealTime.putAll(queueCumulativeRate);

            agentStatus = boardService.getAgentStatus(param);

            userRealTime = boardService.getUserRealTime(param);
        }

        model.addAttribute("queueRealTime", queueRealTime);
        model.addAttribute("agentStatus", agentStatus);
        model.addAttribute("userRealTime", userRealTime);

        return "/board/team-counselor";
    }

    @RequestMapping("test-data")
    public String testData(@RequestParam Map<String, Object> param, Model model) {

        System.out.println(param);
        Map<String, Object> testData = boardService.getAgentStatus(param);
        System.out.println(testData);

        return "/board/team-counselor";
    }

    public Season getSeason(Object value) {
        return Season.values()[Integer.parseInt((String) value) - 1];
    }

}
