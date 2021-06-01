package ar.edu.unq.tip.unquibooking.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.tip.unquibooking.exception.BookingNotFoundException;
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
    public ArrayList<Booking> getAllBookings(){
         return bookingService.getAllBookings();
    }
    
    @PostMapping()
    public Booking saveBooking(@RequestBody Booking booking) throws SeatNotFoundException, UserNotFoundException, BookingRegisteredWithAdminUserException{
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
    public ArrayList<Booking> getBySeat(@RequestParam("seat") Long seat){
    	return bookingService.getBySeat(seat);
    }
    
    @GetMapping("/details")
    public ArrayList<Booking> getBySeatIdAndDateAndStartTimeAndEndTime(
    		@RequestParam("seat") Long seat,
    		@RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
    		@RequestParam("startTime")Integer startTime,
    		@RequestParam("endTime")Integer endTime){
    	return bookingService.getBySeatIdAndDateAndStartTimeAndEndTime(seat, date, startTime, endTime);
    }
    
    @GetMapping("/sd")
    public ArrayList<Booking> getBySeatIdAndDate(
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
    	ArrayList<Booking> bookings = new ArrayList<Booking>();
    	
    	ArrayList<Seat> seats = seatService.getByDesk(desk);
    	
    	for(int i=0; i<seats.size();i++) {
    		bookings = getBySeatIdAndDateAndStartTimeAndEndTime(seats.get(i).getId(), date, startTime, endTime);
    		mapBooking.put(seats.get(i).getId(), bookings.size()==0);
    	}
    	
    	return mapBooking;
    }

}
