package ar.edu.unq.tip.unquibooking.model;

import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name= "booking")
public class Booking {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @JsonFormat(pattern="yyyy-MM-dd", shape=Shape.STRING)
    @Column(nullable=false)
    private LocalDate date;
    
    @Column(nullable=false)
    private Integer startTime;

    @Column(nullable=false)
    private Integer endTime;
    
    @Column
	private boolean deleted;
    
    @Column
    private String state;
    
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public Booking(Seat seat, Integer startTime, Integer endTime, LocalDate date, User user) {
    	this.seat = seat;
    	this.startTime = startTime;
    	this.endTime = endTime;
    	this.date = date;
    	this.deleted = false;
    	this.user = user;
    	this.state = "toConfirm";
    }
    
    public Booking() { }
    
    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public LocalDate getDate(){
        return date;
    }

    public void setStartTime(Integer time){
        this.startTime = time;
    }

    public Integer getStartTime(){
        return startTime;
    }

    public void setEndTime(Integer time){
        this.endTime = time;
    }

    public Integer getEndTime(){
        return endTime;
    }

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	

}
