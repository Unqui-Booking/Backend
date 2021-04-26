package ar.edu.unq.tip.unquibooking.model;

import javax.persistence.*;

@Entity
@Table(name= "desk")
public class Desk {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=50, nullable = false, unique = true)
	private String nameDesk;

	public Long getId() {
		return this.id;
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
		
	
}
