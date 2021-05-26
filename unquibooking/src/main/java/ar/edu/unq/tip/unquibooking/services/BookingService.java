package ar.edu.unq.tip.unquibooking.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.tip.unquibooking.exception.BookingNotFoundException;
import ar.edu.unq.tip.unquibooking.exception.SeatNotFoundException;
import ar.edu.unq.tip.unquibooking.model.Booking;
import ar.edu.unq.tip.unquibooking.model.Seat;
import ar.edu.unq.tip.unquibooking.repositories.BookingRepository;

@Service
public class BookingService {
    
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    SeatService seatService;

    public ArrayList<Booking> getAllBookings(){
        return (ArrayList<Booking>) bookingRepository.findAll();
    }

    public Booking saveBooking(Booking booking) throws SeatNotFoundException{
    	Long seatId = booking.getSeat().getId();
    	Seat seat = seatService.getSeat(seatId);
    	Booking newBooking = bookingRepository.save(booking);
    	newBooking.setSeat(seat);
        return newBooking;
    }

    public Booking getBooking(Long idBooking) throws BookingNotFoundException{
    	Optional<Booking> optionalBooking = bookingRepository.findById(idBooking);
    	if(!optionalBooking.isPresent()) {
    		throw new BookingNotFoundException("Booking not found");
    	}
        return optionalBooking.get();
    }

    public boolean deleteById(Long id) throws BookingNotFoundException{
    	Optional<Booking> optionalBooking = bookingRepository.findById(id);
    	if(!optionalBooking.isPresent()) {
    		throw new BookingNotFoundException("Seat not found");
    	}
    	optionalBooking.get().setDeleted(true);
    	bookingRepository.save(optionalBooking.get());
        return optionalBooking.get().isDeleted();
    }

    public ArrayList<Booking> getBySeat(Long seat){
        return bookingRepository.findBySeatId(seat);
    }
    
    public ArrayList<Booking> getBySeatIdAndDateAndStartTimeAndEndTime(Long seat, LocalDate date,Integer startTime, Integer endTime){
  	  return bookingRepository.findBySeatIdAndDateAndStartTimeAndEndTime(seat, date, startTime, endTime);
    }
    
    public ArrayList<Booking> getBySeaIdAndDate(Long seat, LocalDate date){
    	return bookingRepository.findBySeatIdAndDate(seat, date);
    }
}
