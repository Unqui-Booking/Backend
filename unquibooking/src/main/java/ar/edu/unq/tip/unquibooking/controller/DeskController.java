package ar.edu.unq.tip.unquibooking.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.tip.unquibooking.model.Desk;
import ar.edu.unq.tip.unquibooking.services.DeskService;

@RestController
@RequestMapping("desk")
public class DeskController {
    
    @Autowired
    DeskService deskService;

    @GetMapping()
    public ArrayList<Desk> getAllDesk(){
        return deskService.getAllDesks();
    }

    @PostMapping()
    public Desk saveDesk(Desk desk){
        return deskService.saveDesk(desk);
    }

}
