package ar.edu.unq.tip.unquibooking.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unq.tip.unquibooking.model.Booking;

public interface BookingRepository extends CrudRepository<Booking, Long>{

    public abstract Optional<Booking> findByDesk(Long idDesk);
    
}
