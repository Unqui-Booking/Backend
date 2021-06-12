package ar.edu.unq.tip.unquibooking.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.tip.unquibooking.exception.BookingNotFoundException;
import ar.edu.unq.tip.unquibooking.exception.BookingRegisteredWithAdminUserException;
import ar.edu.unq.tip.unquibooking.exception.SeatNotFoundException;
import ar.edu.unq.tip.unquibooking.exception.UserNotFoundException;
import ar.edu.unq.tip.unquibooking.model.Booking;
import ar.edu.unq.tip.unquibooking.model.Desk;
import ar.edu.unq.tip.unquibooking.model.Seat;
import ar.edu.unq.tip.unquibooking.model.User;
import ar.edu.unq.tip.unquibooking.repositories.BookingRepository;

@Service
public class BookingService {
    
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    SeatService seatService;
    @Autowired
    UserService userService;

    public List<Booking> getAllBookings(){
        return IteratorUtils.toList(bookingRepository.findAll().iterator());
    }
    
    public Booking saveBooking(Booking booking) throws SeatNotFoundException, UserNotFoundException, BookingRegisteredWithAdminUserException, BookingNotFoundException {
    	    
    	Booking result = null;
    	
    	Long seatId = booking.getSeat().getId();
    	Seat seat = seatService.getSeat(seatId);
    	Long userId = booking.getUser().getId();
    	User user = userService.getUSer(userId);
    	
    	if(user.isAdmin()) {
    		throw new BookingRegisteredWithAdminUserException("Booking registered with admin user");
    	}
    	
    	if(booking.getId()!= null) {//if booking existing
    		result = updateBooking(booking, seat, user);//update existing booking
    	}else {
    		result = registerBooking(booking, seat, user); //create new booking
    	}
    	return result;
    }
    
    private Booking updateBooking(Booking booking, Seat seat, User user) throws BookingNotFoundException {
		Booking existingBooking = this.getBooking(booking.getId());
		existingBooking.setSeat(seat);
		existingBooking.setUser(user);
		existingBooking.setDate(booking.getDate());
		existingBooking.setDeleted(booking.isDeleted());
		existingBooking.setEndTime(booking.getEndTime());
		existingBooking.setStartTime(booking.getStartTime());
		existingBooking.setState(booking.getState());
		bookingRepository.save(existingBooking);
		return existingBooking;
    }
    
    private Booking registerBooking(Booking booking, Seat seat, User user) {
		Booking newBooking = bookingRepository.save(booking);
    	newBooking.setSeat(seat);
    	newBooking.setUser(user);
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

    public List<Booking> getBySeat(Long seat){
        return bookingRepository.findBySeatId(seat);
    }
    
    public List<Booking> getBySeatIdAndDateAndStartTimeAndEndTime(Long seat, LocalDate date,Integer startTime, Integer endTime){
  	  return bookingRepository.findBySeatIdAndDateAndStartTimeAndEndTime(seat, date, startTime, endTime);
    }
    
    public List<Booking> getBySeaIdAndDate(Long seat, LocalDate date){
    	return bookingRepository.findBySeatIdAndDate(seat, date);
    }
    
    public List<Booking> getByUSer(Long user, boolean deleted){
    	return bookingRepository.findByUserIdAndDeletedOrderByDateDesc(user, deleted);
    }
    
    public List<Booking> getByDate(LocalDate date, boolean deleted){
    	return bookingRepository.findByDateAndDeletedOrderByStartTimeAsc(date, deleted);
    }
}
