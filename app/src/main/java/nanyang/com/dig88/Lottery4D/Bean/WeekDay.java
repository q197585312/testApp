package nanyang.com.dig88.Lottery4D.Bean;


import androidx.annotation.NonNull;

/**
 * Created by Administrator on 2018/11/19.
 */

public class WeekDay implements Comparable<WeekDay> {
    private String week;
    private String day;
    private int weekDay;
    private String dayOfMonthOfYear;

    public String getDayOfMonthOfYear() {
        return dayOfMonthOfYear;
    }

    public void setDayOfMonthOfYear(String dayOfMonthOfYear) {
        this.dayOfMonthOfYear = dayOfMonthOfYear;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public int compareTo(@NonNull WeekDay another) {
        int i = this.getWeekDay() - another.getWeekDay();
        if (i == 0) {
            return this.weekDay - another.getWeekDay();
        }
        return i;
    }
}
