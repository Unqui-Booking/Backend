package ar.edu.unq.tip.unquibooking.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.tip.unquibooking.model.Booking;
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

    public Optional<Booking> getBooking(Long idBooking){
        return bookingRepository.findById(idBooking);
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

    public Optional<Booking> getByDesk(Long idDesk){
        return bookingRepository.findByDesk(idDesk);
    }
}
