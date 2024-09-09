package com.dd.thuctd.dateAPI;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class DateTimeApi {

    public static void main(String[] args) {
        // dai dien cho 1 ngay
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.toString());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.isLeapYear());

        // dai dien cho mot thoi gian
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.toString());
        System.out.println(localTime.getHour());
        System.out.println(localTime.getMinute());
        System.out.println(localTime.getSecond());

        // dai dien cho ngay-gio theo mui gio hien tai
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toString());
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getHour());
        System.out.println(localDateTime.getNano());

        // Date Adjusters: don gian hoa nhieu tac vu xu ly ngay thang pho bien
        LocalDate date = LocalDate.of(2024, Month.JANUARY, 9);
        LocalDate endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(endOfMonth.toString());

        // Fluent API:
        LocalDateTime date2 = Year.of(2024)
                .atMonth(Month.SEPTEMBER)
                .atDay(9)
                .atTime(12, 11);

        System.out.println(date2.toString());

        // Format a Date:
        // DateTimeFormatterBuilder: tao cac mau dinh dang ngay thang tuy chinh theo cach linh hoat
        // DateTimeFormatter: cung cap dinh dang ngay thang chuan hoac tuy chinh theo ngay gio
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        System.out.println(formatter.format(ZonedDateTime.now()));
    }

}
