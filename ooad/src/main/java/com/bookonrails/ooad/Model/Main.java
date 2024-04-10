package com.bookonrails.ooad.Model;

import java.sql.Time;
import java.util.Arrays;

// Test file for train models

public class Main {
    public static void main (String[] args) {
        StationTimings st1= new StationTimings();
        StationTimings st2= new StationTimings();
        StationTimings st3= new StationTimings();

        Station s1 = new Station();
        s1.setStationCode("HTE");
        s1.setStationName("Hatia");

        st1.setStation(s1);
        st1.setArrivalTime(Time.valueOf("09:00:00"));
        st1.setDepartureTime(Time.valueOf("09:15:00"));

        Station s2 = new Station();
        s2.setStationCode("RNC");
        s2.setStationName("Ranchi");
        
        st2.setStation(s2);
        st2.setArrivalTime(Time.valueOf("09:30:00"));
        st2.setDepartureTime(Time.valueOf("09:45:00"));

        Station s3 = new Station();
        s3.setStationCode("MURI");
        s3.setStationName("Muri");

        st3.setStation(s3);
        st3.setArrivalTime(Time.valueOf("10:00:00"));
        st3.setDepartureTime(Time.valueOf("10:15:00"));

        s1.setStationTimings(Arrays.asList (st1));
        s2.setStationTimings(Arrays.asList (st2));
        s3.setStationTimings(Arrays.asList (st3));
        
        Route r1 = new Route();
        r1.setStationTimings(Arrays.asList(st1,st2,st3));

        
        Train t1 = new Train();
        t1.setRoute(r1);
        r1.setTrain(t1);
        t1.setTrainName("Hatia Muri Express");
        t1.setTrainNo("12345");
        t1.setTraintype("Express");

        OperatingDay od1 = new OperatingDay();
        od1.setDayOfWeek(DayOfWeek.Monday);
        od1.setTrain(t1);

        OperatingDay od2 = new OperatingDay();
        od2.setDayOfWeek(DayOfWeek.Wednesday);
        od2.setTrain(t1);

        OperatingDay od3 = new OperatingDay();
        od3.setDayOfWeek(DayOfWeek.Friday);
        od3.setTrain(t1);


        t1.setOperatingDays(Arrays.asList(od1,od2,od3));

        SeatAvailability sa1 = new SeatAvailability();
        sa1.setAvailableSeats(100);
        sa1.setClasses(ClassType.Sleeper);
        sa1.setDate(java.sql.Date.valueOf("2024-10-01"));
        sa1.setFarePerKM(50);
        sa1.setBasePrice(200);
        sa1.setNo_of_coaches(5);
        sa1.setTrain(t1);

        SeatAvailability sa2 =new SeatAvailability();
        sa2.setAvailableSeats(100);
        sa2.setClasses(ClassType.AC2Tier);
        sa2.setDate(java.sql.Date.valueOf("2024-10-01"));
        sa2.setFarePerKM(100);
        sa2.setBasePrice(500);
        sa2.setNo_of_coaches(5);
        sa2.setTrain(t1);

        t1.setSeatAvailability(Arrays.asList(sa1,sa2));

        System.out.println(t1.getTrainName());
        System.out.println(t1.getTrainNo());
        System.out.println(t1.getTraintype());
        //Station
        System.out.println(t1.getRoute().getStationTimings().get(0).getStation().getStationName());
        //Arrival time
        System.out.println(t1.getRoute().getStationTimings().get(0).getArrivalTime());
        // departure time
        System.out.println(t1.getRoute().getStationTimings().get(0).getDepartureTime());
        
        System.out.println(t1.getRoute().getStationTimings().get(1).getStation().getStationName());
        System.out.println(t1.getRoute().getStationTimings().get(1).getArrivalTime());
        System.out.println(t1.getRoute().getStationTimings().get(1).getDepartureTime());
        System.out.println(t1.getRoute().getStationTimings().get(2).getStation().getStationName());
        System.out.println(t1.getRoute().getStationTimings().get(2).getArrivalTime());
        System.out.println(t1.getRoute().getStationTimings().get(2).getDepartureTime());
        // operating days
        System.out.println(t1.getOperatingDays().get(0).getDayOfWeek());
        System.out.println(t1.getOperatingDays().get(1).getDayOfWeek());
        System.out.println(t1.getOperatingDays().get(2).getDayOfWeek());
        // Seat Availibility
        System.out.println(t1.getSeatAvailability().get(0).getAvailableSeats());
        System.out.println(t1.getSeatAvailability().get(0).getNo_of_coaches());
        System.out.println(t1.getSeatAvailability().get(0).getBasePrice());
        System.out.println(t1.getSeatAvailability().get(0).getFarePerKM());
        System.out.println(t1.getSeatAvailability().get(0).getClasses());






    }

}
