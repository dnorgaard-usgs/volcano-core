/**
 * I waive copyright and related rights in the this work worldwide through the CC0 1.0
 * Universal public domain dedication.
 * https://creativecommons.org/publicdomain/zero/1.0/legalcode
 */

package gov.usgs.volcanoes.core.time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * A utility class for dealing with time, especially formatting and J2Ks.
 *
 * @author Tom Parker
 * @author Dan Cervelli
 */
public final class Time {
  // public static final String ISO_8601_TIME_FORMAT = "yyyyMMdd'T'HHmmss.SSSS'Z'";
  public static final String FDSN_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSS";
  private static Map<String, SimpleDateFormat> formats;
  public static final String INPUT_TIME_FORMAT = "yyyyMMddHHmmss";
  public static final double SECONDSPERYEAR = 31557600;

  public static final String STANDARD_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

  public static final String STANDARD_TIME_FORMAT_MS = "yyyy-MM-dd HH:mm:ss.SSS";

  static {
    formats = new HashMap<String, SimpleDateFormat>();
  }


  /**
   * Formats {@link Date} by format name.
   *
   * @param format Name of format to search
   * @param date date to format
   * @return Formatted date as string
   */
  public static synchronized String format(String format, Date date) {
    return getFormat(format).format(date);
  }


  /**
   * Formats long date by format name.
   *
   * @param format Name of format to search
   * @param time long date to format
   * @return Formatted date as string
   */
  public static synchronized String format(String format, long time) {
    return format(format, new Date(time));
  }

  /**
   * Searches in internal formats list.
   *
   * @param format Format name to search
   * @return Found SimpleDateFormat or, if absent, initialized with fs string one
   */
  protected static SimpleDateFormat getFormat(String format) {
    SimpleDateFormat sdFormat = formats.get(format);
    if (sdFormat == null) {
      sdFormat = new SimpleDateFormat(format);
      sdFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
      formats.put(format, sdFormat);
    }
    return sdFormat;
  }

  /**
   * Formats {@link Date} as "yyyy-MM-dd HH:mm:ss".
   *
   * @param date date
   * @return formatted date
   */
  public static String toDateString(Date date) {
    return format(STANDARD_TIME_FORMAT, date);
  }

  /**
   * Formats long date as "yyyy-MM-dd HH:mm:ss".
   *
   * @param time Date
   * @return formatted date
   */
  public static String toDateString(long time) {
    return format(STANDARD_TIME_FORMAT, time);
  }

  /**
   * Formats {@link Date} as "yyyyMMddHHmmss".
   *
   * @param date date
   * @return formatted date
   */
  public static String toShortString(Date date) {
    return format(INPUT_TIME_FORMAT, date);
  }

  /**
   * Uninstantiatable.
   */
  private Time() {}

}