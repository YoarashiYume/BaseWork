package com.fitness.controller.supportfunc;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class SupportClass {
    static public boolean isNumeric(String v)
    {
        return (StringUtils.isNumeric(v) && !v.isEmpty());
    }
    static public boolean isSomeAlphaCascade(String... args)
    {
        return Arrays.stream(args).anyMatch(SupportClass::isSomeAlpha);
    }
    static public boolean isSomeAlpha( String v)
    {
        return ((!StringUtils.isNumeric(v)||!isDouble(v)) && !v.isEmpty());
    }
    static public Long pOnL(String v)//parse or null
    {
        return isNumeric(v) ? Long.parseLong(v) : null;
    }
    static public Double pOnD(String v)
    {
        return isDouble(v) ? Double.parseDouble(v) : null;
    }
    static public boolean isDouble(String v)
    {
        return NumberUtils.isCreatable(v);
    }
    /*static public boolean eqWnull(String obj1, String obj2)
    {
        if (obj1==obj2) return true;
        if (obj1==null || obj2==null) return false;
        return obj1.equals(obj2);
    }*/
    static public boolean eqWnullEx(String obj1, String obj2)//equals with null, using getExisted
    {
        if (obj1==null && (obj2==null || obj2.isEmpty())) return true;
        if (obj1 == null) return false;
        return obj1.equals(getExisted(obj1,obj2));
    }
    static public boolean eqWnullEx(Double obj1, String obj2)
    {
        if (obj1==null && (obj2==null || obj2.isEmpty())) return true;
        if (obj1 == null) return false;
        return obj1.equals(getExisted(obj1,obj2));
    }
    static public boolean eqWnullEx(Long obj1, String obj2)
    {
        if (obj1==null && (obj2==null || obj2.isEmpty())) return true;
        if (obj1 == null) return false;
        return obj1.equals(getExisted(obj1,obj2));
    }
    static public Long getExisted(Long v1, String v2)
    {
        if (isSomeAlpha(v2) || !isNumeric(v2))
            return v1;
        return Long.parseLong(v2,10);
    }
    static public Double getExisted(Double v1,String v2)
    {
        if (isSomeAlpha(v2) || !isNumeric(v2))
            return v1;
        return Double.parseDouble(v2);
    }
    static public String getExisted(String v1,String v2)
    {
        return v2==null || v2.isEmpty()  ? v1 : v2;
    }
}
