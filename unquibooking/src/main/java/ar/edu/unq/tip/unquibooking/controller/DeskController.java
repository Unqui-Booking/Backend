package ar.edu.unq.tip.unquibooking.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import ar.edu.unq.tip.unquibooking.model.Desk;
import ar.edu.unq.tip.unquibooking.services.DeskService;

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
    public Desk saveDesk(@RequestBody Desk desk){
        return deskService.saveDesk(desk);
    }

    @GetMapping(path="{id}")
    public Optional<Desk> getDesk(@PathVariable("id") Long id){
        return deskService.getDesk(id);
    }

    @DeleteMapping(path="/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean deleted = deskService.deleteById(id);
        String message = deleted ? "Desk deleted: " + id : "Desk could not be deleted: " + id;
        return message;
    }
}
