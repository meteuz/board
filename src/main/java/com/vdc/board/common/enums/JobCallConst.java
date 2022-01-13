package com.vdc.board.common.enums;

import lombok.Getter;

@Getter
public enum JobCallConst {

    TITLE("구 분", "title"),
    RECEIVED("총인입", "Received"),
    ANSWERED("응 대", "Answered"),
    ABANDONED("포 기", "Abandoned"),
    ANSWER_RATE("응대율", "AnswerRate"),
    SERVICE_LEVEL("서비스레벨", "ServiceLevel");

    private final String colTitle;
    private final String key;

    JobCallConst(String colTitle, String key) {
        this.colTitle = colTitle;
        this.key = key;
    }
}
