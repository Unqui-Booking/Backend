package ar.edu.unq.tip.unquibooking.repositories;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.tip.unquibooking.model.Booking;
import ar.edu.unq.tip.unquibooking.model.LocalDateConverter;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long>{

	public abstract ArrayList<Booking> findBySeatIdAndDateAndStartTimeAndEndTime(Long seat, LocalDate date,Integer startTime, Integer endTime);
    
    
}
