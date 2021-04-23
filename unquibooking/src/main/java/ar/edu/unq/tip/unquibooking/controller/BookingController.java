package ar.edu.unq.tip.unquibooking.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.tip.unquibooking.model.Booking;
import ar.edu.unq.tip.unquibooking.services.BookingService;

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
        String messageCondition = deleted ? "Booking deleted: " + id : "Booking could not be deleted: " + id;
        return messageCondition;
    }

    @GetMapping("/query")
    public Optional<Booking> getByDesk(@RequestParam("prioridad") Long id){
        return bookingService.getByDesk(id);
    }

}
