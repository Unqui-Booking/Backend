package ar.edu.unq.tip.unquibooking.model;

public class SeatDTO {
	
	private Long id;
	private Long desk;
	private boolean available;
	
	public SeatDTO(Seat seat) {
		this.id = seat.getId();
		this.desk = seat.getDesk().getId();
		this.available = seat.getAvailable();
	}
	
	public SeatDTO() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getDesk() {
		return desk;
	}
	
	public void setDesk(Long desk) {
		this.desk = desk;
	}
	
	public boolean getAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
}