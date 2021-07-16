package ar.edu.unq.tip.unquibooking.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.tip.unquibooking.exception.DeskNotFoundException;
import ar.edu.unq.tip.unquibooking.exception.SeatNotFoundException;
import ar.edu.unq.tip.unquibooking.model.Desk;
import ar.edu.unq.tip.unquibooking.model.Seat;
import ar.edu.unq.tip.unquibooking.repositories.SeatRepository;

@Service
public class SeatService {
	@Autowired
    SeatRepository seatRepository;
	@Autowired
	DeskService deskService;
	
    public List<Seat> getAllSeats(){
        return IteratorUtils.toList(seatRepository.findAll().iterator());
    }
    
    public Seat saveSeat(Seat seat) throws DeskNotFoundException{
    	Long deskId = seat.getDesk().getId();
    	Desk desk = deskService.getDesk(deskId);
        Seat newSeat = seatRepository.save(seat);
        newSeat.setDesk(desk);
        return newSeat;
    }

    public Seat getSeat(Long idSeat) throws SeatNotFoundException{
    	Optional<Seat> optionalSeat = seatRepository.findById(idSeat);
    	if(!optionalSeat.isPresent()) {
    		throw new SeatNotFoundException("Seat not found");
    	}
        return optionalSeat.get();
    }

    public boolean deleteById(Long id) throws SeatNotFoundException{
    	Optional<Seat> optionalSeat = seatRepository.findById(id);
    	if(!optionalSeat.isPresent()) {
    		throw new SeatNotFoundException("Seat not found");
    	}
    	optionalSeat.get().setDeleted(true);
    	seatRepository.save(optionalSeat.get());
        return optionalSeat.get().isDeleted();
    }

	public List<Seat> getByDesk(Long desk) {
        return seatRepository.findByDeskId(desk);
    }

}
