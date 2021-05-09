package ar.edu.unq.tip.unquibooking.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.tip.unquibooking.model.Desk;

@Repository
public interface DeskRepository extends CrudRepository<Desk, Long> {
    
	public abstract ArrayList<Desk> findByArea(String area);
	
}
