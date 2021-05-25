package ar.edu.unq.tip.unquibooking.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ar.edu.unq.tip.unquibooking.exception.DeskBadRequest;
import ar.edu.unq.tip.unquibooking.exception.DeskNotFoundException;
import ar.edu.unq.tip.unquibooking.model.Desk;
import ar.edu.unq.tip.unquibooking.services.DeskService;


@CrossOrigin("*")
@RestController
@RequestMapping("/desk")
public class DeskController {
    
    @Autowired
    DeskService deskService;

    @GetMapping()
    public ArrayList<Desk> getAllDesk(){
        return deskService.getAllDesks();
    }

    @PostMapping()
    public Desk saveDesk(@RequestBody Desk desk) throws DeskBadRequest{
		return deskService.saveDesk(desk);
    }
    
    @GetMapping(path="{id}")
    public Desk getDesk(@PathVariable("id") Long id) throws DeskNotFoundException {
		return deskService.getDesk(id);
    }

    @DeleteMapping(path="/{id}")
    public String deleteById(@PathVariable("id") Long id) throws DeskNotFoundException{
        boolean deleted = deskService.deleteById(id);
        String message = deleted ? "Desk deleted: " + id : "Desk could not be deleted: " + id;
        return message;
    }
    
    @GetMapping("/query")
    public ArrayList<Desk> getByArea(@RequestParam("area") String area){
    	return deskService.getByArea(area);
    }
    
}
