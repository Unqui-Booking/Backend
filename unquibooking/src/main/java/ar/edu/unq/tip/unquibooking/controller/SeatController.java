package ar.edu.unq.tip.unquibooking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.tip.unquibooking.converter.SeatConverter;
import ar.edu.unq.tip.unquibooking.model.Seat;
import ar.edu.unq.tip.unquibooking.model.SeatDTO;
import ar.edu.unq.tip.unquibooking.services.SeatService;

@CrossOrigin("*")
@RestController
@RequestMapping("/seat")
public class SeatController {
	    
	    @Autowired
	    SeatService seatService;
	    @Autowired
	    SeatConverter seatConverter;

	    @GetMapping()
	    public ArrayList<SeatDTO> getAllSeats(){
	    	List<Seat> seats = seatService.getAllSeats();
	    	return seatConverter.entityToDto(seats);  		
	    }
	 
	    @PostMapping()
	    public SeatDTO saveSeat(@RequestBody SeatDTO seatDTO) {
	    	Seat seat = seatConverter.dtoToEntity(seatDTO);
	    	seat = seatService.saveSeat(seat);
	    	return seatConverter.entityToDTO(seat);
	    }
	    
	    @GetMapping(path="/{id}")
	    public SeatDTO getSeat(@PathVariable("id") Long id){
	    	Seat seat = seatService.getSeat(id); 
	        return seatConverter.entityToDTO(seat);
	    }
	    
	    @DeleteMapping(path="/{id}")
	    public String deleteById(@PathVariable("id") Long id){
	        boolean deleted = seatService.deleteById(id);
	        String message = deleted ? "Seat deleted: " + id : "Seat could not be deleted: " + id;
	        return message;
	    }
	    
	    @GetMapping("/query")
	    public ArrayList<SeatDTO> getByDesk(@RequestParam("desk") Long desk){
	    	List<Seat> seats = seatService.getByDesk(desk);
	        return seatConverter.entityToDto(seats); 
	    }
	
}
