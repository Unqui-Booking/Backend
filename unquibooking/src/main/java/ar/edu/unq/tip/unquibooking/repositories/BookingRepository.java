package ar.edu.unq.tip.unquibooking.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.tip.unquibooking.model.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long>{
	
	public abstract List<Booking> findBySeatId(Long seat);
	public abstract List<Booking> findBySeatIdAndDateAndStartTimeAndEndTime(Long seat, LocalDate date,Integer startTime, Integer endTime);
	public abstract List<Booking> findBySeatIdAndDate(Long seat, LocalDate date);
    public abstract List<Booking> findByUserIdAndDeletedOrderByDateDesc(Long user, boolean deleted);
    public abstract List<Booking> findByDateAndDeletedAndStateOrderByStartTimeAsc(LocalDate date, boolean deleted, String state);
    							  
}
