package com.vdc.board.common.util;

import java.util.Calendar;

public class Utils {

    public static String getCurrentDate() {
        String[] arrDayStr = {"일", "월", "화", "수", "목", "금", "토"};

        Calendar cal = Calendar.getInstance();
        String year = cal.get(Calendar.YEAR) + "년";
        String month = getZeroPlusString(cal.get(Calendar.MONTH) + 1) + "월";
        String dayOfMonth = getZeroPlusString(cal.get(Calendar.DAY_OF_MONTH)) + "일";
        String dayOfWeek = "[" + arrDayStr[cal.get(Calendar.DAY_OF_WEEK) - 1] + "]";

        return year + " " + month + " " + dayOfMonth + " " + dayOfWeek;
    }

    public static String getCurrentTime() {
        String[] arrAmPm = {"오전", "오후"};
        Calendar cal = Calendar.getInstance();
        return arrAmPm[cal.get(Calendar.AM_PM)] + " " + getZeroPlusString(cal.get(Calendar.HOUR)) + ":" + getZeroPlusString(cal.get(Calendar.MINUTE));
    }

    public static String getZeroPlusString(int val) {
        return val < 10 ? "0" + val : "" + val;
    }

}
