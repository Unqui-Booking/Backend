package ar.edu.unq.tip.unquibooking.dto;

import ar.edu.unq.tip.unquibooking.model.Seat;

public class SeatDTO {
	
	private Long id;
	private DeskDTO desk;
	private boolean available;
	
	public SeatDTO(Seat seat) {
		this.id = seat.getId();
		this.desk = new DeskDTO(seat.getDesk().getId());
		this.available = seat.getAvailable();
	}
	
	public SeatDTO() { }
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public DeskDTO getDesk() {
		return desk;
	}
	
	public void setDesk(DeskDTO desk) {
		this.desk = desk;
	}
	
	public boolean getAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
}