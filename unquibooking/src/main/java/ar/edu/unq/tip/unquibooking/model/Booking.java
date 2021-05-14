package ar.edu.unq.tip.unquibooking.model;

import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name= "booking")
public class Booking {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;

    @ManyToOne(cascade={CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Convert(converter = LocalDateConverter.class)
    @Column(nullable=false)
    private LocalDate date;
    
    @Column(nullable=false)
    private Integer startTime;

    @Column(nullable=false)
    private Integer endTime;

    public Booking(Seat seat, Integer startTime, Integer endTime, LocalDate date) {
    	this.seat = seat;
    	this.startTime = startTime;
    	this.endTime = endTime;
    	this.date = date;
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

}
