package ar.edu.unq.tip.unquibooking.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.tip.unquibooking.dto.BookingConverter;
import ar.edu.unq.tip.unquibooking.dto.BookingDTO;
import ar.edu.unq.tip.unquibooking.model.Booking;
import ar.edu.unq.tip.unquibooking.model.LocalDateConverter;
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
    BookingConverter bookingConverter;
    @Autowired
    SeatService seatService;

    @GetMapping()
    public ArrayList<BookingDTO> getAllBookings(){
         List<Booking> bookings = bookingService.getAllBookings();
         return bookingConverter.entityToDto(bookings);
    		}

    @PostMapping()
    public BookingDTO saveBooking(@RequestBody BookingDTO bookingDTO){
    	Booking booking = bookingConverter.dtoToEntity(bookingDTO);
    	booking = bookingService.saveBooking(booking);
    	return bookingConverter.entityToDTO(booking);
    }
    
//    @PostMapping()
//    public Booking saveBooking(@RequestBody Booking booking){
//    	booking = bookingService.saveBooking(booking);
//    	return booking;
//    }

    @GetMapping(path="/{id}")
    public Booking getBooking(@PathVariable("id") Long id){
    	Booking booking = bookingService.getBooking(id);
        return booking;
    }

    @DeleteMapping(path="/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean deleted = bookingService.deleteById(id);
        String message = deleted ? "Booking deleted: " + id : "Booking could not be deleted: " + id;
        return message;
    }

    @GetMapping("/query")
    public ArrayList<BookingDTO> getBySeat(@RequestParam("seat") Long seat){
    	List<Booking> bookings = bookingService.getBySeat(seat);
    	return bookingConverter.entityToDto(bookings);
    }
    
    @GetMapping("/details")
    public ArrayList<BookingDTO> getBySeatIdAndDateAndStartTimeAndEndTime(
    		@RequestParam("seat") Long seat,
    		@RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
    		@RequestParam("startTime")Integer startTime,
    		@RequestParam("endTime")Integer endTime){
    	List<Booking> bookings = bookingService.getBySeatIdAndDateAndStartTimeAndEndTime(seat, date, startTime, endTime);
    	return bookingConverter.entityToDto(bookings);
    }
    
    @GetMapping("/sd")
    public ArrayList<BookingDTO> getBySeatIdAndDate(
    		@RequestParam("seat") Long seat,
    		@RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
    	List<Booking> bookings = bookingService.getBySeaIdAndDate(seat, date);
    	return bookingConverter.entityToDto(bookings);
    }
    
    @GetMapping("/availabled")
    public HashMap<Long, Boolean> getMap(
    		@RequestParam("desk") Long desk,
    		@RequestParam("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
    		@RequestParam("startTime")Integer startTime,
    		@RequestParam("endTime")Integer endTime){
    	
    	HashMap<Long, Boolean> mapBooking = new HashMap<Long, Boolean>();
    	ArrayList<BookingDTO> bookings = new ArrayList<BookingDTO>();
    	
    	ArrayList<Seat> seats = seatService.getByDesk(desk);
    	
    	for(int i=0; i<seats.size();i++) {
    		bookings = getBySeatIdAndDateAndStartTimeAndEndTime(seats.get(i).getId(), date, startTime, endTime);
    		mapBooking.put(seats.get(i).getId(), bookings.size()==0);
    	}
    	
    	return mapBooking;
    }

}
