package com.yc.distribution.base.utils;

import java.util.Random;

/**
 * Created by whh on 2018/02/25.
 */
public class GenerateIdUtil {

    private static final Random random = new Random();
    private static final byte[] LOCK = new byte[0];

    public static final String generateId() {
        synchronized (LOCK) {
            long time = System.currentTimeMillis();
            String rand = String.valueOf(1000 + random.nextInt(9000));
            return "GZ"+time+rand;
        }
    }

    public static boolean isLetter(String str) {
        String regex = "^[a-zA-Z]{1}$";
        if (null != str) {
            return str.matches(regex);
        } else {
            return false;
        }
    }

}
