package com.nandtotetris.assembler.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isNull(Object str){
        return str==null;
    }

    public static boolean isNotNull(Object str){
        return !isNull(str);
    }

    public static String getFirtMatch(String regex, String str){
        if(isNull(str)) return null;
        Matcher matcher = getMath(regex,str);
        if(matcher.find()){
            return matcher.group(1);
        }
        return null;
    }

    public static Matcher getMath(String regex, String str){
        if(isNull(str)) return null;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }



    public static boolean isEmpty(String str){
        return isNull(str) || str.length()==0;
    }

    public static boolean isNotEmpty(String str){
        return isNotNull(str) && str.length()>0;
    }
}
