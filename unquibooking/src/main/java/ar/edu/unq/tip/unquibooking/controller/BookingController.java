package ar.edu.unq.tip.unquibooking.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.tip.unquibooking.model.Booking;
import ar.edu.unq.tip.unquibooking.model.Desk;
import ar.edu.unq.tip.unquibooking.services.BookingService;

@CrossOrigin("*")
@RestController
@RequestMapping("/booking")
public class BookingController {
    
    @Autowired
    BookingService bookingService;

    @GetMapping()
    public ArrayList<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @PostMapping()
    public Booking saveBooking(@RequestBody Booking booking){
        return bookingService.saveBooking(booking);
    }

    @GetMapping(path="/{id}")
    public Optional<Booking> getBooking(@PathVariable("id") Long id){
        return bookingService.getBooking(id);
    }

    @DeleteMapping(path="/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean deleted = bookingService.deleteById(id);
        String message = deleted ? "Booking deleted: " + id : "Booking could not be deleted: " + id;
        return message;
    }

    @GetMapping("/query")
    public ArrayList<Booking> getByDesk(@RequestParam("desk") Long desk){
       return bookingService.getByDesk(desk);
    }

    //@GetMapping("/query")
    //public ArrayList<Booking> getByStartTime(@RequestParam("startTime") Integer startTime){
    	
    //    return bookingService.getByStartTime(startTime);
    //}

}
