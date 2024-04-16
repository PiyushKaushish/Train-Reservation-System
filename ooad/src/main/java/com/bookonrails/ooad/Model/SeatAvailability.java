package com.bookonrails.ooad.Model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class SeatAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainNo", nullable = false)
    private Train train;

    @Temporal(TemporalType.DATE)
    private Date date;
    
    private int no_of_coaches;
    @Enumerated(EnumType.STRING) // to store the type of coach as a string
    private ClassType classes;
    private int availableSeats;

    @OneToMany(mappedBy = "seatAvailability", cascade = CascadeType.ALL)
    private List<Ticket> waitingList;
    
    @ElementCollection
    @CollectionTable(name = "CancelledSeats", joinColumns = @JoinColumn(name = "seatAvailability_id"))
    @Column(name = "seat_number")
    private List<Integer> CancelledSeats; // check n%2 = 1 -> Lower, n%2 = 0 -> Upper

    private int lastUnbookedLowerSeat;
    private int lastUnbookedUpperSeat;
    private int lastLowerSeat;
    private int lastUpperSeat;

    private double basePrice;
    private double farePerKM;
    private double seniorCitizenDiscount;
    private double cancellationCharge;

    
    public SeatAvailability(){
        this.waitingList = new ArrayList<>();
        this.CancelledSeats = new ArrayList<>();

        this.setAvailableSeats();
        this.lastUnbookedLowerSeat=1; // next seat that is lower birth and is not booked
        this.lastUnbookedUpperSeat=2; // next seat that is upper birth and is not booked
        setLastLowerSeat();
        setLastUpperSeat();
    }
    
    public SeatAvailability(int no_of_coaches){
        this.no_of_coaches = no_of_coaches;
        this.waitingList = new ArrayList<>();
        this.CancelledSeats = new ArrayList<>();

        this.setAvailableSeats();
        this.lastUnbookedLowerSeat=1; // next seat that is lower birth and is not booked
        this.lastUnbookedUpperSeat=2; // next seat that is upper birth and is not booked
        setLastLowerSeat();
        setLastUpperSeat();
    }

    
    public Train getTrain() {
        return train;
    }

    public String getTrainNo(){
        return train.getTrainNo();
    }

    private String convertDayOfWeekEnum(DayOfWeek d){
        switch (d) {
            case DayOfWeek.Monday:return "Monday";        
            case DayOfWeek.Tuesday:return "Tuesday";        
            case DayOfWeek.Wednesday:return "Wednesday";        
            case DayOfWeek.Thursday:return "Thursday";        
            case DayOfWeek.Friday:return "Monday";        
            case DayOfWeek.Saturday:return "Saturday";        
            case DayOfWeek.Sunday:return "Sunday";        
            default: return "invalid";
        }
    }

    public String getDaysRunning(){
        List<OperatingDay> od= train.getOperatingDays();
        String s = "";
        for (OperatingDay o: od){
            s+= convertDayOfWeekEnum(o.getDayOfWeek());
            s+=" ";
        }
        return s;

    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNo_of_coaches() {
        return no_of_coaches;
    }

    public void setNo_of_coaches(int no_of_coaches) {
        this.no_of_coaches = no_of_coaches;
    }

    public ClassType getClasses() {
        return classes;
    }

    public void setClasses(ClassType classes) {
        this.classes = classes;
    }

    public int getAvailableSeats() {
        System.out.println("Cancelled seats size"+CancelledSeats.size());
        return availableSeats+CancelledSeats.size();
    }

    public void setAvailableSeats() {
        this.availableSeats = no_of_coaches*30; // Assuming that each coach has 30 seats
    }

    public List<Ticket> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(List<Ticket> waitingList) {
        this.waitingList = waitingList;
    }

    public List<Integer> getCancelledSeats() {
        return CancelledSeats;
    }
    
    // birth has only 2 preferences - Lower or Upper
    public void setCancelledSeats(List<Integer> cancelledSeats) {
        CancelledSeats = cancelledSeats;
    }

    public int getLastUnbookedLowerSeat() {
        return lastUnbookedLowerSeat;
    }

    public void setLastUnbookedLowerSeat(int lastUnbookedLowerSeat) {
        this.lastUnbookedLowerSeat = lastUnbookedLowerSeat;
    }

    public int getLastUnbookedUpperSeat() {
        return lastUnbookedUpperSeat;
    }

    public void setLastUnbookedUpperSeat(int lastUnbookedUpperSeat) {
        this.lastUnbookedUpperSeat = lastUnbookedUpperSeat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getFarePerKM() {
        return farePerKM;
    }

    public void setFarePerKM(double farePerKM) {
        this.farePerKM = farePerKM;
    }

    public void addTicketToWaitingList(Ticket t){
        this.waitingList.add(t);
    }

    public void deleteTicketFromWaitingList(Ticket t){
        if(!waitingList.isEmpty()){
            this.waitingList.remove(t);
        }
    }

    public void checkWaitingPassengers(){
        if(waitingList.size()<=0){ // no waiting ticket
            return;
        }
        for (Ticket t: waitingList){
            List<Passenger> passengers = t.getPassengers();
            allocatePassengerSeatNo(passengers);
            Boolean waitingExistsInTicket=false;
            for (Passenger p: passengers){
                if(p.getSeatNo()==-1){
                    waitingExistsInTicket = true;
                }
                else{
                    p.setWaitingList(false);
                }
            }        
            if(!waitingExistsInTicket){
                // remove ticket from waiting list
                System.out.println("Remove Ticket "+t.getId());
                deleteTicketFromWaitingList(t);
            }
        } 
    }

    public void addCancelledSeat(int c){
        CancelledSeats.add(c);        
    }

    
    public void deleteCancelledSeat(int c) {
        if (!CancelledSeats.isEmpty()) {
            CancelledSeats.remove(Integer.valueOf(c));
        }
    }
    
    public boolean isFull(){
        if(getAvailableSeats()<=0){
            return true; // all coaches are full
        }
        else{
            return false; // seats are there
        }
    }


    // DO NOT use this function just to check if seats are available
    public int allocateSeatNumber(Birth b){ // checks if seat is available in train with birth preference b
        if(isFull()){
            return -1;
        }
        if(b==Birth.Lower){
            if(lastUnbookedLowerSeat<=lastLowerSeat){ // seats are empty
                int seatNo= lastUnbookedLowerSeat;
                availableSeats--;
                lastUnbookedLowerSeat+=2;
                return seatNo;
            }
            else if(CancelledSeats.size() > 0){
                // check for any cancelled seat which is lower
                for(int c:CancelledSeats){
                    if(c%2==1){
                        deleteCancelledSeat(c);
                        return c;
                    }
                }
                return -1;
            } 
        }
        else if (b== Birth.Upper){
            if(lastUnbookedUpperSeat<=lastUpperSeat){ // seats are empty
                int seatNo= lastUnbookedUpperSeat;
                lastUnbookedUpperSeat+=2;
                availableSeats--;
                return seatNo;
            }
            else if(CancelledSeats.size() > 0){
                // check for any cancelled seat which is lower
                for(int c:CancelledSeats){
                    if(c%2==0){
                        deleteCancelledSeat(c);
                        return c;
                    }
                }
                return -1;
            } 
        }
        return -1;
    }
    
    // DO NOT use this function just to check if seats are available
    // if seat availability is not available according to preference, then use this function
    // as we know that we cannot allocate user the seat preference they have
    public int allocateSeatNumber(){
        if(isFull()){
            return -1; // train is full for this coach
        }
        if(lastUnbookedLowerSeat<=lastLowerSeat){
            int seatNo= lastUnbookedLowerSeat;
            lastUnbookedLowerSeat+=2;
            availableSeats--;
            return seatNo;
        }
        else if (lastUnbookedUpperSeat<=lastUpperSeat){
            int seatNo= lastUnbookedLowerSeat;
            lastUnbookedUpperSeat+=2;
            availableSeats--;
            return seatNo;
        }
        else if (CancelledSeats.size()>=0){
            int seatNo = CancelledSeats.get(0);
            deleteCancelledSeat(seatNo);
            return seatNo;
        }
        return -1;
    }


    public int getLastLowerSeat() {
        return lastLowerSeat;
    }


    public void setLastLowerSeat() {
        if (availableSeats%2==1){
            this.lastLowerSeat= availableSeats;
        }
        else{
            this.lastLowerSeat = availableSeats -1;
        }
    }


    public int getLastUpperSeat() {
        return lastUpperSeat;
    }


    public void setLastUpperSeat() {
        if(availableSeats%2==0){
            this.lastUpperSeat= availableSeats;
        }
        else{
            this.lastUpperSeat= availableSeats-1;
        }
    }

    public void allocatePassengerSeatNo(List<Passenger> passengers){ // allocate seatNo for Passengers present in a ticket
        for (Passenger p: passengers){
            Birth b=p.getBirthpreference();
            int seatNo= allocateSeatNumber(b);
            if(seatNo>0){
                p.setWaitingList(false);
                p.setSeatNo(seatNo);
                p.setCoachNo((seatNo/this.no_of_coaches)+1);
                continue;
            }
            seatNo= allocateSeatNumber();
            if(seatNo>0){
                p.setWaitingList(false);
                p.setSeatNo(seatNo);
                p.setCoachNo((seatNo/this.no_of_coaches)+1);
                continue;
            }
            p.setWaitingList(true);
        }
    }

    // this function checks if there is any passenger which has a waiting list seat in a ticket 
    public boolean checkWaitingPassengers(List<Passenger> passengers){
        if(passengers.get(0).isWaitingList()){ // ticket should be added to waiting list
            return true;
        }
        else{
            for(Passenger p: passengers){
                if(p.isWaitingList()){
                    return true;
                }
            }
        }
        return false;
    }

    public int checkSeniorCitizenCount(List<Passenger> passengers){ // return no of senior citizen
        int c=0;
        for(Passenger p:passengers){
            if(p.isSeniorCitizen()){
                c++;
            }
        }
        return c;
    }

    public double getFare(Station src, Station dest,List<Passenger> passengers){
        int numOfTravelers=passengers.size();
        Route r=train.getRoute();
        double distance= r.getDistanceBetweenStations(src.getStationCode(), dest.getStationCode());
        return (this.farePerKM *distance + this.basePrice)* numOfTravelers - (getSeniorCitizenDiscount()*checkSeniorCitizenCount(passengers));
    }
    public double getFare(Station src, Station dest){ // get fare for one passenger
        Route r=train.getRoute();
        double distance= r.getDistanceBetweenStations(src.getStationCode(), dest.getStationCode());
        System.err.println("Distance: "+distance);
        return (this.farePerKM *distance + this.basePrice);
    }



    public double getSeniorCitizenDiscount() { // senior citizen get tickets at a lower price
        return seniorCitizenDiscount;
    }

    public void setSeniorCitizenDiscount(double seniorCitizenDiscount) {
        this.seniorCitizenDiscount = seniorCitizenDiscount;
    }


    public double getCancellationCharge() { // cancellation charge for ticket per class 
        return cancellationCharge;
    }

    public void setCancellationCharge(double cancellationCharge) {
        this.cancellationCharge = cancellationCharge;
    }  
    
    public List<Integer> getPassengerSeats(List<Passenger> p){
        List<Integer> seats=new ArrayList<>();
        for(Passenger pa:p){
            if (pa.getSeatNo()>0){ // ticket cancelled was confirmed
                seats.add(pa.getSeatNo());
            }
        }
        return seats;
    }
    
    public void cancelTicket(Ticket t){
        List<Integer> seat= getPassengerSeats(t.getPassengers());
        for(int s:seat){
            addCancelledSeat(s);
        }
        t.setStatus(TicketStatus.Cancelled);
    }

    
}
