package com.rocketball.java;

import javax.annotation.concurrent.Immutable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * One possible (and common) use is when you have some object that is not thread-safe, but you want to avoid synchronizing access to that object
 *
 */
public class threadlocal {
    private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd HHmm");
        }
    };

    public String formatIt(Date date) {
        return formatter.get().format(date);
    }

    public static void main(String[] args) {
        threadlocal tl = new threadlocal();
    }
}
