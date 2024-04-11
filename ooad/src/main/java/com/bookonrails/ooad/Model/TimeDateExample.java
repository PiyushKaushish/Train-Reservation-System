package com.bookonrails.ooad.Model;

import java.sql.Time;
import java.sql.Date;

public class TimeDateExample {
    public static void main(String[] args) {
        // Creating a java.sql.Time object
        Time currentTime = new Time(System.currentTimeMillis());

        // Creating a java.sql.Date object
        Date currentDate = new Date(System.currentTimeMillis());

        // Displaying current time and date
        System.out.println("Current Time: " + currentTime);
        System.out.println("Current Date: " + currentDate);

        // Example of using Time and Date objects retrieved from a database
        // In a real-world scenario, you would retrieve these values from a database query

        // Example: Suppose you retrieved a time value "13:45:30" and a date value "2022-04-12" from the database
        // Simulating retrieval from the database
        Time timeFromDB = Time.valueOf("13:45:30");
        Date dateFromDB = Date.valueOf("2022-04-12");

        // Displaying time and date retrieved from the database
        System.out.println("Time from Database: " + timeFromDB);
        System.out.println("Date from Database: " + dateFromDB);
    }
}
