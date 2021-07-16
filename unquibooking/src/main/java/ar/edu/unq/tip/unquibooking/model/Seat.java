package ar.edu.unq.tip.unquibooking.model;

import javax.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, name="seat_id")
    private Long id;
    
	@ManyToOne(cascade={CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "desk_id")
	private Desk desk;
	
	@Column
	private boolean deleted;
	
	public Seat(Desk desk) {
		this.desk = desk;
		this.deleted = false;
	}
	
	public Seat() { }

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

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
}