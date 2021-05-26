package ar.edu.unq.tip.unquibooking.model;

import javax.persistence.*;

@Entity
@Table(name="desk")
public class Desk {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, name="desk_id")
	private Long id;

	@Column(length=50, unique=true)
	private String nameDesk;
	
	@Column(length=50)
	private String area;
	
	@Column
	private boolean availableDesk;
	
	@Column
	private boolean deleted;
	
	public Desk(String nameDesk, String area, boolean availableDesk) {
		this.nameDesk = nameDesk;
		this.area = area;
		this.availableDesk = availableDesk;
		this.deleted = false;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public boolean getAvailableDesk() {
		return availableDesk;
	}

	public void setAvailableDesk(boolean available) {
		this.availableDesk = available;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
}
