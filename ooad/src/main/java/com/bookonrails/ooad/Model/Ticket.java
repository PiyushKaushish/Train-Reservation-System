package com.bookonrails.ooad.Model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_id;
    private String PNR;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainNo", referencedColumnName = "trainNo")
    private Train train;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SRC", referencedColumnName = "stationCode")
    private Station SRC;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEST", referencedColumnName = "stationCode")
    private Station DEST;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Passenger> passengers;

    private Boolean wantFood;
    private Boolean veg;
    private int quantity;

    private Date date;
    // enum
    @Enumerated(EnumType.STRING)
    private ClassType classes;
    // enum
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private int WaitingListNumber;
    // enum
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToOne(mappedBy = "ticket", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Payment payment;

    private double totalAmount;
    private double foodprice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_availability_id", nullable = false)
    private SeatAvailability seatAvailability;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<Feedback> feedbackList;

    public Ticket() {
        this.paymentStatus = PaymentStatus.Pending;
        this.WaitingListNumber = -1;

    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus PaymentStatus) {
        paymentStatus = PaymentStatus;
    }

    public Long getId() {
        return ticket_id;
    }

    public void setId(Long id) {
        this.ticket_id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount() {
        if (seatAvailability == null) {
            // seat for such classType is not available
            this.totalAmount = 0;
        }
        this.totalAmount = seatAvailability.getFare(SRC, DEST, passengers);
    }

    public double getCancellationCharge() {
        SeatAvailability s = train.getSeatAvailabilityClasswise(classes, date);
        return s.getCancellationCharge();
    }

    public String getPNR() {
        return PNR;
    }

    public void setPNR(String pNR) {
        PNR = pNR;
    }

    public Station getSRC() {
        return SRC;
    }

    public void setSRC(Station sRC) {
        SRC = sRC;
    }

    public Station getDEST() {
        return DEST;
    }

    public void setDEST(Station dEST) {
        DEST = dEST;
    }

    public User getUser() {
        return user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date newDate) {
        this.date = newDate;
    }

    public ClassType getClasses() {
        return classes;
    }

    public void setClasses(ClassType classes) {
        this.classes = classes;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public int getWaitingListNumber() {
        return WaitingListNumber;
    }

    public void setWaitingListNumber(int waitingListNumber) {
        WaitingListNumber = waitingListNumber;
    }

    public Long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Boolean getWantFood() {
        return wantFood;
    }

    public boolean isPassengersWaiting(){
        for(Passenger p : passengers)
        {
            if(p.isWaitingList()){
                return true;
            }
        }
        return false;

    }

    public void setWantFood(Boolean wantFood) {
        this.wantFood = wantFood;
    }

    public Boolean getVeg() {
        return veg;
    }

    public void setVeg(Boolean veg) {
        this.veg = veg;
        this.setFoodprice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getFoodprice() {
        return foodprice;
    }

    public void setFoodprice() {
        FoodPrice fp = new FoodPrice();
        double p;
        if (veg) {
            p = fp.getVegprice() * quantity;
        } else {
            p = fp.getNonvegprice() * quantity;
        }

        this.foodprice = p;

    }

    public void setFoodprice(double foodprice) {
        this.foodprice = foodprice;
    }

    public SeatAvailability getSeatAvailability() {
        return seatAvailability;
    }

    public void setSeatAvailability(SeatAvailability seatAvailability) {
        this.seatAvailability = seatAvailability;
    }

    public double calculateFoodPrice() {
        FoodPrice fp = new FoodPrice();
        if (wantFood) {
            if (veg) {
                return fp.getVegprice() * quantity;
            } else {
                return fp.getNonvegprice() * quantity;
            }
        }
        return 0.0;
    }

    public double calculateFinalPrice(){
        return foodprice+totalAmount;
    }
}
