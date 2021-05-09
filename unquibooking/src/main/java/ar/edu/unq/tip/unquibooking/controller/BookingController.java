package ar.edu.unq.tip.unquibooking.controller;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.tip.unquibooking.model.Booking;
import ar.edu.unq.tip.unquibooking.model.BookingDTO;
import ar.edu.unq.tip.unquibooking.model.Seat;
import ar.edu.unq.tip.unquibooking.services.BookingService;

@CrossOrigin("*")
@RestController
@RequestMapping("/booking")
public class BookingController {
    
    @Autowired
    BookingService bookingService;

    @GetMapping()
    public ArrayList<BookingDTO> getAllBookings(){
        return (ArrayList<BookingDTO>) bookingService.getAllBookings().stream().map(s-> new BookingDTO(s)).collect(Collectors.toList());
    }

    @PostMapping()
    public BookingDTO saveBooking(@RequestBody Booking booking){
        return new BookingDTO(bookingService.saveBooking(booking));
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
    public ArrayList<BookingDTO> getBySeat(@RequestParam("seat") Seat seat){
       return (ArrayList<BookingDTO>) bookingService.getBySeat(seat).stream().map(s-> new BookingDTO(s)).collect(Collectors.toList());
    }



}
