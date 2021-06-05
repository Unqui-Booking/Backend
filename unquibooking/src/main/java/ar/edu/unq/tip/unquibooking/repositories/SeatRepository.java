package ar.edu.unq.tip.unquibooking.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.tip.unquibooking.model.Seat;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Long>{
	public abstract List<Seat> findByDeskId(Long desk);
	
}
