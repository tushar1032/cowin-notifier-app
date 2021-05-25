package com.tushar.cowin.notifier.commons;

public class Constants {
    public static final Integer THREADS_COUNT = 4;
    public static final String MOZILLA_HEADER = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36 Edg/90.0.818.51";
    public static final String USER_AGENT_HEADER_KEY = "User-Agent";
    public static final String GET_CENTERS_BY_DISTRICT_FOR_7_DAYS_URL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/calendarByDistrict?district_id=%s&date=%s";
    public static final String MAIL_SUBJECT = "Cowin Notification: %s +";
    public static final String STATES_INFO_URL = "https://cdn-api.co-vin.in/api/v2/admin/location/states";
    public static final String DISTRICT_INFO_URL = "https://cdn-api.co-vin.in/api/v2/admin/location/districts/%s";
}
