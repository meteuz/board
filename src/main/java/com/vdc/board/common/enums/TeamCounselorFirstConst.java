package com.vdc.board.common.enums;

import lombok.Getter;

@Getter
public enum TeamCounselorFirstConst {

    RECEIVED("인 입", "Received"),
    ANSWERED("응 대", "Answered"),
    ANSWER_RATE("응 대 율", "AnswerRate"),
    WAIT_CALLS("대 기 호", "WaitCalls"),
    AVERAGE_ANSWERED_WAIT_TIME("평균대기시간", "AverageAnsweredWaitTime"),
    SERVICE_LEVEL("서비스레벨", "ServiceLevel");

    private final String title;
    private final String key;

    TeamCounselorFirstConst(String title, String key) {
        this.title = title;
        this.key = key;
    }
}
