package com.example.remindme.shared.utils;

import java.util.Date;

public class DateUtil {
  public static Date addMillisecondsToNow(int timeInMs) {
    return new Date(new Date().getTime() + timeInMs);
  }
}