package ar.edu.unq.tip.unquibooking.dto;

import ar.edu.unq.tip.unquibooking.model.Seat;

public class SeatBookingDTO {
	
	private Long id;
	
	public SeatBookingDTO(Seat seat) {
		this.id = seat.getId();
	}
	
	public SeatBookingDTO() { }
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
