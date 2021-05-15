package ar.edu.unq.tip.unquibooking.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.tip.unquibooking.model.Booking;
import ar.edu.unq.tip.unquibooking.model.LocalDateConverter;
import ar.edu.unq.tip.unquibooking.model.Seat;
import ar.edu.unq.tip.unquibooking.repositories.BookingRepository;

@Service
public class BookingService {
    
    @Autowired
    BookingRepository bookingRepository;

    public ArrayList<Booking> getAllBookings(){
        return (ArrayList<Booking>) bookingRepository.findAll();
    }

    public Booking saveBooking(Booking booking){
        return bookingRepository.save(booking);
    }

    public Booking getBooking(Long idBooking){
        return bookingRepository.findById(idBooking).get();
    }

    public boolean deleteById(Long id){
        try{
            bookingRepository.deleteById(id);
            return true;
        }
        catch(Exception error){
            return false;
        }
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
