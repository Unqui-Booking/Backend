package ar.edu.unq.tip.unquibooking.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.tip.unquibooking.exception.DeskNotFoundException;
import ar.edu.unq.tip.unquibooking.exception.SeatNotFoundException;
import ar.edu.unq.tip.unquibooking.model.Seat;
import ar.edu.unq.tip.unquibooking.services.SeatService;

@CrossOrigin("*")
@RestController
@RequestMapping("/seat")
public class SeatController {
	    
	    @Autowired
	    SeatService seatService;

	    @GetMapping()
	    public ArrayList<Seat> getAllSeats(){
	    	return  seatService.getAllSeats();		
	    }
	
	    @PostMapping()
	    public Seat saveSeat(@RequestBody Seat seat) throws DeskNotFoundException {
	    	return seatService.saveSeat(seat);
	    }
	    
	    @GetMapping(path="/{id}")
	    public Seat getSeat(@PathVariable("id") Long id) throws SeatNotFoundException{
	        return seatService.getSeat(id); 
	        
	    }
	    
	    @DeleteMapping(path="/{id}")
	    public String deleteById(@PathVariable("id") Long id) throws SeatNotFoundException{
	        boolean deleted = seatService.deleteById(id);
	        String message = deleted ? "Seat deleted: " + id : "Seat could not be deleted: " + id;
	        return message;
	    }
	    
	    @GetMapping("/query")
	    public ArrayList<Seat> getByDesk(@RequestParam("desk") Long desk){
	        return seatService.getByDesk(desk);
	    }
	
}
