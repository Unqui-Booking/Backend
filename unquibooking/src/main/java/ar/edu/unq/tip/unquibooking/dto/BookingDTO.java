package ar.edu.unq.tip.unquibooking.dto;

import java.time.LocalDate;

import ar.edu.unq.tip.unquibooking.model.Booking;

public class BookingDTO {
	
	 private Long id;
	 private SeatBookingDTO seat;
	 private LocalDate date;
	 private Integer startTime;
	 private Integer endTime;
	 
	 public BookingDTO(Booking booking) {
		 this.id = booking.getId();
		 this.seat = new SeatBookingDTO(booking.getSeat());
		 this.date = booking.getDate();
		 this.startTime = booking.getStartTime();
		 this.endTime = booking.getEndTime();
	 }

	 public BookingDTO() { }
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SeatBookingDTO getSeat() {
		return seat;
	}

	public void setSeat(SeatBookingDTO seat) {
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
