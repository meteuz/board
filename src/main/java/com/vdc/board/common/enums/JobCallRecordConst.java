package com.vdc.board.common.enums;

public enum JobCallRecordConst {

    TOTAL("전 체", "total"),
    WORK("워크넷", "work"),
    INSURANCE("고용보험", "insurance"),
    SKILL("능력개발", "skill"),
    FOREIGNER("외국인고용", "foreigner"),
    ETC("기타문의", "etc");

    private final String recTitle;
    private final String key;

    JobCallRecordConst(String recTitle, String key) {
        this.recTitle = recTitle;
        this.key = key;
    }
}
