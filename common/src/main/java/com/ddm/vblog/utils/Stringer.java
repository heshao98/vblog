
package com.ddm.vblog.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <strong>Title : Strings </strong>. <br>
 * <strong>Description : 字符串处理工具类.</strong> <br>
 * <strong>Create on : May 20, 2015 5:32:38 PM </strong>. <br>
 * <p>
 * <strong>Copyright (C) AlexHo Co.,Ltd.</strong> <br>
 * </p>
 *
 * @author k2 <br>
 * @version <strong>base-platform-0.1.0</strong> <br>
 * <br>
 * <strong>修改历史: .</strong> <br>
 * 修改人 修改日期 修改描述<br>
 * -------------------------------------------<br>
 * <br>
 * <br>
 */
public class Stringer {


    public static final String SPECIAL_CHAR = "[\\\\`~!@#$%^&*+=|{}',:;\"[url=file://\\[\\].<]\\[\\].<>/[/url]?～！＠＃￥％……＆×（）——＋｜｛｝【】［］‘；：＂。，、．＜＞／？]";

    /**
     * @param string
     * @return
     * @author by K2 Aug 21, 2015
     * @desc 判断某字符串是否为空，如果为空，则返回一个空串.
     */
    public static String nullToEmpty(String string) {
        return isNullOrEmpty(string) ? "" : string;
    }

    /**
     * @param string
     * @return
     * @author by K2 Aug 21, 2015
     * @desc 判断某字符串是否为空，如果为空，则返回一个null空对象.
     */
    public static String emptyToNull(String string) {
        return isNullOrEmpty(string) ? null : string;
    }

    /**
     * @param obj
     * @return
     * @author by K2 Aug 21, 2015
     * @desc 判断某对象(String, Collection, Map, obj)是否为空.
     */
    public static boolean isNullOrEmpty(Object obj) {

        boolean result = true;
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            result = (obj.toString().trim().length() == 0) || obj.toString().trim().equals("null");
        } else if (obj instanceof Collection) {
            result = ((Collection<?>) obj).size() == 0;
        } else if (obj instanceof Map) {
            result = ((Map<?, ?>) obj).isEmpty();
        } else {
            result = ((obj == null) || (obj.toString().trim().length() < 1)) ? true : false;
        }
        return result;
    }

    /**
     * @param in
     * @return 输出二进制数据
     * @throws IOException
     * @author by K2 Aug 21, 2015
     * @desc 处理读取InputStream数据流.
     */
    public static byte[] readInStream(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        out.close();
        in.close();
        return out.toByteArray();
    }

    /**
     * @param is
     * @return
     * @throws IOException
     * @author by K2 Aug 21, 2015
     * @desc InputStream数据流转换成字符串.
     */
    public static String toInStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }

    /**
     * @param sInputString
     * @return
     * @author by K2 Aug 21, 2015
     * @desc 将字符串转成InputStream流.
     */
    public static InputStream toInStream(String sInputString) {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
        }
        return tInputStringStream;
    }

    /**
     * @param string
     * @return
     * @author by K2 Aug 18, 2015
     * @desc 小数点数据字符串转成保留两位小数的float数据类型.
     */
    public static float toFloat(String string) {
        float f = Float.parseFloat(string);
        BigDecimal b = new BigDecimal(f);
        float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return f1;
    }

    /**
     * @param f
     * @return
     * @author by K2 Aug 18, 2015
     * @desc 浮点型数据转换成两位小数点的字符串.
     */
    public static String toFloat(float f) {
        DecimalFormat decimalFormat = new DecimalFormat(".00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p = decimalFormat.format(f);// format 返回的是字符串
        return p;
    }

    /**
     * @param string
     * @return
     * @author by K2 Aug 19, 2015
     * @desc 双精度数据转换成两位小数点的字符串.
     */
    public static Double toDouble(String string) {
        float f = Float.parseFloat(string);
        BigDecimal b = new BigDecimal(f);
        Double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    /**
     * @param dd
     * @return
     * @author by K2 Aug 19, 2015
     * @desc 双精度数据转换成两位小数点的字符串.
     */
    public static String toDouble(Double dd) {
        return String.format("%.2f", dd);
    }

    /**
     * @param srcString 原字符串
     * @param regEx     替换的正则表达式，如果为空则使用默认的正则表达式，变量=SPECIAL_CHAR
     * @return 如果有返回true
     * @author by K2 Aug 21, 2015
     * @desc 检查指定字符串中是否包含特殊字符
     */
    public static boolean existSpecialChar(String srcString, String regEx) {
        if (isNullOrEmpty(regEx)) {
            regEx = SPECIAL_CHAR;
        }
        Matcher m = Pattern.compile(regEx).matcher(srcString);
        return m.find();
    }

    /**
     * @Title: nullToEmptyString @Description: TODO @param @param
     * o @param @return @return String @auther yaoyinchu @throws
     */
    public static String nullOrEmptyToString(Object o) {
        if (Stringer.isNullOrEmpty(o)) {
            return "";
        } else {
            return o.toString();
        }
    }

    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * 下划线转驼峰法(默认小驼峰)
     *
     * @param line       源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰(驼峰，第一个字符是大写还是小写)
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line, boolean... smallCamel) {
        if (Stringer.isNullOrEmpty(line)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher = pattern.matcher(line);
        //匹配正则表达式
        while (matcher.find()) {
            String word = matcher.group();
            //当是true 或则是空的情况
            if ((smallCamel.length == 0 || smallCamel[0]) && matcher.start() == 0) {
                sb.append(Character.toLowerCase(word.charAt(0)));
            } else {
                sb.append(Character.toUpperCase(word.charAt(0)));
            }

            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰法转下划线
     *
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line) {
        if (Stringer.isNullOrEmpty(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase()
                .concat(line.substring(1));
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString();
    }

}
