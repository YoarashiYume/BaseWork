package com.fitness.utility;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;

@UtilityClass
public class SupportClass {

    public boolean isNumeric(String v) {
        if (StringUtils.isNumeric(v) && !v.isEmpty()) {
            double d = Double.parseDouble(v);
            int i = (int) d;
            return (d - i) == 0;
        }
        return false;
    }

    public boolean isSomeAlphaCascade(String... args) {
        return Arrays.stream(args).anyMatch(SupportClass::isSomeAlpha);
    }

    public boolean isSomeAlpha(String v) {
        return ((!StringUtils.isNumeric(v) || !isDouble(v)) && !v.isEmpty());
    }

    //parse or null
    public Long pOnL(String v) {
        return isNumeric(v) ? Long.parseLong(v) : null;
    }

    public Double pOnD(String v) {
        return isDouble(v) ? Double.parseDouble(v) : null;
    }

    public boolean isDouble(String v) {
        return NumberUtils.isCreatable(v);
    }

    /*static public boolean eqWnull(String obj1, String obj2) {
        if (obj1==obj2) return true;
        if (obj1==null || obj2==null) return false;
        return obj1.equals(obj2);
    }*/

    // equals with null, using getExisted
    public boolean eqWnullEx(String obj1, String obj2) {
        if (obj1 == null && (obj2 == null || obj2.isEmpty())) return true;
        if (obj1 == null) return false;
        return obj1.equals(getExisted(obj1, obj2));
    }

    public boolean eqWnullEx(Double obj1, String obj2) {
        if (obj1 == null && (obj2 == null || obj2.isEmpty())) { return true; }
        if (obj1 == null) { return false; }
        return obj1.equals(getExisted(obj1, obj2));
    }

    public boolean eqWnullEx(Long obj1, String obj2) {
        if (obj1 == null && (obj2 == null || obj2.isEmpty())) { return true; }
        if (obj1 == null) { return false; }
        return obj1.equals(getExisted(obj1, obj2));
    }

    public Long getExisted(Long v1, String v2) {
        if (isSomeAlpha(v2) || !isNumeric(v2)) {
            return v1;
        }
        return Long.parseLong(v2, 10);
    }

    public Double getExisted(Double v1, String v2) {
        if (isSomeAlpha(v2) || !isNumeric(v2)) {
            return v1;
        }
        return Double.parseDouble(v2);
    }

    public String getExisted(String v1, String v2) {
        return v2 == null || v2.isEmpty() ? v1 : v2;
    }
}
