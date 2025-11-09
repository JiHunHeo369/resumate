package com.boilerplate.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    // 기본 날짜 형식 (e.g. "2025-08-30")
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // 기본 일시 형식 (e.g. "2025-08-30 15:45:00")
    private static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * yyyy-MM-dd 형식의 문자열을 LocalDate로 파싱합니다.
     * @param dateString 날짜 문자열
     * @return 파싱된 LocalDate
     * @throws DateTimeParseException 포맷 불일치 시 예외 발생
     */
    public static LocalDate parseToLocalDate(String dateString) {
        return LocalDate.parse(dateString, DATE_FORMATTER);
    }

    public static LocalDateTime toDateTime(LocalDate date, LocalTime time) {
        if (date == null || time == null) {
            return null;
        }
        return date.atTime(time);
    }

    /**
     * yyyy-mm-dd 형식을 LocalDateTime으로 파싱합니다.
     * @param dateString 일시 문자열
     * @param isStartDay 시작 일 인지 확인
     * @return 파싱된 LocalDateTime
     */
    public static LocalDateTime parseToLocalDateTime(
            String dateString, Boolean isStartDay
    ) {
        if (!StringUtils.hasText(dateString) || isStartDay == null) {
            return null;
        }
        if (isStartDay) {
            return toStartOfDay(dateString);
        } else {
            return toEndOfDay(dateString);
        }
    }


    /**
     * yyyy-MM-dd 형식의 문자열을 해당일 00:00:00의 LocalDateTime으로 변환합니다.
     * @param dateString 날짜 문자열
     * @return 시작 시각 LocalDateTime
     */
    public static LocalDateTime toStartOfDay(String dateString) {
        LocalDate date = parseToLocalDate(dateString);
        return date.atStartOfDay();
    }

    /**
     * yyyy-MM-dd 형식의 문자열을 해당일 23:59:59.999999999의 LocalDateTime으로 변환합니다.
     * @param dateString 날짜 문자열
     * @return 종료 시각 LocalDateTime
     */
    public static LocalDateTime toEndOfDay(String dateString) {
        LocalDate date = parseToLocalDate(dateString);
        return date.atTime(LocalTime.MAX);
    }
}