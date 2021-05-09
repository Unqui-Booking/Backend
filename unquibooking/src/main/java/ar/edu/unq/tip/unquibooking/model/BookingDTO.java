package ar.edu.unq.tip.unquibooking.model;

import java.time.LocalDate;

public class BookingDTO {
	
	 private Long id;
	 private Long seat;
	 private LocalDate date;
	 private Integer startTime;
	 private Integer endTime;
	 
	 public BookingDTO(Booking booking) {
		 this.id = booking.getId();
		 this.seat = booking.getSeat().getId();
		 this.date = booking.getDate();
		 this.startTime = booking.getStartTime();
		 this.endTime = booking.getEndTime();
	 }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSeat() {
		return seat;
	}

	public void setSeat(Long seat) {
		this.seat = seat;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}
	 
	 
}
