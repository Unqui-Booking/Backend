package ar.edu.unq.tip.unquibooking.model;

import javax.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, name="seat_id")
    private Long id;
    
	@ManyToOne(cascade={CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "desk_id")
	private Desk desk;
    
	@Column
    private boolean available;
	
	public Seat(Desk desk) {
		this.desk = desk;
		this.available = true;
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