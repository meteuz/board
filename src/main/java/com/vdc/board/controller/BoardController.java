package com.vdc.board.controller;

import com.vdc.board.common.enums.*;
import com.vdc.board.service.interfaces.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    // /total-call?dept=1&season=1&wc=50&ar=50

    @RequestMapping("current-date")
    public String currentDate(@RequestParam Map<String, Object> param, Model model) {

        model.addAttribute("season", getSeason(param.get("season")));

        return "/board/current-date";
    }

    @RequestMapping("total-call")
    public String totalCall(@RequestParam Map<String, Object> param, Model model) {

        model.addAttribute("season", getSeason(param.get("season")));
        model.addAttribute("dept", param.get("dept"));
        model.addAttribute("wc", param.get("wc"));
        model.addAttribute("ar", param.get("ar"));
        model.addAttribute("title", Title.values()[0].getTitleStr());

        return "/board/total-call";
    }

    @RequestMapping("getTotalCall")
    public ModelAndView getTotalCall(@RequestParam Map<String, Object> param, ModelAndView mv) {

        Map<String, Object> queueRealTime = null;

        if (IS_TEST) {
            queueRealTime = boardService.getTotalCallInfo();
        } else {
            Map<String, Object> queueCumulativeRate = boardService.getQueueCumulativeRate(param).size() == 0 ? null : boardService.getQueueCumulativeRate(param).get(0);
            queueRealTime = boardService.getQueueRealTime(param);
            Map<String, Object> agentStatus = boardService.getAgentStatus(param);

            if (queueRealTime != null) {
                if (queueCumulativeRate != null) {
                    queueRealTime.putAll(queueCumulativeRate);
                }

                if (agentStatus != null) {
                    queueRealTime.putAll(agentStatus);
                }
            }
        }

        mv.setViewName("jsonView");
        mv.addObject("data", queueRealTime);

        return mv;
    }

    @RequestMapping("job-call")
    public String jobCall(@RequestParam Map<String, Object> param, Model model) {

        model.addAttribute("season", getSeason(param.get("season")));
        model.addAttribute("dept", param.get("dept"));
        model.addAttribute("wc", param.get("wc"));
        model.addAttribute("ar", param.get("ar"));
        model.addAttribute("title", Title.values()[1].getTitleStr());

        return "/board/job-call";
    }

    @RequestMapping("getJobCall")
    public ModelAndView getJobCall(@RequestParam Map<String, Object> param, ModelAndView mv) {

        List<Map<String, Object>> jobCallInfoList = null;

        if (IS_TEST) {
            jobCallInfoList = boardService.getJobCallInfoList();
        } else {
            jobCallInfoList = boardService.getQueueCumulativeRate(param);
        }

        mv.setViewName("jsonView");
        mv.addObject("data", jobCallInfoList);

        return mv;
    }

    @RequestMapping("team-call")
    public String teamCall(@RequestParam Map<String, Object> param, Model model) {

        model.addAttribute("season", getSeason(param.get("season")));
        model.addAttribute("dept", param.get("dept"));
        model.addAttribute("wc", param.get("wc"));
        model.addAttribute("ar", param.get("ar"));
//        model.addAttribute("deptNm", boardService.getDeptNm(param));
        model.addAttribute("title", Title.values()[2].getTitleStr() + "(" + boardService.getDeptNm(param).get("Dept_Nm") + ")");

        return "/board/team-call";
    }

    @RequestMapping("getTeamCall")
    public ModelAndView getTeamCall(@RequestParam Map<String, Object> param, ModelAndView mv) {

        Map<String, Object> queueRealTime = null;
        List<Map<String, Object>> userRealTime = null;

        if (IS_TEST) {
            queueRealTime = boardService.getTeamCallInfo();
            userRealTime = boardService.getIndividualPerformance();
        } else {
            Map<String, Object> queueCumulativeRate = boardService.getQueueCumulativeRate(param).size() == 0 ? null : boardService.getQueueCumulativeRate(param).get(0);
            queueRealTime = boardService.getQueueRealTime(param);

            if (queueRealTime != null) {
                if (queueCumulativeRate != null) {
                    queueRealTime.putAll(queueCumulativeRate);
                }
            }

            userRealTime = boardService.getUserRealTime(param);
        }

        mv.setViewName("jsonView");
        mv.addObject("data1", queueRealTime);
        mv.addObject("data2", userRealTime);

        return mv;
    }

    // PresenceState
    // ACTIVE(HandlingState) : 상담원전화상태(code_cmm 4)
    // BUSY(RoutingState, RoutingStateReasonKey) : 이석(code_cmm 1) -> 0 : 이석, 1 : Service Out, 2 : Automatic, 4 : 식사, 6 : 휴식, 8 : 교육, 10 : 업무
    // else(PresenceState) : 상담원상태(code_cmm 5) -> 99 : 시스템 이석, 98 : 상담원 이석, 97 : 후처리, 96 : 식사, 95 : 교육, 94 : 회의
    // AWAY : 이석 , WORK : 후처리, IDLE : 대기, BUSY : 상담중(통화중)
    // INIT : 초기 , MEAL : 식사, BREAK : 휴식, EDUCATION : 교육, JOB : 업무
    @RequestMapping("team-counselor")
    public String teamCounselor(@RequestParam Map<String, Object> param, Model model) {

        model.addAttribute("season", getSeason(param.get("season")));
        model.addAttribute("dept", param.get("dept"));
        model.addAttribute("wc", param.get("wc"));
        model.addAttribute("ar", param.get("ar"));
//        model.addAttribute("deptNm", boardService.getDeptNm(param));
        model.addAttribute("title", Title.values()[3].getTitleStr() + "(" + boardService.getDeptNm(param).get("Dept_Nm") + ")");

        return "/board/team-counselor";
    }

    @RequestMapping("getTeamCounselor")
    public ModelAndView getTeamCounselor(@RequestParam Map<String, Object> param, ModelAndView mv) {

        Map<String, Object> queueRealTime = null;
        Map<String, Object> agentStatus = null;
        List<Map<String, Object>> userRealTime = null;

        if (IS_TEST) {
            queueRealTime = boardService.getTeamCounselorInfo();
            agentStatus = boardService.getTeamCounselorInfo2();
            userRealTime = boardService.getIndividualCounselorState();
        } else {
            Map<String, Object> queueCumulativeRate = boardService.getQueueCumulativeRate(param).size() == 0 ? null : boardService.getQueueCumulativeRate(param).get(0);
            queueRealTime = boardService.getQueueRealTime(param);

            if (queueRealTime != null) {
                if (queueCumulativeRate != null) {
                    queueRealTime.putAll(queueCumulativeRate);
                }
            }

            agentStatus = boardService.getAgentStatus(param);

            userRealTime = boardService.getUserRealTime(param);
        }

        mv.setViewName("jsonView");
        mv.addObject("data1", queueRealTime);
        mv.addObject("data2", agentStatus);
        mv.addObject("data3", userRealTime);

        return mv;
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
