package ar.edu.unq.tip.unquibooking.model;

import javax.persistence.*;

@Entity
@Table(name="desk")
public class Desk {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, name="desk_id")
	private Long id;

	@Column(length=50, nullable=false, unique=true)
	private String nameDesk;

	@Column(nullable=false)
	private Integer maxAmountSeats;
	
	@Column(length=50, nullable=false)
	private String area;
	
	public Desk(String nameDesk, Integer maxAmountSeats, String area) {
		this.nameDesk = nameDesk;
		this.maxAmountSeats = maxAmountSeats;
		this.area = area.toLowerCase();
	}
	
	public Desk() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameDesk(){
		return nameDesk;
	}

	public void setNameDesk(String nameDesk){
		this.nameDesk = nameDesk;
	}
	
	public Integer getMaxAmountSeats(){
		return maxAmountSeats;
	}

	public void setMaxAmountSeats(Integer maxAmountSeats){
		this.maxAmountSeats = maxAmountSeats;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	
	
}
