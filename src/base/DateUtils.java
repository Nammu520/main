package base;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class DateUtils {


    /**
     * 隐藏构造器
     */
    private DateUtils() {

    }

    public static final TimeZone ZONE_GMT_8 = TimeZone.getTimeZone("ETC/GMT-8");
    public static final TimeZone ZONE_GMT = TimeZone.getTimeZone("GMT");
    // 东八区
    public static final String TIME_ZONE_SYSTEM = "Asia/Shanghai";
    public static final TimeZone ZONE_SHANGHAI = TimeZone.getTimeZone("ETC/GMT+8");
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String MONTH_PATTERN_SPECIAL = "yyyy_MM";
    public static final String MONTH_PATTERN_SPECIAL2 = "yyyyMM";
    public static final String YEAR_MONTH_DAY_HOUR_PATTERN = "yyMMddHH";
    public static final String YEAR_MONTH_DAY_PATTERN = "yyyyMMdd";
    public static final String YEAR_WEEK_PATTERN = "yyyyww";
    public static final String MONTH_DAY_PATTERN = "MMdd";
    public static final String YEAR_PATTERN = "yyyy";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final String FORMAT_DATETIME_WITH_MINL = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String FORMAT_DATETIME_WITH_NUMBER = "yyyyMMddHH";
    public static final String HOUR_PATTERN_UTC_DEFAULT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String HOUR_PATTERN_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String HOUR_PATTERN_MS_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String HOUR_PATTERN_8601 = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String CRON_PATTERN = "ss mm HH dd MM ? yyyy";


    /**
     * 计算两个日期的日期差
     *
     * @param dateOne dateOne
     * @param dateTwo dateTwo
     * @return long
     */
    public static long getBetweenDays(Date dateOne, Date dateTwo) {
        long from = dayBegin(dateOne).getTime();
        long to = dayBegin(dateTwo).getTime();
        long days = (to - from) / (24 * 3600 * 1000);

        return days;
    }

    /**
     * 计算两个时间的小时差
     *
     * @param dateOne dateOne
     * @param dateTwo dateTwo
     * @return long
     * @throws
     * @author WangHan
     * @date 2018/8/22 20:16
     */
    public static long getBetweenHours(Date dateOne, Date dateTwo) {
        long from = dateOne.getTime();
        long to = dateTwo.getTime();
        return (to - from) / (1000 * 60 * 60);
    }

    /**
     * 返回特定日期的零点时间 </br> 2018-01-18 00:00:00
     *
     * @param date date
     * @return Date
     */
    public static Date dayBegin(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        return c.getTime();
    }

    public static Date getGMT8CurrentDate() {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_SYSTEM));
        return c.getTime();
    }

    /**
     * 本月第一天
     *
     * @return Date
     * @throws ParseException ParseException
     */
    public static Date getMonthFirstDay() throws ParseException {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MONTH, 0);
        ca.set(Calendar.DAY_OF_MONTH, 1); //设置为1号,当前日期既为本月第一天
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        return dateParse(sdf.format(ca.getTime()), DATE_PATTERN);
    }

    /**
     * 传入时间的月第一天
     *
     * @param date date
     * @return Date
     * @throws ParseException ParseException
     */
    public static Date getMonthFirstDay(Date date) throws ParseException {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, 0);
        ca.set(Calendar.DAY_OF_MONTH, 1); //设置为1号,当前日期既为本月第一天
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        return dateParse(sdf.format(ca.getTime()), DATE_PATTERN);
    }

    /**
     * 本月最后一天
     *
     * @return Date
     * @throws ParseException ParseException
     */
    public static Date getMonthLastDay() throws ParseException {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

        return dateParse(sdf.format(ca.getTime()), DATE_PATTERN);
    }


    /**
     * 本月最后一天
     *
     * @param date date
     * @return Date
     * @throws ParseException ParseException
     */
    public static Date getMonthLastDay(Date date) throws ParseException {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

        return dateParse(sdf.format(ca.getTime()), DATE_PATTERN);
    }

    /**
     * 日期相加减天数
     *
     * @param date        如果为Null，则为当前时间
     * @param days        加减天数
     * @param includeTime 是否包括时分秒,true表示包含
     * @return Date
     * @throws ParseException ParseException
     */
    public static Date dateAdd(Date date, int days, boolean includeTime) throws ParseException {
        if (date == null) {
            date = new Date();
        }
        if (!includeTime) {
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_PATTERN);
            date = sdf.parse(sdf.format(date));
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 时间格式化成字符串
     *
     * @param date    Date
     * @param pattern StringUtils.DATE_TIME_PATTERN || StringUtils.DATE_PATTERN， 如果为空，则为yyyy-MM-dd
     * @return String
     * @throws ParseException ParseException
     */
    public static String dateFormat(Date date, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = DateUtils.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串解析成时间对象
     *
     * @param dateTimeString String
     * @param pattern        StringUtils.DATE_TIME_PATTERN || StringUtils.DATE_PATTERN，如果为空，则为yyyy-MM-dd
     * @return Date
     * @throws ParseException ParseException
     */
    public static Date dateParse(String dateTimeString, String pattern) throws ParseException {
        if (StringUtils.isEmpty(pattern)) {
            pattern = DateUtils.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateTimeString);
    }

    /**
     * 字符串解析成时间对象
     *
     * @param dateTimeString String
     * @param pattern        StringUtils.DATE_TIME_PATTERN || StringUtils.DATE_PATTERN，如果为空，则为yyyy-MM-dd
     * @return Date
     * @throws ParseException ParseException
     */
    public static Date dateParseOfNull(String dateTimeString, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = DateUtils.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateTimeString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 字符串解析成时间对象
     *
     * @param dateTimeString String
     * @param pattern        StringUtils.DATE_TIME_PATTERN || StringUtils.DATE_PATTERN，如果为空，则为yyyy-MM-dd
     * @param zone           zone
     * @return Date
     * @throws ParseException ParseException
     */
    public static Date dateParse(String dateTimeString, String pattern, TimeZone zone) throws ParseException {
        if (StringUtils.isEmpty(pattern)) {
            pattern = DateUtils.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(zone);
        return sdf.parse(dateTimeString);
    }


    /**
     * 将日期时间格式成只有日期的字符串（可以直接使用dateFormat，Pattern为Null进行格式化）
     *
     * @param dateTime Date
     * @return String
     */
    public static String dateTimeToDateString(Date dateTime) {
        String dateTimeString = DateUtils.dateFormat(dateTime, DateUtils.DATE_TIME_PATTERN);
        return dateTimeString.substring(0, 10);
    }

    /**
     * 当时、分、秒为00:00:00时，将日期时间格式成只有日期的字符串，
     * 当时、分、秒不为00:00:00时，直接返回
     *
     * @param dateTime Date
     * @return String
     */
    public static String dateTimeToDateStringIfTimeEndZero(Date dateTime) {
        String dateTimeString = DateUtils.dateFormat(dateTime, DateUtils.DATE_TIME_PATTERN);
        if (dateTimeString.endsWith("00:00:00")) {
            return dateTimeString.substring(0, 10);
        } else {
            return dateTimeString;
        }
    }

    /**
     * 将日期时间格式成日期对象，和dateParse互用
     *
     * @param dateTime Date
     * @return Date
     */
    public static Date dateTimeToDate(Date dateTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 时间加减小时
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param hours     加减的小时
     * @return Date
     */
    public static Date dateAddHours(Date startDate, int hours) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.HOUR, c.get(Calendar.HOUR) + hours);
        return c.getTime();
    }

    /**
     * 时间加减分钟
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param minutes   加减的分钟
     * @return Date
     */
    public static Date dateAddMinutes(Date startDate, int minutes) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + minutes);
        return c.getTime();
    }

    /**
     * 时间加减秒数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param seconds   加减的秒数
     * @return Date
     */
    public static Date dateAddSeconds(Date startDate, int seconds) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.SECOND, c.get(Calendar.SECOND) + seconds);
        return c.getTime();
    }

    /**
     * 时间加减天数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param days      加减的天数
     * @return Date
     */
    public static Date dateAddDays(Date startDate, int days) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.DATE, c.get(Calendar.DATE) + days);
        return c.getTime();
    }

    /**
     * 时间加减月数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param months    加减的月数
     * @return Date
     */
    public static Date dateAddMonths(Date startDate, int months) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + months);
        return c.getTime();
    }

    /**
     * 时间加减周
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param weeks     加减的周数
     * @return Date
     */
    public static Date dateAddWeeks(Date startDate, int weeks) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.WEEK_OF_YEAR, c.get(Calendar.WEEK_OF_YEAR) + weeks);
        return c.getTime();
    }

    /**
     * 时间加减年数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param years     加减的年数
     * @return Date
     */
    public static Date dateAddYears(Date startDate, int years) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) + years);
        return c.getTime();
    }

    /**
     * 时间比较（如果myDate>compareDate返回1，<返回-1，相等返回0）
     *
     * @param myDate      时间
     * @param compareDate 要比较的时间
     * @return int
     */
    public static int dateCompare(Date myDate, Date compareDate) {
        Calendar myCal = Calendar.getInstance();
        Calendar compareCal = Calendar.getInstance();
        if (myDate == null && compareDate == null) {
            return 0;
        } else if (myDate == null) {
            return -1;
        } else if (compareDate == null) {
            return 1;
        }
        myCal.setTime(myDate);
        compareCal.setTime(compareDate);
        return myCal.compareTo(compareCal);
    }

    /**
     * 获取两个时间中最小的一个时间
     *
     * @param date        date
     * @param compareDate compareDate
     * @return Date
     */
    public static Date dateMin(Date date, Date compareDate) {
        if (date == null) {
            return compareDate;
        }
        if (compareDate == null) {
            return date;
        }
        if (1 == dateCompare(date, compareDate)) {
            return compareDate;
        } else if (-1 == dateCompare(date, compareDate)) {
            return date;
        }
        return date;
    }

    /**
     * 获取两个时间中最大的一个时间
     *
     * @param date        date
     * @param compareDate compareDate
     * @return Date
     */
    public static Date dateMax(Date date, Date compareDate) {
        if (date == null) {
            return compareDate;
        }
        if (compareDate == null) {
            return date;
        }
        if (1 == dateCompare(date, compareDate)) {
            return date;
        } else if (-1 == dateCompare(date, compareDate)) {
            return compareDate;
        }
        return date;
    }

    /**
     * 获取两个日期（不含时分秒）相差的天数，不包含今天
     *
     * @param startDate startDate
     * @param endDate   endDate
     * @return int
     */
    public static int dateBetweenYear(Date startDate, Date endDate) {
        return (int) ((endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24 / 365);
    }

    /**
     * 获取两个日期（不含时分秒）相差的天数，不包含今天
     *
     * @param startDate startDate
     * @param endDate   endDate
     * @return int
     */
    public static int dateBetween(Date startDate, Date endDate) {
        return (int) ((endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24);
    }

    /**
     * @param startDate startDate
     * @param endDate   endDate
     * @return long
     */
    public static long dateBetweenSeconds(Date startDate, Date endDate) {
        return (endDate.getTime() - startDate.getTime()) / 1000;
    }

    /**
     * @param startDate startDate
     * @param endDate   endDate
     * @return long
     */
    public static long dateBetweenHours(Date startDate, Date endDate) {
        return dateBetweenSeconds(startDate, endDate) / 60;
    }


    /**
     * 获取两个日期（不含时分秒）相差的天数，不包含今天
     *
     * @param date      date
     * @param startDate startDate
     * @param endDate   endDate
     * @return boolean
     * @throws ParseException ParseException
     */
    public static boolean isDateBetween(Date date, Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return true;
        }
        return date.getTime() >= startDate.getTime() && date.getTime() <= endDate.getTime();

    }

    /**
     * 获取两个日期（不含时分秒）相差的天数，包含今天
     *
     * @param startDate startDate
     * @param endDate   endDate
     * @return int
     */
    public static int dateBetweenIncludeToday(Date startDate, Date endDate) {
        return dateBetween(startDate, endDate) + 1;
    }

    /**
     * 获取日期时间的年份，如2017-02-13，返回2017
     *
     * @param date date
     * @return int
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取日期时间的月份，如2017年2月13日，返回2
     *
     * @param date date
     * @return int
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期时间的第几天（即返回日期的dd），如2017-02-13，返回13
     *
     * @param date date
     * @return int
     */
    public static int getDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    /**
     * @param date date
     * @return int
     */
    public static int getWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取日期时间当月的总天数，如2017-02-13，返回28
     *
     * @param date date
     * @return int
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 获取日期时间当月的总天数，如2017-02-13，返回28
     *
     * @param date date
     * @return int
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取日期时间当月的总天数，如2017-02-13，返回28
     *
     * @param date date
     * @return int
     */
    public static int getDaysOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取日期时间当年的第几天，如2017-02-13，返回2017年的53
     *
     * @param date date
     * @return int
     */
    public static int getDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取日期时间当年的总天数，如2017-02-13，返回2017年的总天数
     *
     * @param date date
     * @return int
     */
    public static int getDaysOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }


    /**
     * 根据时间获取当月最大的日期
     * <li>2017-02-13，返回2017-02-28</li>
     * <li>2016-02-13，返回2016-02-29</li>
     * <li>2016-01-11，返回2016-01-31</li>
     *
     * @param date Date
     * @return Date
     * @throws Exception Exception
     */
    public static Date maxDateOfMonth(Date date) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DATE);
        return dateParse(dateFormat(date, MONTH_PATTERN) + "-" + value, null);
    }

    /**
     * 根据时间获取当月最小的日期，也就是返回当月的1号日期对象
     *
     * @param date Date
     * @return Date
     * @throws Exception Exception
     */
    public static Date minDateOfMonth(Date date) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMinimum(Calendar.DATE);
        return dateParse(dateFormat(date, MONTH_PATTERN) + "-" + value, null);
    }

    /**
     * 根据时间获取当月最小的日期，也就是返回当月的1号日期对象
     *
     * @param time 整形时间
     * @return Date
     */
    public static Date parse(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal.getTime();
    }

    /**
     * @param time time
     * @return Date
     */
    public static Date parseShort(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time * 1000);
        return cal.getTime();
    }

    /**
     * 是否是同一天
     *
     * @param date1 date1
     * @param date2 date2
     * @return boolean
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     * 是否是同一月
     *
     * @param date1 date1
     * @param date2 date2
     * @return boolean
     */
    public static boolean isSameMonth(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameMonth(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     * @param cal1 cal1
     * @param cal2 cal2
     * @return boolean
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     * @param cal1 cal1
     * @param cal2 cal2
     * @return boolean
     */
    public static boolean isSameMonth(Calendar cal1, Calendar cal2) {
        if (cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(2) == cal2.get(2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    /**
     * @param date date
     * @return Date
     */
    public static Date getDateEndOfTheDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * @param date date
     * @return Date
     */
    public static Date getDateStartOfTheDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * @param startTime startTime
     * @param endTime   endTime
     * @return Long
     */
    public static Long computeSecondDiff(Date startTime, Date endTime) {
        return (endTime.getTime() - startTime.getTime()) / 1000L;
    }

    /**
     * 返回当前日期
     *
     * @return Date
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 返回明日日期
     *
     * @return Date
     */
    public static Date tomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now());
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        return calendar.getTime();
    }

    /**
     * 格式化时间 HH:mm:ss
     *
     * @param date date
     * @return String
     */
    public static String formatTime(Date date) {
        return new SimpleDateFormat(TIME_PATTERN).format(date);
    }

    /**
     * 格式化日期 yyyy-MM-dd HH:mm:ss
     *
     * @param date date
     * @return String
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(DATE_TIME_PATTERN).format(date);
    }

    /**
     * 格式化日期 yyyy-MM-dd
     *
     * @param date date
     * @return String
     */
    public static String formatDate(Date date) {
        return new SimpleDateFormat(DATE_PATTERN).format(date);
    }

    /**
     * 返回特定日期偏移后的日期
     *
     * @param date   date
     * @param field  field
     * @param amount amount
     * @return Date
     */
    public static Date dateOffset(Date date, int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, amount);
        return c.getTime();
    }

    /**
     * @param date date
     * @return Date
     */
    public static Date addHourMinutesSecond(Date date) {
        Calendar c = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
        c.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
        return c.getTime();
    }

    /**
     * 获取10天的时长
     *
     * @return long
     */
    public static long tenDaysTime() {
        return 24 * 3600 * 1000 * 10;
    }

    /**
     * @param now now
     * @return Date
     */
    public static Date setHotTime(Date now) {
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 14);
        return c.getTime();
    }

    /**
     * 是否是晚间，6-18点为晚间
     *
     * @param now now
     * @return boolean
     */
    public static boolean isEvening(Date now) {
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 6);
        Date beginTime = c.getTime();

        c.setTime(now);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 18);
        Date endTime = c.getTime();
        return !isEffectiveDate(now, beginTime, endTime);
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，不包含临界时间
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return boolean
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }

    /**
     * 检查当前日期是否生日
     *
     * @param currentDate currentDate
     * @param birthday    birthday
     * @return Boolean
     */
    public static Boolean checkBirthday(Date currentDate, Date birthday) {
        if (birthday != null && currentDate != null) {
            return getMonth(currentDate) == getMonth(birthday)
                    && getDate(currentDate) == getDate(birthday);
        } else {
            return false;
        }
    }


    /**
     * 时间格式转化
     *
     * @param date    Date
     * @param pattern StringUtils.DATE_TIME_PATTERN || StringUtils.DATE_PATTERN， 如果为空，则为yyyy-MM-dd
     * @return String
     * @throws ParseException ParseException
     */
    public static Date format(Date date, String pattern) throws ParseException {
        if (StringUtils.isEmpty(pattern)) {
            pattern = DateUtils.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return dateParse(sdf.format(date), pattern);
    }

    /**
     * 时间格式转化
     *
     * @param date    Date
     * @param pattern StringUtils.DATE_TIME_PATTERN || StringUtils.DATE_PATTERN， 如果为空，则为yyyy-MM-dd
     * @return String
     * @throws ParseException ParseException
     */
    public static String formatDate(Date date, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = DateUtils.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 时间格式转化
     *
     * @param date    Date
     * @param pattern StringUtils.DATE_TIME_PATTERN || StringUtils.DATE_PATTERN， 如果为空，则为yyyy-MM-dd
     * @return String
     * @throws ParseException ParseException
     */
    public static String formatDateZone(Date date, String pattern, String timeZone) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = DateUtils.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        return sdf.format(date);
    }


    /**
     * 转成gmt时间
     *
     * @param date 时间
     * @return 格式化数据
     */
    public static String getGMT(Date date) {
        TimeZone tz = DateUtils.ZONE_GMT;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(tz);
        return sdf.format(date);
    }

    /**
     * 转成utc时间
     *
     * @param date 时间
     * @return 格式化数据
     */
    public static String toISO8601UTC(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat(HOUR_PATTERN_8601);
        df.setTimeZone(tz);
        return df.format(date);
    }

    /**
     * 转成格式化时间
     *
     * @param dateStr 格式化数据
     * @return 时间
     */
    public static Date fromISO8601UTC(String dateStr) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat(HOUR_PATTERN_8601);
        df.setTimeZone(tz);

        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws ParseException {

        Date date = DateUtils.dateParse("2019-05-22T11:23:45+08:00", DateUtils.HOUR_PATTERN_8601);
        System.out.println(date);

    }

    /**
     * 获取当月最后时刻
     *
     * @param date 当月的任意一天
     * @return Date
     * @throws ParseException 时间序列号异常
     */
    public static Date getMonthLast(Date date) throws ParseException {
        Calendar call = Calendar.getInstance();
        call.setTime(date);
        int maxDays = call.getActualMaximum(Calendar.DAY_OF_MONTH);
        call.set(Calendar.DAY_OF_MONTH, maxDays);
        return getDateEndOfTheDate(call.getTime());
    }

    /**
     * 获取当月第一天的零时刻
     *
     * @param date 当月的任意一天
     * @return Date
     */
    public static Date getMonthBeginZero(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return getDateStartOfTheDate(cale.getTime());
    }

}