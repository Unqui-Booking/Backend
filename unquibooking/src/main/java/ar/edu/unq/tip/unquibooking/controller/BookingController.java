package ar.edu.unq.tip.unquibooking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.tip.unquibooking.dto.BookingConverter;
import ar.edu.unq.tip.unquibooking.dto.BookingDTO;
import ar.edu.unq.tip.unquibooking.model.Booking;
import ar.edu.unq.tip.unquibooking.services.BookingService;

@CrossOrigin("*")
@RestController
@RequestMapping("/booking")
public class BookingController {
    
    @Autowired
    BookingService bookingService;
    @Autowired
    BookingConverter bookingConverter;

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

    @GetMapping(path="/{id}")
    public BookingDTO getBooking(@PathVariable("id") Long id){
    	Booking booking = bookingService.getBooking(id);
        return bookingConverter.entityToDTO(booking);
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


}
