package top.pengcheng789.addressapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author pen
 */
public class DateUtil {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * 返回一个具有良好格式的日期字符串。
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }

        return DATE_FORMATTER.format(date);
    }

    /**
     * 将字符串解析成LocalDate对象。
     */
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检查一个字符串是否为有效的日期。
     */
    public static boolean validDate(String dateString) {
        return DateUtil.parse(dateString) != null;
    }
}
