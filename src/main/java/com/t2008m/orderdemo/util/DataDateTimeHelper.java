package com.t2008m.orderdemo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataDateTimeHelper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static LocalDateTime convertStringLocalDateTime(String date){
        return LocalDate.parse(date,formatter).atStartOfDay();
    }
    public static String convertStringLocalDateTime(LocalDateTime date){
        return date.format(formatter);
    }

}
