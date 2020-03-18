package com.example.calendarsample.provider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.example.calendarsample.CalendarObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarProvider {
    private Context context;
    public static final String LOCAL = "My Calendar";
    public static final String ALL = "ALL";

    public CalendarProvider(Context context) {
        this.context = context;

    }


    public List<CalendarObject> readCalendar(String account) {
        Cursor cur = null;
        List<CalendarObject> list = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String[] projection = new String[]{CalendarContract.Events.CALENDAR_ID, CalendarContract.Events.TITLE,
                CalendarContract.Events.DESCRIPTION, CalendarContract.Events.DTSTART, CalendarContract.Events.DTEND,
                CalendarContract.Events.ALL_DAY, CalendarContract.Events.EVENT_LOCATION, CalendarContract.Events.ACCOUNT_TYPE,
                CalendarContract.Events.ACCOUNT_NAME,
                CalendarContract.Events.DISPLAY_COLOR,
                CalendarContract.Events.CALENDAR_COLOR};

        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[]{"khiconlinh@gmail.com", "com.example.calendasample",
                "khiconlinh@gmail.com"};
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
        } else {
            Cursor cursor;

            if (account.equals("ALL")) {
                cursor = cr.query(Uri.parse
                                ("content://com.android.calendar/events"),
                        projection, null, null, null);
            } else {
                String accountNameSelect = "account_name like " + "'" + account + "'";
                cursor = cr.query(Uri.parse
                                ("content://com.android.calendar/events"),
                        projection, accountNameSelect, null, CalendarContract.Events.DISPLAY_COLOR);
            }
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                String diStart = "";
                String diEnd = "";
                try {
                    diStart = convertLongToDate(cursor.getString(3));
                    diEnd = convertLongToDate(cursor.getString(4));
                } catch (Exception e) {
                    Log.e("TAG", "Error " + e.toString());
                }
                String allDay = cursor.getString(5);
                String location = cursor.getString(6);
                String accountType = cursor.getString(7);
                String accountName = cursor.getString(8);
                int color = cursor.getInt(9);
                int calendarColor = cursor.getInt(10);
                list.add(new CalendarObject(id, title, description, diStart,
                        diEnd, allDay, location, accountType, accountName, color, calendarColor));

            }
        }

        return list;
    }

    private String convertLongToTime(String value) {
        long millisecond = Long.parseLong(value);
        String dateString = DateFormat.format("HH:mm:ss dd/MM/yyyy", new Date(millisecond)).toString();
        return dateString;
    }

    private String convertLongToDate(String value) {
        long millisecond = Long.parseLong(value);
        String dateString = DateFormat.format("dd/MM/yyyy", new Date(millisecond)).toString();
        return dateString;
    }


}