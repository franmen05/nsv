/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nsv.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author you_k
 */
public class DateUtil {

//    public static final String DATE_TIME_FORMAT = "MM/dd/yyyy";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd";

    public static Instant getInstant(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        var zonedDateTime = LocalDate.parse(date, formatter);
        return zonedDateTime.atStartOfDay().atZone(ZoneId.of("UTC")).toInstant();
    }
    
    
    
}
