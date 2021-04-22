package ar.edu.unq.tip.unquibooking.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.tip.unquibooking.model.Desk;
import ar.edu.unq.tip.unquibooking.repositories.DeskRepository;

@Service
public class DeskService {

    @Autowired
    DeskRepository deskRepository;

    public ArrayList<Desk> getAllDesks(){
        return (ArrayList<Desk>) deskRepository.findAll();
    }
    
    public Desk saveDesk(Desk desk){
        return deskRepository.save(desk);
    }
}
