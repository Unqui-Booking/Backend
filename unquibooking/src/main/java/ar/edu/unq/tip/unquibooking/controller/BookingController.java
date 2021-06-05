package ar.edu.unq.tip.unquibooking.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.tip.unquibooking.exception.BookingNotFoundException;
import ar.edu.unq.tip.unquibooking.exception.BookingNotUpdateException;
import ar.edu.unq.tip.unquibooking.exception.BookingRegisteredWithAdminUserException;
import ar.edu.unq.tip.unquibooking.exception.SeatNotFoundException;
import ar.edu.unq.tip.unquibooking.exception.UserNotFoundException;
import ar.edu.unq.tip.unquibooking.model.Booking;
import ar.edu.unq.tip.unquibooking.model.Seat;
import ar.edu.unq.tip.unquibooking.services.BookingService;
import ar.edu.unq.tip.unquibooking.services.SeatService;

@CrossOrigin("*")
@RestController
@RequestMapping("/booking")
public class BookingController {
    
    @Autowired
    BookingService bookingService;
    @Autowired
    SeatService seatService;

    @GetMapping()
    public List<Booking> getAllBookings(){
         return bookingService.getAllBookings();
    }
    
    @PostMapping()
    public Booking saveBooking(@RequestBody Booking booking) throws SeatNotFoundException, UserNotFoundException, BookingRegisteredWithAdminUserException, BookingNotFoundException{
    	booking = bookingService.saveBooking(booking);
    	return booking;
    }

    @GetMapping(path="/{id}")
    public Booking getBooking(@PathVariable("id") Long id) throws BookingNotFoundException{
        return bookingService.getBooking(id);
    }

    @DeleteMapping(path="/{id}")
    public String deleteById(@PathVariable("id") Long id) throws BookingNotFoundException{
        boolean deleted = bookingService.deleteById(id);
        String message = deleted ? "Booking deleted: " + id : "Booking could not be deleted: " + id;
        return message;
    }

    @GetMapping("/query")
    public List<Booking> getBySeat(@RequestParam("seat") Long seat){
    	return bookingService.getBySeat(seat);
    }
    
    @GetMapping("/details")
    public List<Booking> getBySeatIdAndDateAndStartTimeAndEndTime(
    		@RequestParam("seat") Long seat,
    		@RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
    		@RequestParam("startTime")Integer startTime,
    		@RequestParam("endTime")Integer endTime){
    	return bookingService.getBySeatIdAndDateAndStartTimeAndEndTime(seat, date, startTime, endTime);
    }
    
    @GetMapping("/sd")
    public List<Booking> getBySeatIdAndDate(
    		@RequestParam("seat") Long seat,
    		@RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
    	return bookingService.getBySeaIdAndDate(seat, date);
    }
    
    @GetMapping("/availabled")
    public HashMap<Long, Boolean> getMap(
    		@RequestParam("desk") Long desk,
    		@RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
    		@RequestParam("startTime")Integer startTime,
    		@RequestParam("endTime")Integer endTime){
    	
    	HashMap<Long, Boolean> mapBooking = new HashMap<Long, Boolean>();
    	List<Booking> bookings = new ArrayList<Booking>();
    	
    	List<Seat> seats = seatService.getByDesk(desk);
    	
    	for(int i=0; i<seats.size();i++) {
    		bookings = getBySeatIdAndDateAndStartTimeAndEndTime(seats.get(i).getId(), date, startTime, endTime);
    		mapBooking.put(seats.get(i).getId(), bookings.size()==0);
    	}
    	
    	return mapBooking;
    }
    
    @GetMapping("/user")
    public List<Booking> getByUser(@RequestParam("user") Long userId){
    	return bookingService.getByUSer(userId, false);
    }
    
    @GetMapping("/historical")
    public List<Booking> getHistoricalBookingsByUser(@RequestParam("user") Long userId){
    	List<Booking> bookings = bookingService.getByUSer(userId, false);
    	LocalDate today = LocalDate.now(); 
    	return bookings.stream().filter(b -> b.getDate().isBefore(today)).collect(Collectors.toList());
    }
    
    @GetMapping("/current")
    public List<Booking> getCurrentBookingsByUser(@RequestParam("user") Long userId){
    	List<Booking> bookings = bookingService.getByUSer(userId, false);
    	LocalDate today = LocalDate.now(); 
    	return bookings.stream().filter(b -> b.getDate().isAfter(today) || b.getDate().equals(today)).collect(Collectors.toList());
    }
    
    @GetMapping("/bydate")
    public List<Booking> getBookingsByDate(@RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
    	return bookingService.getByDate(date);
    }

}
