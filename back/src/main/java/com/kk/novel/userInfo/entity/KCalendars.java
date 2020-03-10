package com.kk.novel.userInfo.entity;

import java.io.Serializable;

/**
 * (KCalendars)实体类
 *
 * @author kk
 * @since 2020-02-17 16:00:51
 */
public class KCalendars implements Serializable {
    private static final long serialVersionUID = -42029608506171788L;
    
    private String schedName;
    
    private String calendarName;
    
    private Integer calendar;


    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public Integer getCalendar() {
        return calendar;
    }

    public void setCalendar(Integer calendar) {
        this.calendar = calendar;
    }

}