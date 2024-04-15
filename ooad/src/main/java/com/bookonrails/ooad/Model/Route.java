package com.bookonrails.ooad.Model;

import jakarta.persistence.*;
import java.util.List;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String routeCode;

    @OneToOne(mappedBy = "route", fetch = FetchType.LAZY)
    private Train train;

    @OneToMany(mappedBy = "route")
    private List<StationTimings> stationTimings;

    public Time getDepartureTimeByStation(Station s){
        for (StationTimings st : stationTimings) {
            if(st.getStation().equals(s) ){
                return st.getDepartureTime();
            }
        }
        return null;
    }

    public Time getArrivalTimeByStation(Station s){
        for (StationTimings st : stationTimings) {
            if(st.getStation().equals(s) ){
                return st.getArrivalTime();
            }
        }
        return null;


    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public List<StationTimings> getStationTimings() {
        return stationTimings;
    }

    public void setStationTimings(List<StationTimings> stationTimings) {
        this.stationTimings = stationTimings;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void addStationTimings(StationTimings st) {
        this.stationTimings.add(st);
    }

    public void deleteStationTimings(StationTimings st) {
        this.stationTimings.remove(st);
    }

    // get List of Stops
    public List<String> getAllStops() {
        List<String> stops = new java.util.ArrayList<String>();
        for (StationTimings st : stationTimings) {
            stops.add(st.toString());
        }
        return stops;
    }

    // get StationTimings by StationCode
    public StationTimings getStationTimingsByStationCode(String stationCode) {
        for (StationTimings st : stationTimings) {
            if (st.getStation().getStationCode().equals(stationCode)) {
                return st;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String route = new String();
        for (StationTimings st : stationTimings) {
            route += st.toString() + "\n";
        }
        return route;
    }

    public List<Station> getStations() {
        List<Station> stations = new ArrayList<>();
        for (StationTimings st : stationTimings) {
            stations.add(st.getStation());
        }
        return stations;
    }

    // to check if a particular station is present given station code from the list
    // of all stations in the Route
    // returns index; -1 if not present
    public int isStationPresent(String stationCode) {
        for (StationTimings st : stationTimings) {
            if (st.getStation().getStationCode().equals(stationCode)) {
                return stationTimings.indexOf(st);
            }
        }
        return -1;
    }

    // to get the next station by station code from the list of all stations in the
    // Route
    public StationTimings getNextStation(String currentStationCode) {
        for (int i = 0; i < stationTimings.size(); i++) {
            if (stationTimings.get(i).getStation().getStationCode().equals(currentStationCode)) {
                if (i + 1 < stationTimings.size()) {
                    return stationTimings.get(i + 1);
                }
            }
        }
        return null; // returns null if that is the last station
    }

    // to get the previous station by station code from the list of all stations in
    // the Route
    public StationTimings getPreviousStation(String currentStationCode) {
        for (int i = 0; i < stationTimings.size(); i++) {
            if (stationTimings.get(i).getStation().getStationCode().equals(currentStationCode)) {
                if (i - 1 >= 0) {
                    return stationTimings.get(i - 1);
                }
            }
        }
        return null; // returns null if that is the first station
    }

    // to get the distance between two stations by station code from the list of all
    // stations in the Route
    public double getDistanceBetweenStations(String stationCode1, String stationCode2) {
        int distance = 0;
        boolean sourceFlag = false;
        boolean destFlag = false;
        for (StationTimings st : stationTimings) {
            if (st.getStation().getStationCode().equals(stationCode1)) {
                sourceFlag = true;
            }
            if (st.getStation().getStationCode().equals(stationCode2)) {
                destFlag = true;
            }
            if (sourceFlag && !destFlag) {
                System.out.print(st.getStation().getStationCode());
                System.out.println(st.getDistanceFromNextStation());

                distance += st.getDistanceFromNextStation();
            }
            if (sourceFlag && destFlag) {
                return distance;
            }
        }
        return -1.0; // returns -1 if the stations are not present in the route
    }

    // checks if a route exists in a particular train or not
    public boolean isRoutePresent(Station src, Station dest) {
        int s = isStationPresent(src.getStationCode());
        int d = isStationPresent(dest.getStationCode());
        return s >= 0 && d >= 0 && s < d; // both should be present to create a route
    }

    public List<StationTimings> findSubRoute(Station SRC, Station DEST) {
        if (isRoutePresent(SRC, DEST)) {
            List<StationTimings> subList = new ArrayList<>();
            boolean addNextStation = false;

            // Create a sublist of stationTimings starting from SRC and ending at DEST
            for (StationTimings st : stationTimings) {
                if (st.getStation().equals(SRC)) {
                    addNextStation = true;
                }
                if (addNextStation) {
                    subList.add(st);
                }
                if (st.getStation().equals(DEST)) {
                    subList.add(st);
                    break;
                }
            }
            return subList;
        }
        return new ArrayList<>();

    }

    private long calculateTimeDifference(Time src, Time dest) {
        long t1 = src.getTime();
        long t2 = dest.getTime();
        if (t2 > t1) {
            return t2 - t1;
        } else {
            return (t1-Time.valueOf("00:00:00").getTime() ) + (t2 - Time.valueOf("00:00:00").getTime());
        }
    }

    // Calculate the duration between two stations
    public long calculateTime(List<StationTimings> subList) {
        long totalDuration = 0;
        // Time prevDepartureTime = null;
        for (int i = 0; i < subList.size() - 2; i++) {
            StationTimings st = subList.get(i);
            StationTimings nextST = subList.get(i + 1);
            long duration = 0;
            if (i == 0) {
                duration = calculateTimeDifference(st.getDepartureTime(), st.getArrivalTime());
            } else {
                duration = calculateTimeDifference(st.getArrivalTime(), nextST.getArrivalTime());
            }

            System.out.println(st.getStation().getStationName() + " " + nextST.getStation().getStationName() + " "
                    + (duration % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000) + "days" + duration / (60 * 60 * 1000)
                    + "Hrs " + ((duration % (24 * 60 * 60 * 1000)) % (60 * 60 * 1000)) / (60 * 1000) + " mins");
            totalDuration += duration;
        }
        return totalDuration;

    }

    public Long calculateDuration(List<StationTimings> timings) {
        long totalMilliseconds = 0;
        for (int i = 0; i < timings.size() - 1; i++) {
            StationTimings currentTiming = timings.get(i);
            StationTimings nextTiming = timings.get(i+1);
            long milliseconds = Math.abs(nextTiming.getDepartureTime().getTime() - currentTiming.getArrivalTime().getTime());
            totalMilliseconds += milliseconds;
            // If departure time is earlier than arrival time, it means it's on the next day
            if (nextTiming.getDepartureTime().before(currentTiming.getArrivalTime())) {
                totalMilliseconds += TimeUnit.DAYS.toMillis(1); // Add a day
            }
        }
        return totalMilliseconds;
    }

    public String formatTime(Long milliseconds) {
        long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds) % 24;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds) % 60;
        return String.format("%d days %02d hours %02d minutes", days, hours,minutes);
    }

    public String calculateJourneyTime(Station src, Station dest) {
        if (isRoutePresent(src, dest)) {
            List<StationTimings> subList = findSubRoute(src, dest);
            Long journeyTimeInMills = calculateDuration(subList);
            return formatTime(journeyTimeInMills);
            // return calculateDuration(subList);
        }
        return "Route does not exist";
    }

	

}