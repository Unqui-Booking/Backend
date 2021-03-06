package ar.edu.unq.tip.unquibooking.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ar.edu.unq.tip.unquibooking.exception.DeskBadRequestException;
import ar.edu.unq.tip.unquibooking.exception.DeskNotFoundException;
import ar.edu.unq.tip.unquibooking.model.Desk;
import ar.edu.unq.tip.unquibooking.repositories.DeskRepository;

@Service
public class DeskService {

    @Autowired
    DeskRepository deskRepository;
    
    public List<Desk> getAllDesks(){
        return  IteratorUtils.toList(deskRepository.findAll().iterator());
    }
    
    public Desk saveDesk(Desk desk) throws DeskBadRequestException{
    	if(!validate(desk)) {
    		throw new DeskBadRequestException("Wrong desk");
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
    
    public List<Desk> getByArea(String area){
    	return deskRepository.findByArea(area);
    }
    
    private boolean validate(Desk desk) {
    	String area = desk.getArea();
    	boolean correctArea = area.equals("silent") || area.equals("general");
    	List<String> allNamesDesk = getAllDesks().stream().map(d -> d.getNameDesk().toLowerCase()).collect(Collectors.toList());
    	boolean uniqueName = !allNamesDesk.contains(desk.getNameDesk().toLowerCase());
    	return correctArea && uniqueName;
    }
}
