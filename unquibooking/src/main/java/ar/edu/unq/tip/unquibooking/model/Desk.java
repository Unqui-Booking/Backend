package ar.edu.unq.tip.unquibooking.model;

import java.util.ArrayList;
import java.util.List;

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

		//@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
		//private List<Booking> bookings = new ArrayList<Booking>();


		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName(){
			return nameDesk;
		}

		public void setName(String nameDesk){
			this.nameDesk = nameDesk;
		}
		
		
}
