package ar.edu.unq.tip.unquibooking.repositories;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unq.tip.unquibooking.model.Booking;

public interface BookingRepository extends CrudRepository{

    public abstract Booking findByDesk(Long idDesk);
    
}
