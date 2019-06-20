package net.asgardsolutions.dollgirl.serverdb.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class TimeUtilities {

	public static String convert(Date d) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		String dateFormatted = fmt.format(d);
		return dateFormatted;
	}

	public static String[] convert(String date) {
		String[] dateAndTime = new String[2];
		String[] dt = date.split("T");
		String[] dd = dt[0].split("-");
		String[] tt = dt[1].split(":");

		dateAndTime[0] = new String(dd[0] + dd[1] + dd[2]);
		dateAndTime[1] = new String(tt[0] + tt[1] + tt[2]);

		return dateAndTime;
	}

	public static String addDay(long d0, int days) {
		Date d = convertLong2DateDate(d0);

		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DATE, days); // minus number would decrement the days

		String newD = "" + cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int d1 = cal.get(Calendar.DAY_OF_MONTH);

		newD += (m > 9) ? ("" + m) : ("0" + m);

		newD += (d1 > 9) ? ("" + d1) : ("0" + d1);

		return newD;
	}

	public static String addDay(String date, int days) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			return null;
		}

		cal.add(Calendar.DATE, 1);

		return sdf.format(cal.getTime());
	}

	/**
	 * Get a diff between two dates
	 * 
	 * @param date1
	 *            the oldest date
	 * @param date2
	 *            the newest date
	 * @param timeUnit
	 *            the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date1.getTime() - date2.getTime();
		// long a = TimeUnit.MINUTES.convert(diffInMillies,
		// TimeUnit.MILLISECONDS);
		// long b = TimeUnit.SECONDS.convert(diffInMillies,
		// TimeUnit.MILLISECONDS);
		// long c= TimeUnit.MILLISECONDS.convert(diffInMillies,
		// TimeUnit.MILLISECONDS);

		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	public static int getWeekDay(String date) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			return 0;
		}
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static long calcDiff(String startDate, String stopDate) {
		Calendar cal2 = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			cal1.setTime(sdf.parse(startDate));
			cal2.setTime(sdf.parse(stopDate));
		} catch (ParseException e) {
			return 0;
		}
		// String s = sdf.format(cal1.getTime());
		// String z = sdf.format(cal2.getTime());

		long diff = getDateDiff(cal1.getTime(), cal2.getTime(), TimeUnit.SECONDS);

		return diff;
	}

	public static String ToHourMinutes(long minutes) {
		minutes = (long) Math.round(minutes / 60.0);
		long hour = minutes / 60;
		long min = minutes % 60;

		String h = "" + hour;
		String m = "" + min;
		// if (hour < 10) {
		// h = "0" + hour;
		// }
		if (min < 10) {
			m = "0" + min;
		}
		if (hour == 0 && min == 0)
			return "&nbsp;";
		return h + ":" + m;
	}

	public static Long convertDate2Long(String date) {
		String dateAndTime = new String();
		String[] dd;
		String[] tt;
		if (date.contains("T") && date.contains("-") && date.contains(":")) {
			String[] dt = date.split("T");
			dd = dt[0].split("-");
			tt = dt[1].split(":");

			dateAndTime += new String(dd[0] + dd[1] + dd[2]);
			dateAndTime += new String(tt[0] + tt[1] + tt[2]);

			long res;
			try {
				res = Long.parseLong(dateAndTime);
			} catch (NumberFormatException e) {
				res = 0;
			}
			return res;
		} else if (date.length() == 14) {
			return Long.parseLong(date);

		}
		return -1L;
	}

	public static Date convertLong2DateDate(long date) {

		String dd = String.valueOf(date);
		int year = Integer.parseInt(dd.substring(0, 4));
		int month = Integer.parseInt(dd.substring(4, 6));
		int day = Integer.parseInt(dd.substring(6, 8));
		int hrs = Integer.parseInt(dd.substring(8, 10));
		int min = Integer.parseInt(dd.substring(10, 12));
		int sec = Integer.parseInt(dd.substring(12, 14));
		Calendar c = GregorianCalendar.getInstance();

		c.set(year, month - 1, day, hrs, min, sec);
		return c.getTime();

	}

	public static String convertLong2Date(String date) {
		String d = date.substring(0, 8);
		String t = date.substring(8, 14);
		return convert(d, t);
	}

	public static String convertLong2Date(long date) {
		return convertLong2Date("" + date);
	}

	public static String stripSecondsFromLongDate(long date) {
		return new String("" + date).substring(0, 8);
	}

	public static String stripTimeFromLongDate(long date) {
		String s = new String("" + date);
		return s.substring(0, 4) + "-" + s.substring(4, 6) + "-" + s.substring(6, 8);
	}

	public static String displayFormatDate(int lastChangedDate) {
		String s = new String("" + lastChangedDate);
		String ts = s.substring(0, 4) + "-" + s.substring(4, 6) + "-" + s.substring(6, 8);
		return ts;
	}

	public static String convert(String date, String time) {
		String ts = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + "T"
				+ time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4, 6);
		return ts;
	}

	public static String convert(int date, int time) {
		if (time >= 100000)
			return convert("" + date, "" + time);
		else if (time >= 10000)
			return convert("" + date, "0" + time);
		else
			return convert("" + date, "00" + time);

	}

	public static String getTimestamp() {
		Calendar c = GregorianCalendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateFormatted = fmt.format(c.getTime());

		return dateFormatted;
	}

	public static String getTimestamp(int days) {
		Calendar c = GregorianCalendar.getInstance();
		Date d = c.getTime();
		d.setTime(d.getTime() - days * 1000 * 60 * 60 * 24);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateFormatted = fmt.format(d);

		return dateFormatted;
	}

	public static int getWorkWeekNumber(String date) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			return 0;
		}

		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	public static String[] getWorkWeek(String date) {
		String[] res = new String[2];
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			return null;
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		// int week = cal.get(Calendar.WEEK_OF_YEAR);

		int day = cal.get(Calendar.DAY_OF_WEEK);

		int shiftIt;
		if (day >= Calendar.MONDAY && day <= Calendar.SATURDAY) {
			shiftIt = Calendar.MONDAY - day;
		} else {
			shiftIt = -6;
		}

		cal.add(Calendar.HOUR, 24 * shiftIt);

		res[0] = sdf.format(cal.getTime());

		cal.add(Calendar.HOUR, 24 * 6);

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		res[1] = sdf.format(cal.getTime());

		cal.add(Calendar.HOUR, 4);
		// String s = sdf.format(cal.getTime());

		return res;
	}

	public static String getTimestampTime() {
		Calendar c = GregorianCalendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("HHmmss");
		String dateFormatted = fmt.format(c.getTime());
		return dateFormatted;
	}

	public static String getTimestampDate() {
		Calendar c = GregorianCalendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		String dateFormatted = fmt.format(c.getTime());
		return dateFormatted;

	}

	public static Date getTime() {
		Calendar c = GregorianCalendar.getInstance();
		return c.getTime();
	}

	public static String getTimestampDateWithDivider() {
		Calendar c = GregorianCalendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String dateFormatted = fmt.format(c.getTime());
		return dateFormatted;

	}

}
