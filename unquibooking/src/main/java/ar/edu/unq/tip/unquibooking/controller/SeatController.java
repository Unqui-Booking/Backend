package ar.edu.unq.tip.unquibooking.controller;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.tip.unquibooking.model.Seat;
import ar.edu.unq.tip.unquibooking.model.SeatDTO;
import ar.edu.unq.tip.unquibooking.services.SeatService;

@CrossOrigin("*")
@RestController
@RequestMapping("/seat")
public class SeatController {
	    
	    @Autowired
	    SeatService seatService;

	    @GetMapping()
	    public ArrayList<SeatDTO> getAllSeats(){
	        return (ArrayList<SeatDTO>) seatService.getAllSeats().stream().map(s-> new SeatDTO(s)).collect(Collectors.toList());
	    }

	    @PostMapping()
	    public SeatDTO saveSeat(@RequestBody Seat seat){
	        return new SeatDTO(seatService.saveSeat(seat));
	    }

	    @GetMapping(path="/{id}")
	    public Optional<Seat> getSeat(@PathVariable("id") Long id){
	        return seatService.getSeat(id);
	    }

	    @DeleteMapping(path="/{id}")
	    public String deleteById(@PathVariable("id") Long id){
	        boolean deleted = seatService.deleteById(id);
	        String message = deleted ? "Seat deleted: " + id : "Seat could not be deleted: " + id;
	        return message;
	    }

	    @GetMapping("/query")
	    public ArrayList<SeatDTO> getByDesk(@RequestParam("desk") Long desk){
	       return (ArrayList<SeatDTO>) seatService.getByDesk(desk).stream().map(s-> new SeatDTO(s)).collect(Collectors.toList());
	    }
	
}
