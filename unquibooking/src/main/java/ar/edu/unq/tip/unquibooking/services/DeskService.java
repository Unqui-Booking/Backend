package ar.edu.unq.tip.unquibooking.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ar.edu.unq.tip.unquibooking.exception.DeskBadRequest;
import ar.edu.unq.tip.unquibooking.exception.DeskNotFoundException;
import ar.edu.unq.tip.unquibooking.model.Desk;
import ar.edu.unq.tip.unquibooking.repositories.DeskRepository;

@Service
public class DeskService {

    @Autowired
    DeskRepository deskRepository;

    public ArrayList<Desk> getAllDesks(){
        return (ArrayList<Desk>) deskRepository.findAll();
    }
    
    public Desk saveDesk(Desk desk) throws DeskBadRequest{
    	if(!validate(desk)) {
    		throw new DeskBadRequest("Wrong desk");
    	}
        return deskRepository.save(desk);
    }

    public Desk getDesk(Long id) throws DeskNotFoundException {
    	Optional<Desk> optionalDesk = deskRepository.findById(id);
    	if(!optionalDesk.isPresent()) {
    		throw new DeskNotFoundException("Desk not found");
    	}
        return optionalDesk.get();
    } 
    
    public boolean deleteById(Long id) throws DeskNotFoundException{
    	Optional<Desk> optionalDesk = deskRepository.findById(id);
    	if(!optionalDesk.isPresent()) {
    		throw new DeskNotFoundException("Desk not found");
    	}
        optionalDesk.get().setDeleted(true);
        deskRepository.save(optionalDesk.get());
        return optionalDesk.get().isDeleted();
    }
    
    public ArrayList<Desk> getByArea(String area){
    	return deskRepository.findByArea(area);
    }
    
    private boolean validate(Desk desk) {
    	String area = desk.getArea();
    	boolean correctArea = area.equals("silent") || area.equals("general");
    	ArrayList<String> allNamesDesk = (ArrayList<String>) getAllDesks().stream().map(d -> d.getNameDesk().toLowerCase()).collect(Collectors.toList());
    	boolean uniqueName = !allNamesDesk.contains(desk.getNameDesk().toLowerCase());
    	return correctArea && uniqueName;
    }
}
