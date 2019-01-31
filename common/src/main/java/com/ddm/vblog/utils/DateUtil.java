package com.ddm.vblog.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
    private final static String sdfYear = new String("yyyy");

    private final static String sdfDay = new String("yyyy-MM-dd");

    private final static String sdfDays = new String("yyyyMMdd");

    private final static String sdfTime = new String("yyyy-MM-dd HH:mm:ss");

    private final static String sdfMonth = new String("yyyy-MM");

    final static String sdfTimes = new String("yyyyMMddHHmmss");

    public static final String ENG_DATE_FROMAT = "EEE, d MMM yyyy HH:mm:ss z";

    public static final String YYYY_MM_DD_HH_MM_SS_S = "yyyy-MM-dd HH:mm:ss.S";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String YYYY = "yyyy";
    public static final String MM = "MM";
    public static final String DD = "dd";

    public static final String YYYY_MM_DD_CN = "yyyy年MM月dd";
    public static final String YYYY_MM_DD_HH_MM_SS_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    public static final String YYYY_MM_DD_HH_MM_SS_S_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    /**
     * 锁对象
     */
    private static final Object lockObj = new Object();

    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     *
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                    System.out.println("put new sdf of pattern " + pattern + " to map");

                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new
                    // SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>() {

                        @Override
                        protected SimpleDateFormat initialValue() {
                            System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }

        return tl.get();
    }

    public static String getNow() {
        return format(new Date());
    }

    public static Date format(String strDate) {
        return format(strDate, getDefaultPattern());
    }

    public static Date format(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String format(Date date) {
        return format(date, getDefaultPattern());
    }

    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    public static String getDefaultPattern() {
        return YYYY_MM_DD_HH_MM_SS;
    }

    /**
     * 本月第一天 yyyy-MM-dd
     */
    public static String firstDayOfCurrentMonth() {

        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.set(Calendar.DAY_OF_MONTH, 1); // 设置为1号,当前日期既为本月第一天
        return getSdf(sdfDay).format(cal_1.getTime());
    }

    /**
     * 本月最后一天 yyyy-MM-dd
     */
    public static String lastDayOfCurrentMonth() {

        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.set(Calendar.DAY_OF_MONTH, cal_1.getActualMaximum(Calendar.DAY_OF_MONTH)); // 设置为1号,当前日期既为本月第一天
        return getSdf(sdfDay).format(cal_1.getTime());
    }

    /**
     * 当前前一个月 yyyy-MM
     */
    public static String previousMonth() {

        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        // cal_1.set(Calendar.DAY_OF_MONTH,1); //设置为1号,当前日期既为本月第一天
        return getSdf(sdfMonth).format(cal_1.getTime());
    }

    /**
     * 当前月前(负数) 后(正数)几个月 第一天 yyyy-MM-01
     */
    public static String getAfterMonthDayFirstDate(int month) {
        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.add(Calendar.MONTH, month);
        cal_1.set(Calendar.DAY_OF_MONTH, 1); // 设置为1号,当前日期既为本月第一天
        return getSdf(sdfDay).format(cal_1.getTime());
    }

    /**
     * 当前月前(负数) 后(正数)几个月 某一天 yyyy-MM-01
     */
    public static String getAfterMonthDayForDay(int month, int day) {
        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.add(Calendar.MONTH, month);
        cal_1.set(Calendar.DAY_OF_MONTH, day); // 设置为1号,当前日期既为本月第一天
        return getSdf(sdfDay).format(cal_1.getTime());
    }

    /**
     * 当前月前(负数) 后(正数)几个月 最后一天 yyyy-MM-01
     */
    public static String getAfterMonthDayLastDate(int month) {
        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.add(Calendar.MONTH, month + 1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1); // 设置为1号,当前日期既为本月第一天
        cal_1.add(Calendar.MONTH, -1);
        return getSdf(sdfDay).format(cal_1.getTime());
    }

    /**
     * 当前月前(负数) 后(正数)几个月 最后一天 yyyy-MM-01
     */
    public static String getAfterMonthDayLastDate(Date refDate, int month) {
        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.setTime(refDate);
        cal_1.add(Calendar.MONTH, month + 1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1); // 设置为1号,当前日期既为本月第一天
        cal_1.add(Calendar.DATE, -1);
        return getSdf(sdfDay).format(cal_1.getTime());
    }

    /**
     * 当前月前(负数) 后(正数)几个月 yyyy-MM-dd
     */
    public static String getAfterMonthDayDate(int month) {
        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.add(Calendar.MONTH, month);
        return getSdf(sdfDay).format(cal_1.getTime());
    }

    /**
     * 当前日期(负数) 后(正数)几天
     */
    public static String getAfterDate(Date date, int day) {
        Calendar cal_1 = Calendar.getInstance();
        cal_1.setTime(date);
        cal_1.add(Calendar.DATE, day);
        String timeString = getSdf(sdfTime).format(cal_1.getTime());
        return timeString;
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return getSdf(sdfYear).format(new Date());
    }

    /**
     * 获取YYYYMM格式
     *
     * @return
     */
    public static String getMonth() {
        return getSdf(sdfMonth).format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getToday() {
        return getSdf(sdfDay).format(new Date());
    }

    public static String dateToday(Date date) {
        return getSdf(sdfDay).format(date);
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays() {
        return getSdf(sdfDays).format(new Date());
    }

    /**
     * 获取YYYY-MM-DD hh:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return getSdf(sdfTime).format(new Date());
    }

    /**
     * 获取YYYY-MM-DD hh:mm:ss格式
     *
     * @return
     */
    public static String getISOTimeAfterMinutes(Date date, int minutes) {

        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.setTime(date);
        cal_1.add(Calendar.MINUTE, minutes);
        String timeString = getSdf(sdfTime).format(cal_1.getTime());
        return timeString.split(" ")[0] + "T" + timeString.split(" ")[1];

    }

    /**
     * @param s
     * @param e
     * @return boolean
     * @throws @author luguosui
     * @Title: compareDate
     * @Description: TODO(日期比较 ， 如果s > = e 返回true 否则返回false)
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return fomatDate(s).getTime() >= fomatDate(e).getTime();
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date fomatDate(String date, String pattern) {
        try {
            if (Stringer.isNullOrEmpty(pattern)) {
                pattern = "yyyy-MM-dd";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24))
                    / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        // System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);
        return getAfterDayDate(null, daysInt);
    }

    /**
     * @param dateStr   日期字符串 yyyy-MM-dd
     * @param afterDays 之后某天
     * @return yyyy-MM-dd
     * @Description: 一定日期前后的某一天
     * @author: Storydo
     * @date: 2018年10月16日 下午2:06:49
     */
    public static String getAfterDayDate(String dateStr, int afterDays) {
        int daysInt = afterDays;

        Calendar canlendar = Calendar.getInstance(); // java.util包

        if (dateStr != null) {
            Date date = fomatDate(dateStr, YYYY_MM_DD);
            canlendar.setTime(date);
        }

        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        return getSdf(sdfDay).format(date);
    }

    public static String getAfterDayDate(int days) {
        return getAfterDayDate(null, days);
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayMonth(String date, Integer month) {
        Calendar cal_1 = Calendar.getInstance();
        cal_1.setTime(DateUtil.fomatDate(date));
        cal_1.add(Calendar.MONTH, 5);
        String format = getSdf(sdfDay).format(cal_1.getTime());

        return format;
    }

    /**
     * 得到两个时间相差的天数
     *
     * @param date1 小时间
     * @param date2 大时间
     * @return
     */
    public static long getDiffDay(Date date1, Date date2) {
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        if (time1 < time2) {
            throw new DateTimeException("第一个参数时间必须大于第二个参数时间");
        }
        long diff = time1 - time2;
        return diff / (1000 * 60 * 60 * 24);
    }

    /**
     * 得到某个事件减去x天后的日期
     *
     * @param someTime 时间
     * @param num      天数
     * @return
     */
    public static String getBeforeDay(Date someTime, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(someTime);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - num);
        String resultTime = getSdf(sdfDay).format(calendar.getTime());
        return resultTime;
    }

    /**
     * 获取传入时间是周几
     *
     * @param someDay
     * @return
     */
    public static Integer getSomeWeek(Date someDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(someDay);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static void main(String[] args) {
        String time = getBeforeDay(new Date(), 5);
        System.err.println(time);
    }

}
