package ar.edu.unq.tip.unquibooking.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    	return bookings.stream().filter(b -> b.getState().equals("confirmed") || b.getState().equals("cancelled") || b.getState().equals("fined")).collect(Collectors.toList());
    }
    
    @GetMapping("/current")
    public List<Booking> getCurrentBookingsByUser(@RequestParam("user") Long userId){
    	List<Booking> bookings = bookingService.getByUSer(userId, false);
    	LocalDate today = LocalDate.now(); 
    	return bookings.stream().filter(b -> (b.getDate().isAfter(today) || b.getDate().equals(today)) && (b.getState().equals("uploaded") || b.getState().equals("expired") || b.getState().equals("toConfirm"))).collect(Collectors.toList());
    }
    
    @GetMapping("/bystate")
    public List<Booking> getBookingsByDateAndState(@RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam("state") String state){
    	return bookingService.getByDateAndState(date, false, state);
    }
    
    @GetMapping("/today")  
    public List<Booking> getBookingsByToday(@RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
    	List<Booking> uploadedBookings = getBookingsByDateAndState(date, "uploaded");
    	List<Booking> toConfirmBookings = getBookingsByDateAndState(date, "toConfirm");
    	List<Booking> expiredBookings = getBookingsByDateAndState(date, "expired");
    	List<Booking> listResult = Stream.concat(toConfirmBookings.stream(), expiredBookings.stream()).collect(Collectors.toList());
    	List<Booking> listResult2 = Stream.concat(listResult.stream(), uploadedBookings.stream()).collect(Collectors.toList());
    	return listResult2;
    }

    @GetMapping("/fined")
    public List<Booking> getBookingFinedByUser(@RequestParam("user") Long userId) {
    	return bookingService.getByStateFinedAndUser(userId);
    }
    
    @GetMapping("/between")
    public List<Booking> getBookingsByUserBetweenDates(@RequestParam("start")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, 
    												   @RequestParam("end")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
    												   @RequestParam("user") Long userId){
    	
    	List<Booking> bookings = bookingService.getBookingsByUserBetweenDates(startDate, endDate, userId, false);
    	return bookings.stream().filter(booking -> !booking.getState().equals("fined")).collect(Collectors.toList());
    }
    
    @GetMapping("/byname")
    public List<Booking> getByUserName(@RequestParam("name") String userName){
    	List<Booking> bookings = getBookingsByToday(LocalDate.now());
    	return bookings.stream().filter(booking -> booking.getUser().getName().contains(userName)).collect(Collectors.toList());
    }
}
