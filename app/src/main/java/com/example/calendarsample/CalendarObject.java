package com.example.calendarsample;

public class CalendarObject {
    private int id;
    private String title ;
    private String description;
    private String diStart;
    private String dtEnd ;
    private String allDay ;
    private String location;


    //test
    private String accountType;
    private String accountName;
    private int colorDisplay;
    private int calendarColor;

    public int getCalendarColor() {
        return calendarColor;
    }

    public void setCalendarColor(int calendarColor) {
        this.calendarColor = calendarColor;
    }

    public int getColorDisplay() {
        return colorDisplay;
    }

    public void setColorDisplay(int colorDisplay) {
        this.colorDisplay = colorDisplay;
    }

    public CalendarObject(int id, String title, String description, String diStart,
                          String dtEnd, String allDay, String location, String accountType,
                          String accountName, int colorDisplay, int calendarColor) {
        this.title = title;
        this.calendarColor = calendarColor;
        this.accountName = accountName;
        this.description = description;
        this.diStart = diStart;
        this.dtEnd = dtEnd;
        this.allDay = allDay;
        this.location = location;
        this.accountType = accountType;
        this.colorDisplay = colorDisplay;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiStart() {
        return diStart;
    }

    public void setDiStart(String diStart) {
        this.diStart = diStart;
    }

    public String getDtEnd() {
        return dtEnd;
    }

    public void setDtEnd(String dtEnd) {
        this.dtEnd = dtEnd;
    }

    public String getAllDay() {
        return allDay;
    }

    public void setAllDay(String allDay) {
        this.allDay = allDay;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
