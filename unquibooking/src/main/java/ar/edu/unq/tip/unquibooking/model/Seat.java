package ar.edu.unq.tip.unquibooking.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "seat")
public class Seat {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, name="seat_id")
    private Long id;
    
	@ManyToOne(cascade={CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "desk_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Desk desk;
    
	@Column(nullable=false)
    private boolean available;
	
	public Seat(Desk desk, boolean available) {
		this.desk = desk;
		this.available = available;
	}
	
	public Seat() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Desk getDesk() {
		return desk;
	}

	public void setDesk(Desk desk) {
		this.desk = desk;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

}