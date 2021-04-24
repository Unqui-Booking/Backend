package ar.edu.unq.tip.unquibooking.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.tip.unquibooking.model.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long>{

    public abstract Optional<Booking> findByDesk(Long idDesk);
    
}
